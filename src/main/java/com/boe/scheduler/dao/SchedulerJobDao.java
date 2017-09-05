package com.boe.scheduler.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.scheduler.entity.SchedulerJob;
import com.boe.common.persistence.annotation.MyBatisDao;

/**
 * @ClassName: SchedulerJobDao 
 * @Description: TODO {@link SchedulerJob}
 * @author WangShengDong
 * @date 2017年3月23日 下午8:26:31
 */
@MyBatisDao("schedulerJobDao")
public interface SchedulerJobDao extends CrudDao<SchedulerJob> {
	
	/**
	 * 乐观锁操作
	 * @param job
	 * @return
	 */
	Integer updateJobLock(SchedulerJob job);
	
	/**
	 * 结束任务，释放任务锁
	 * @param job
	 */
	void updateCompletion(SchedulerJob job);
	
}
