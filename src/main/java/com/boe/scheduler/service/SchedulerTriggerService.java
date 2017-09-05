package com.boe.scheduler.service;

import com.boe.common.service.CrudService;
import com.boe.scheduler.entity.SchedulerTrigger;
import com.boe.scheduler.dao.SchedulerTriggerDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/** 
 * @ClassName: SchedulerTriggerService 
 * @Description: TODO {@link SchedulerTriggerDao}
 * @author WangShengDong
 * @date 2017年3月23日 下午8:30:35 
 */
@Service("xxwSchedulerTriggerService")
@Transactional(readOnly = true)
public class SchedulerTriggerService extends CrudService<SchedulerTriggerDao,SchedulerTrigger> {
	
	public void saveOrUpdate(SchedulerTrigger trigger){
		trigger.setLastUpdateDate(new Date());
		if (trigger.getIsNewRecord()) {
			trigger.setCreationDate(new Date());
		}
		save(trigger);
	}
	
	public List<SchedulerTrigger> findListByJobCode(String jobCode){
		return dao.findListByJobCode(jobCode);
	}
	
}
