package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.SysUserToken;

import java.util.List;

/**
 * OCR TOKEN DAO
 * @author Prosper
 * @time 2017年1月24日
 */
@com.boe.common.persistence.annotation.MyBatisDao
public interface SysUserTokenDao extends CrudDao<SysUserToken>
{
    /**
     * 根据user_num和token查询记录
     * @author Prosper
     * @param userNum
     * @param token
     * @return
     */
    public List<SysUserToken> getByUserNumAndToken(String userNum, String token);
}
