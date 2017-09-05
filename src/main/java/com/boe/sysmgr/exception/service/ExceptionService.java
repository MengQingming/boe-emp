package com.boe.sysmgr.exception.service;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.exception.dao.ExceptionDao;
import com.boe.sysmgr.exception.entity.SysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 异常处理 Service
 *
 * @author xt
 * @date 2017年1月4日 10:52:28
 * @version 1.0
 *
 */
@Service
@Transactional(readOnly = true)
public class ExceptionService extends CrudService<ExceptionDao, SysException> {

    @Autowired
    private ExceptionDao exceptionDao;
    /**
     * 保存 异常信息
     * @param sysException
     */
    @Transactional(readOnly = false)
    public void saveOrUpdateException(SysException sysException){
        super.save(sysException);
    }

    /**
     * 更新 异常状态
     * @param sysException
     */
    @Transactional(readOnly = false)
    public void updateExcStatus(SysException sysException){
        exceptionDao.updateExcStatus(sysException);
    }
}
