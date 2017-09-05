package com.boe.sysmgr.listener;

import javax.servlet.ServletContext;

import com.boe.sysmgr.service.SystemService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;


public class WebContextListener extends ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
