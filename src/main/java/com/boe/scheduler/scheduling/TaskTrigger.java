/**
 * 
 */
package com.boe.scheduler.scheduling;

import org.springframework.scheduling.Trigger;

/** 
 * @ClassName: TaskTrigger 
 * @Description: TODO 
 * @author WangShengDong
 * @date 2017年3月28日 下午8:43:39 
 */
public abstract class TaskTrigger implements Trigger {
	
	private String jobCode;
	
	public TaskTrigger(String jobCode) {
		this.jobCode = jobCode;	
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
}
