package com.boe.sysmgr.service;

import java.util.Date;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.entity.SysUserToken;
import com.boe.sysmgr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.sysmgr.dao.SysUserTokenDao;

@Service
@Transactional(readOnly = true)
public class SysUserTokenService extends CrudService<SysUserTokenDao, SysUserToken>
{

    @Autowired
    private SysUserTokenDao tokenDao;

    /**
     * 保存token数据
     * @author Prosper
     * @param user
     * @param token
     */
    @Transactional(readOnly = false)
    public void saveToken(User user, String token)
    {
        SysUserToken userToken = new SysUserToken();
        userToken.setCompanyId(user.getCompany().getId());
        userToken.setCompanyNo(user.getCompany().getCompanyNo());
        userToken.setCompanyName(user.getCompany().getCompanyName());
        userToken.setUserId(user.getId());
        userToken.setUserNum(user.getUserNum());
        userToken.setUserName(user.getUserName());
        userToken.setUserFullname(user.getFullName());
        userToken.setCreationDate(new Date());
        userToken.setLastUpdateDate(new Date());
        userToken.setToken(token);

        dao.insert(userToken);
    }
}
