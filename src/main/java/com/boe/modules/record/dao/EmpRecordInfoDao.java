/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.record.dao;


import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.modules.record.entity.EmpRecordInfo;

/**
 * recordDAO接口
 * @author YYY
 * @version 2017-08-16
 */
@MyBatisDao
public interface EmpRecordInfoDao extends CrudDao<EmpRecordInfo> {
	
}