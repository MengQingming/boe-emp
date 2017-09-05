package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.Log;


/**
* <p>Description:LogDao 日志dao</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:13:56
* @version: 1.0
*/
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
