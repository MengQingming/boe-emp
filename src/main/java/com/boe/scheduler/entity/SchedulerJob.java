package com.boe.scheduler.entity;

import com.boe.common.persistence.DataEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SchedulerJob
 * @Description: TODO 调度基础表
 * @author WangShengDong
 * @date 2017年3月23日 下午8:26:48
 */
public class SchedulerJob extends DataEntity<SchedulerJob> {

	private static final long	serialVersionUID	= 1L;

	/** 任务名称 */
	private String				jobName;

	/** 任务编码 */
	private String				jobCode;

	/** 任务项目路径 */
	private String				jobPath;

	/** 调度方法名 */
	private String				jobMethod;

	/** 任务描述 */
	private String				jobMessage;

	/** 授权IP */
	private String				jobIp;
	
	/** 授权端口 */
	private String				jobPort;
	
	/** 授权服务 */
	private String				jobService;

	/** 调度锁 */
	private String				jobLock;
	
	/** 下次调度时间 */
	private Timestamp			jobNextDate;
	
	/** 上次调度时间 */
	private Timestamp			jobLastDate;

	/** 调度次数 */
	private Integer				jobNum;

	/** 调度任务状态 */
	private String				jobStatus;

	private Date				creationDate;

	private Date				lastUpdateDate;
	
	private List<SchedulerTrigger> triggers;

	public SchedulerJob() {
		this.jobNum = 0;
		this.jobStatus = "1";
		this.jobLock = "noLock";
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobPath() {
		return jobPath;
	}

	public void setJobPath(String jobPath) {
		this.jobPath = jobPath;
	}

	public String getJobMessage() {
		return jobMessage;
	}

	public void setJobMessage(String jobMessage) {
		this.jobMessage = jobMessage;
	}

	public String getJobIp() {
		return jobIp;
	}

	public void setJobIp(String jobIp) {
		this.jobIp = jobIp;
	}

	public String getJobPort() {
		return jobPort;
	}

	public void setJobPort(String jobPort) {
		this.jobPort = jobPort;
	}

	public String getJobLock() {
		return jobLock;
	}

	public void setJobLock(String jobLock) {
		this.jobLock = jobLock;
	}

	public Timestamp getJobLastDate() {
		return jobLastDate;
	}

	public void setJobLastDate(Timestamp jobLastDate) {
		this.jobLastDate = jobLastDate;
	}

	public Integer getJobNum() {
		return jobNum;
	}

	public void setJobNum(Integer jobNum) {
		this.jobNum = jobNum;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getJobMethod() {
		return jobMethod;
	}

	public void setJobMethod(String jobMethod) {
		this.jobMethod = jobMethod;
	}

	public List<SchedulerTrigger> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<SchedulerTrigger> triggers) {
		this.triggers = triggers;
	}

	public String getJobService() {
		return jobService;
	}

	public void setJobService(String jobService) {
		this.jobService = jobService;
	}

	public Timestamp getJobNextDate() {
		return jobNextDate;
	}

	public void setJobNextDate(Timestamp jobNextDate) {
		this.jobNextDate = jobNextDate;
	}

}