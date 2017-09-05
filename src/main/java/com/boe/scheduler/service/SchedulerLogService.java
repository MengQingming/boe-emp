package com.boe.scheduler.service;

import com.boe.common.service.CrudService;
import com.boe.scheduler.dao.SchedulerLogDao;
import com.boe.scheduler.entity.SchedulerLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @ClassName: SchedulerLogService 
 * @Description: TODO {@link SchedulerLogDao}
 * @author WangShengDong
 * @date 2017年3月23日 下午8:30:05 
 */
@Service("xxwSchedulerLogService")
@Transactional(readOnly = true)
public class SchedulerLogService extends CrudService<SchedulerLogDao,SchedulerLog> {
	
	public void testTask(){
		System.out.println("testTask");
	}
	
}
