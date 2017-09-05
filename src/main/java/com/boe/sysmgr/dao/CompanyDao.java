package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.Company;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.App;

/**
* <p>Description:CompanyDao 公司dao</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:12:35
* @version: 1.0
*/
@MyBatisDao
public interface CompanyDao extends CrudDao<Company> {
	public App findByAppNo(Company company);
}
