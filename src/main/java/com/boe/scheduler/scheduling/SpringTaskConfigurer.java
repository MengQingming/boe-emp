package com.boe.scheduler.scheduling;

import com.boe.scheduler.entity.SchedulerJob;
import com.boe.scheduler.entity.SchedulerTrigger;
import com.boe.scheduler.entity.SchedulerLog;
import com.boe.scheduler.service.SchedulerJobService;
import com.boe.scheduler.service.SchedulerLogService;
import com.boe.scheduler.service.SchedulerTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName: SpringTaskConfigurer
 * @Description: TODO 定时任务必须调用的配置文件
 * @author WangShengDong
 * @date 2017年3月22日17:14:14
 */
@Component
public class SpringTaskConfigurer implements SchedulingConfigurer {

	public static ScheduledTaskRegistrar	TASK_REGISTRAR;

	public static ScheduledTaskRegistrar	TASK_REGISTRAR_RUN;

	public static final String				LOCALHOST_IP	= getIp();

	public static final String				SERVICE_NAME	= System.getProperty("weblogic.Name");

	public static Map<String, SchedulerJob>	JOB_MAP;

	public static Map<Runnable, Trigger>	TRIGGER_TASKS;
	
	@Autowired
	private ApplicationContext				context;

	@Autowired
	private SchedulerJobService				jobService;

	@Autowired
	private SchedulerLogService				logService;

