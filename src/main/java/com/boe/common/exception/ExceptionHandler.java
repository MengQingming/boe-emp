package com.boe.common.exception;

import com.boe.common.utils.Exceptions;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.exception.entity.SysException;
import com.boe.sysmgr.exception.service.ExceptionService;
import com.boe.sysmgr.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * 异常处理类,根据从底层向上抛出的异常类型相应转到不同的异常处理页面 
 *
 * @author linjiarong
 * @date 2014年11月2日 下午10:01:52 
 * @version 0.0.1
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

	@Autowired
	private ExceptionService exceptionService;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView returnValue = new ModelAndView();

		User user= UserUtils.getUser();
		String appNo = (String) UserUtils.getSession().getAttribute("appNo");	//appNo

		SysException sysException = new SysException();
		sysException.setLanguage(user.getLang());
		sysException.setAppNo(appNo);
		sysException.setStatus("未处理");

		// 根据不同错误转向不同页面
		if(ex instanceof BusinessException) {				//通用业务异常
			BusinessException be = (BusinessException) ex;
			sysException.setExceptionCode(be.getCode());
			sysException.setExceptionMessage(be.getMessage());
			sysException.setExceptionType("BusinessException");
			returnValue.setViewName("/error/500");

		}else if(ex instanceof ValidationException) {		//参数异常
			ValidationException ve = (ValidationException) ex;
			sysException.setExceptionCode(ve.getCode());
			sysException.setExceptionMessage(ve.getMessage());
			sysException.setExceptionType("ValidationException");
			returnValue.setViewName("/error/400");

		}else if(ex instanceof PermissionException){		//权限异常
			PermissionException pe = (PermissionException) ex;
			sysException.setExceptionCode(pe.getCode());
			sysException.setExceptionMessage(pe.getMessage());
			sysException.setExceptionType("PermissionException");
			returnValue.setViewName("/error/403");

		}else {												//系统其他异常
			sysException.setExceptionCode(ex.getClass().getName());
			sysException.setExceptionMessage(Exceptions.getStackTraceAsString(ex));
			sysException.setExceptionType("OtherException");
			returnValue.setViewName("/error/500");
		}
		if (!sysException.getExceptionType().equals("OtherException")){
			returnValue.addObject("ex", ex);
		}
		// 保存异常日志
		log.error("********** "+sysException.getExceptionType()+" Start********");
		log.error("",ex);
		log.error(ex.getLocalizedMessage(),ex.getMessage());
		log.error("********** "+sysException.getExceptionType()+" End**********");
		exceptionService.saveOrUpdateException(sysException);

		return returnValue;
	}
}
