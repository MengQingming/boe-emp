package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.App;


/**
* <p>Description:AppDao 应用dao</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:08:44
* @version: 1.0
*/
@com.boe.common.persistence.annotation.MyBatisDao
public interface AppDao extends CrudDao<App> {
	/**
	 *@param app
	 *@return APP
	 */
	public App findByAppNo(App app);
}
