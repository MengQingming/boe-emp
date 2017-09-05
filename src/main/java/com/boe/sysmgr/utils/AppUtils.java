package com.boe.sysmgr.utils;

import java.util.ArrayList;
import java.util.List;

import com.boe.common.utils.SpringContextHolder;
import com.boe.scheduler.dao.SchedulerJobDao;
import com.boe.scheduler.entity.SchedulerJob;
import com.boe.sysmgr.dao.CompanyDao;
import com.boe.sysmgr.entity.Company;
import com.boe.common.mapper.JsonMapper;
import com.boe.sysmgr.dao.AppDao;
import com.boe.sysmgr.entity.App;

/**
* <p>Description:AppUtils 应用工具类</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:30:23
* @version: 1.0
*/
public class AppUtils {
	
	private static AppDao appDao = SpringContextHolder.getBean(AppDao.class);
	
	private static CompanyDao companyDao = SpringContextHolder.getBean(CompanyDao.class);
	
	private static SchedulerJobDao schedulerJobsDao = SpringContextHolder.getBean(SchedulerJobDao.class);
	
	public static List<App> getAllApp(){
		List<App> apps = new ArrayList<App>();
		apps = appDao.findAllList(new App());
		return apps;
	}
	/**
	 * 查询所有的 公司信息
	 *@return
	 */
	public static List<Company> getAllCompany(){
		List<Company> companys = new ArrayList<Company>();
		companys = companyDao.findAllList(new Company());
		return companys;
	}
	
	/**
	 * 查询所有的 job信息
	 *@return
	 */
	public static List<SchedulerJob> getAllSchedulerJob(){
		List<SchedulerJob> schedulerJobs = new ArrayList<SchedulerJob>();
		schedulerJobs = schedulerJobsDao.findAllList(new SchedulerJob());
		return schedulerJobs;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @return
	 */
	public static String getAppsJson(){
		return JsonMapper.toJsonString(getAllApp());
	}
	
}
