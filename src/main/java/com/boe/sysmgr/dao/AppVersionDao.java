package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.AppVersion;

/**
* <p>Description:AppVersionDao 应用版本DAO</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:11:41
* @version: 1.0
*/
@com.boe.common.persistence.annotation.MyBatisDao
public interface AppVersionDao extends CrudDao<AppVersion> {
}
