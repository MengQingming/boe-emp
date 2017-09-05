package com.boe.gen.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {
	
	public void deleteByGenTableId(Integer genTableId);
}
