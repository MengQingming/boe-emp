package com.boe.sysmgr.exception.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.exception.entity.SysException;


/**
 *
 * 异常处理 Dao
 *
 * @author xt
 * @date 2017年1月4日 10:52:28
 * @version 0.1
 *
 */
@MyBatisDao
public interface ExceptionDao extends CrudDao<SysException> {
    void updateExcStatus(SysException e);
}