	@Autowired
	private SchedulerTriggerService			triggerService;
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		TASK_REGISTRAR = TASK_REGISTRAR_RUN = taskRegistrar;
		triggers();
		TASK_REGISTRAR_RUN.setTriggerTasks(TRIGGER_TASKS);
	}

	public void triggers() {
		TRIGGER_TASKS = new HashMap<Runnable, Trigger>();
		JOB_MAP = new HashMap<String, SchedulerJob>();
		SchedulerJob job = new SchedulerJob();
		job.setJobIp(LOCALHOST_IP);
		List<SchedulerJob> list = jobService.findList(job);
		for (SchedulerJob schedulerJob : list) {
			TRIGGER_TASKS.put(new TaskRunnable(schedulerJob.getJobCode()) {
				@Override
				public void run() {
					taskRegistrarRun(getJobCode(), "A");
				}
			}, new TaskTrigger(schedulerJob.getJobCode()) {
				@Override
				public Date nextExecutionTime(TriggerContext triggerContext) {
					return getNextDate(getJobCode());
				}
			});
			schedulerJob.setJobIp(LOCALHOST_IP);
			schedulerJob.setJobService(SERVICE_NAME == null ? "SERVICE_NOT" : SERVICE_NAME);
			schedulerJob.setTriggers(triggerService.findListByJobCode(schedulerJob.getJobCode()));
			JOB_MAP.put(schedulerJob.getJobCode(), schedulerJob);
		}
	}

	/**
	 * 重置任务
	 * 
	 * @param
	 */
	public void taskResult() {
		triggers();
		try {
			TASK_REGISTRAR_RUN.destroy();
		} catch (Exception e) {
		}
		TASK_REGISTRAR_RUN = TASK_REGISTRAR;
		TASK_REGISTRAR_RUN.setTriggerTasks(TRIGGER_TASKS);
		TASK_REGISTRAR_RUN.afterPropertiesSet();
	}

	public Date getNextDate(String jobCode) {
		if (jobCode == null || "".equals(jobCode))
			return null;
		if (JOB_MAP.get(jobCode) == null)
			return null;
		List<SchedulerTrigger> triggers = JOB_MAP.get(jobCode).getTriggers();
		Date nextDate = null;
		for (SchedulerTrigger schedulerTrigger : triggers) {
			Date runDate = new Date();
			switch (schedulerTrigger.getTriggerType()) {
			case "forCron":
				runDate = forCron(schedulerTrigger.getTriggerCron());
				break;
			case "forSleep":
				runDate = forSleep(schedulerTrigger);
				break;
			case "forDate":
				runDate = forDate(schedulerTrigger.getTriggerDate());
				break;
			default:
				break;
			}
			if (nextDate == null) {
				nextDate = runDate;
			} else if (runDate.compareTo(new Date()) > 0 && runDate.compareTo(nextDate) <= 0) {
				nextDate = runDate;
			}
		}
		return nextDate;
	}

	/**
	 * 新版任务执行
	 * 
	 * @data 2017年4月2日21:01:04
	 * @param jobCode
	 * @param runningType
	 */
	public void taskRegistrarRun(String jobCode, String runningType) {
		String jobLock = UUID.randomUUID().toString().trim().replaceAll("-", "");
		SchedulerJob job = JOB_MAP.get(jobCode);
		if (job != null) {
			job.setJobLock(jobLock);
			if (runningType.equals("M")) {
				job.setJobNextDate(null);
			} else {
				job.setJobNextDate(new Timestamp(new Date().getTime()));
			}
			if (jobService.getByJobCode(job)) {
				SchedulerLog log = new SchedulerLog();
				log.setJobStartDate(new Timestamp(System.currentTimeMillis()));
				log.setJobStatus("0");
				try {
					Class<?> clazz = Class.forName(job.getJobPath());
					Method m2 = clazz.getMethod(job.getJobMethod());

					m2.invoke(context.getBean(clazz));

					log.setJobShowMsg("任务调用完成");
					log.setJobStatus("1");
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					//因为目前没有配置集群，所以出现内部异常关闭调度功能暂时注释；
					//JOB_MAP.remove(jobCode);
					String exc = "<pre>任务IP：" + job.getJobIp() + "\t任务调度服务：" + job.getJobService() + "\n\n" + getExc(e.getTargetException()) + "</pre>";
					log.setJobException(exc);
				} catch (Exception e) {
					e.printStackTrace();
					JOB_MAP.remove(jobCode);
					String exc = "<pre>任务IP：" + job.getJobIp() + "\t任务调度服务：" + job.getJobService() + "\n\n" + getExc(e) + "</pre>";
					log.setJobException(exc);
				}
				log.setJobId(job.getId());
				log.setJobCode(job.getJobCode());
				log.setJobName(job.getJobName());
				log.setJobIp(job.getJobIp());
				log.setJobPort(job.getJobPort());
				log.setJobService(job.getJobService());
				log.setJobLock(job.getJobLock());
				Date nextDate = getNextDate(job.getJobCode());
				if (nextDate != null)
					job.setJobNextDate(new Timestamp(nextDate.getTime()));
				else
					job.setJobNextDate(null);
				jobService.jobCompletion(job);
				log.setJobStopDate(new Timestamp(System.currentTimeMillis()));
				log.setJobRunningType(runningType);
				logService.save(log);
			}
		}
	}

	private static String getExc(Throwable e) {
		StringBuffer stringBuffer = new StringBuffer("<span style='color:red;'>" + e.toString() + "\n</span>");
		StackTraceElement[] messages = e.getStackTrace(); 
		for (StackTraceElement stackTraceElement : messages) {
			stringBuffer.append("\t" + stackTraceElement.toString() + "\n");
			if (stringBuffer.length()>3000)
				break;
		}
		return stringBuffer.toString();
	}

	public static String getIp() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				String name = intf.getName();
				if (!name.contains("docker") && !name.contains("lo")) {
					for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							String ipaddress = inetAddress.getHostAddress().toString();
							if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80") && !ipaddress.contains("2.0.") && !ipaddress.contains("192.168.122.1")) {
								return ipaddress;
							}
						}
					}
				}
			}
		} catch (SocketException e) {
		}
		return "0.0.0.0";
	}

	/**
	 * 通过CRON表达式获取下一次计划执行时间；
	 * 
	 * @param cron
	 *            cron表达式 (例： 0/10 * * * * ?)
	 * @return
	 */
	private Date forCron(String cron) {
		CronSequenceGenerator sequenceGenerator = new CronSequenceGenerator(cron);
		return sequenceGenerator.next(new Date());
	}

	/**
	 * 指定特定执行时间；（仅调用一次时执行）
	 * 
	 * @param taskDate
	 *            传入指定运行时间
	 * @return
	 */
	private Date forDate(Date taskDate) {
		if (taskDate.compareTo(new Date()) > 0)
			return taskDate;
		else
			return null;
	}

	/**
	 * 获取循环调度时间点
	 * 
	 * @param trigger
	 * @return
	 */
	private Timestamp forSleep(SchedulerTrigger trigger) {
		if (trigger.getTriggerSleepDate() == null)
			trigger.setTriggerSleepDate(new Timestamp(new Date().getTime()));
		if (trigger.getTriggerSleepDate().getTime() < new Date().getTime()) {
			trigger.setTriggerSleepDate(new Timestamp(new Date().getTime() + trigger.getTriggerSleep()));
		}
		return trigger.getTriggerSleepDate();
	}

}