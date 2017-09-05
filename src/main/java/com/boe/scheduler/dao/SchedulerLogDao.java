package com.boe.scheduler.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.scheduler.entity.SchedulerLog;

/**
 * @ClassName: SchedulerLogDao 
 * @Description: TODO {@link SchedulerLog}
 * @author WangShengDong
 * @date 2017年3月23日 下午8:26:09
 */
@com.boe.common.persistence.annotation.MyBatisDao("chedulerLogDao")
public interface SchedulerLogDao extends CrudDao<SchedulerLog> {
	
}
