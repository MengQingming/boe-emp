package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.SysMessage;

@MyBatisDao
public interface SysMessageDao extends CrudDao<SysMessage> {

}
