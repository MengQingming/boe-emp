package com.boe.sysmgr.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import com.bizenit.idm.siam.client.util.AbstractCasFilter;
import com.bizenit.idm.siam.client.validation.Assertion;

/**
 * 自定义的用户是否登录判断，通过单点登录判断
 * @author Prosper
 * @time 2017年1月16日
 */
public class TarkUserFilter extends UserFilter
{

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            
            /**
             * 如果没有登录用户，判断单点登录有没有用户
             */
            if (subject.getPrincipal() == null)
            {
                ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest)request;
                
                // 从容器的request中去取
                HttpServletRequest httpRequest = (HttpServletRequest) shiroRequest.getRequest();
                
                Assertion assertion = (Assertion)httpRequest.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
                if (assertion == null)
                {
                    HttpSession session = httpRequest.getSession();
                    assertion = (Assertion)session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
                }
                
                if (assertion == null)
                    return false;
                
                UsernamePasswordToken token = new UsernamePasswordToken("88001044", new char[0], false, "", "", false);
                subject.login(token);
            }
            
            return subject.getPrincipal() != null;
        }
    }
    
}
