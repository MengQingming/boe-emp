package com.boe.sysmgr.service;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.entity.AppVersion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.sysmgr.dao.AppVersionDao;

/**
* <p>Description:AppVersionService App版本应用Service</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:20:12
* @version: 1.0
*/
@Service
@Transactional(readOnly = true)
public class AppVersionService extends CrudService<AppVersionDao, AppVersion> {
	
	/**
	 * 保存或修改App版本应用
	 *@param appVersion
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateAppVersion(AppVersion appVersion) {
		super.save(appVersion);
	}
	/**
	 * 删除App应用
	 *@param appVersion
	 */
	@Transactional(readOnly = false)
	public void deleteAppVersion(AppVersion appVersion) {
		super.delete(appVersion);
	}

}
