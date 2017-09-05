/**
 * 
 */
package com.boe.scheduler.scheduling;

/**
 * @ClassName: TaskRunnable
 * @Description: TODO
 * @author WangShengDong
 * @date 2017年3月28日 下午8:44:58
 */
public abstract class TaskRunnable implements Runnable {

	private String	jobCode;

	public TaskRunnable(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

}
