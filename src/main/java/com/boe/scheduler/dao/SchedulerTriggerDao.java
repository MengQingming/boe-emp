package com.boe.scheduler.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.scheduler.entity.SchedulerTrigger;

import java.util.List;

/** 
 * @ClassName: SchedulerTriggerDao 
 * @Description: TODO {@link SchedulerTrigger}
 * @author WangShengDong
 * @date 2017年3月23日 下午8:25:24 
 */
@com.boe.common.persistence.annotation.MyBatisDao("chedulerTriggerDao")
public interface SchedulerTriggerDao extends CrudDao<SchedulerTrigger> {
	
	/**
	 * 通过jobCode获取触发器
	 * @param jobCode
	 * @return
	 */
	List<SchedulerTrigger> findListByJobCode(String jobCode);
	
	/**
	 * 禁用当前任务全部触发器
	 * @param jobId
	 */
	void stopTrigger(Integer jobId);
	
}
