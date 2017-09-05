package com.boe.scheduler.service;

import com.boe.common.service.CrudService;
import com.boe.scheduler.dao.SchedulerJobDao;
import com.boe.scheduler.dao.SchedulerTriggerDao;
import com.boe.scheduler.entity.SchedulerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/** 
 * @ClassName: SchedulerJobService 
 * @Description: TODO {@link SchedulerJobDao}
 * @author WangShengDong
 * @date 2017年3月23日 下午8:29:05 
 */
@Service("xxwSchedulerJobService")
@Transactional(readOnly = true)
public class SchedulerJobService extends CrudService<SchedulerJobDao,SchedulerJob> {
	
	@Autowired
	private SchedulerTriggerDao triggerDao;
	
	/**
	 * 保存
	 * @param job
	 */
	public void saveOrUpdate(SchedulerJob job){
		job.setLastUpdateDate(new Date());
		if (job.getIsNewRecord()) {
			job.setCreationDate(new Date());
			if ("0".equals(job.getJobStatus())) {
				triggerDao.stopTrigger(job.getId());
			}
		}
		save(job);
	}
	
	/**
	 * 新版调度锁
	 * @param jobCode
	 * @param jobLock
	 * @return
	 */
	public Boolean getByJobCode(SchedulerJob job){
		if (dao.updateJobLock(job) == 1)
			return true;
		return false;
	}
	
	/**
	 * 新的任务结束调度
	 * 2017年4月2日19:51:46
	 * @param job
	 * @return
	 */
	public void jobCompletion(SchedulerJob job){
		dao.updateCompletion(job);
	}
	
}
