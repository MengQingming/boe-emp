/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.demand.dao;


import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.modules.demand.entity.EmpDemandCommon;

/**
 * demandDAO接口
 * @author YYY
 * @version 2017-08-16
 */
@MyBatisDao
public interface EmpDemandCommonDao extends CrudDao<EmpDemandCommon> {
	
	public void deleteBatch(String [] ids) ;
	
}