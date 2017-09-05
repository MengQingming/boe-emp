package com.boe.sysmgr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.dao.AppDao;
import com.boe.sysmgr.entity.App;


/**
* <p>Description:AppService 应用Service</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:18:03
* @version: 1.0
*/
@Service
@Transactional(readOnly = true)
public class AppService extends CrudService<AppDao, App> {
	
	/**
	 * 保存或者修改App应用
	 *@param app
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateApp(App app) {
		super.save(app);
	}
	/**
	 * 删除App应用
	 *@param app
	 */
	@Transactional(readOnly = false)
	public void deleteApp(App app) {
		super.delete(app);
	}
	/**
	 * 根据appNo查询对应的App应用
	 *@param app
	 *@return App
	 */
	public App findByAppNo(App app){
		return dao.findByAppNo(app);
	}
}
