package com.boe.sysmgr.security;

import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizenit.idm.siam.client.util.AbstractCasFilter;

/**
 * 自定义的登出过滤器，含单点登录的登出
 * 
 * @author Prosper
 * @time 2017年1月16日
 */
public class TarkLogoutFilter extends LogoutFilter
{
    private static final Logger log = LoggerFactory.getLogger(TarkLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        Subject subject = getSubject(request, response);
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        
        /**
         * 删除单点登录写在session中的东西
         */
        ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest)request;
        HttpServletRequest httpRequest = (HttpServletRequest) shiroRequest.getRequest();
        HttpSession session = httpRequest.getSession();
        session.removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        
        /**
         *  组装登出的url
         */
        String logoutUrlPrefix = request.getServletContext().getInitParameter("logoutUrlPrefix");
        String logoutServerName = request.getServletContext().getInitParameter("logoutServerName");
        String logoutUrl = logoutUrlPrefix + "?service=" + URLEncoder.encode(logoutServerName, "UTF-8");
        log.debug("logoutUrl:" + logoutUrl);
        /**
         *  跳转到登出界面
         */
        ShiroHttpServletResponse shiroResponse = (ShiroHttpServletResponse)response;
        shiroResponse.sendRedirect(logoutUrl);
        
        return false;
    }
    
}
