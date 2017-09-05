/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.kpi.dao;


import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.modules.kpi.entity.EmpKpi;


/**
 * kpiDAO接口
 * @author YYY
 * @version 2017-08-14
 */
@MyBatisDao
public interface EmpKpiDao extends CrudDao<EmpKpi> {
	
}