package com.boe.scheduler.entity;

import com.boe.common.persistence.DataEntity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName: SchedulerTrigger
 * @Description: TODO 触发器
 * @author WangShengDong
 * @date 2017年3月23日 下午8:27:14
 */
public class SchedulerTrigger extends DataEntity<SchedulerTrigger> {

	private static final long	serialVersionUID	= 1L;

	/** 调度任务ID */
	private Integer				jobId;

	/** 任务名称 */
	private String				jobName;

	/** 任务编码 */
	private String				jobCode;

	/** 触发类型 */
	private String				triggerType;

	/** 触发类型名称 */
	private String				triggerTypeName;

	/** cron表达式 */
	private String				triggerCron;

	/** 轮询周期时长 */
	private Long				triggerSleep;

	/** 轮询周期下一个时间点 */
	private Timestamp			triggerSleepDate;

	/** 固定日期 */
	private Date				triggerDate;

	/** 触发器状态 */
	private String				triggerStatus		= "1";

	private Date				creationDate;

	private Date				lastUpdateDate;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
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

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public String getTriggerCron() {
		return triggerCron;
	}

	public void setTriggerCron(String triggerCron) {
		this.triggerCron = triggerCron;
	}

	public Long getTriggerSleep() {
		return triggerSleep;
	}

	public void setTriggerSleep(Long triggerSleep) {
		this.triggerSleep = triggerSleep;
	}

	public Date getTriggerDate() {
		return triggerDate;
	}

	public void setTriggerDate(Date triggerDate) {
		this.triggerDate = triggerDate;
	}

	public String getTriggerStatus() {
		return triggerStatus;
	}

	public void setTriggerStatus(String triggerStatus) {
		this.triggerStatus = triggerStatus;
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

	public String getTriggerTypeName() {
		return triggerTypeName;
	}

	public void setTriggerTypeName(String triggerTypeName) {
		this.triggerTypeName = triggerTypeName;
	}

	public Timestamp getTriggerSleepDate() {
		return triggerSleepDate;
	}

	public void setTriggerSleepDate(Timestamp triggerSleepDate) {
		this.triggerSleepDate = triggerSleepDate;
	}

}