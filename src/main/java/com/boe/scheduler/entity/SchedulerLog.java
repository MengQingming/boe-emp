package com.boe.scheduler.entity;

import com.boe.common.persistence.DataEntity;

import java.sql.Timestamp;

/**
 * @ClassName: SchedulerLog
 * @Description: TODO 调度日志表
 * @author WangShengDong
 * @date 2017年3月23日 下午8:27:00
 */
public class SchedulerLog extends DataEntity<SchedulerLog> {

	private static final long	serialVersionUID	= 1L;

	/** 任务ID */
	private Integer				jobId;

	/** 任务名称 */
	private String				jobName;

	/** 任务编码 */
	private String				jobCode;

	/** 调度IP */
	private String				jobIp;

	/** 调度端口 */
	private String				jobPort;
	
	/** 授权服务 */
	private String				jobService;

	/** 调度锁 */
	private String				jobLock;

	/** 调度开始时间 */
	private Timestamp			jobStartDate;

	/** 调度结束时间 */
	private Timestamp			jobStopDate;

	/** 调度状态 */
	private String				jobStatus;

	/** 调度信息 */
	private String				jobShowMsg;

	/** 调度异常 */
	private String				jobException;
	
	/** 调度运行类型 */
	private String				jobRunningType;
	
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

	public Timestamp getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(Timestamp jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public Timestamp getJobStopDate() {
		return jobStopDate;
	}

	public void setJobStopDate(Timestamp jobStopDate) {
		this.jobStopDate = jobStopDate;
	}

	public String getJobShowMsg() {
		return jobShowMsg;
	}

	public void setJobShowMsg(String jobShowMsg) {
		this.jobShowMsg = jobShowMsg;
	}

	public String getJobException() {
		return jobException;
	}

	public void setJobException(String jobException) {
		this.jobException = jobException;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobRunningType() {
		return jobRunningType;
	}

	public void setJobRunningType(String jobRunningType) {
		this.jobRunningType = jobRunningType;
	}

	public String getJobService() {
		return jobService;
	}

	public void setJobService(String jobService) {
		this.jobService = jobService;
	}

}