package com.boe.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一异常 处理 (fssc项目中不使用, 异常处理 使用 ExceptionHandler )
 *
 */
public class AnnotationMethodExceptionHandler extends ExceptionHandlerExceptionResolver {  
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
	                 
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {  
		StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw =  new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            exception.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        log.error(sw.toString());
		if (handlerMethod == null) {  
            return null;  
        }  
        Method method = handlerMethod.getMethod();  
        if (method == null) {  
            return null;  
        }  
        
        //如果定义了ExceptionHandler则返回相应的Map中的数据  
        ModelAndView returnValue = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);  
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);  
        if (responseBodyAnn != null) {  
            try {  
                ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(method, ResponseStatus.class);  
                if (responseStatusAnn != null) {  
                    HttpStatus responseStatus = responseStatusAnn.value();  
                    String reason = responseStatusAnn.reason();  
                    if (!StringUtils.hasText(reason)) {  
                        response.setStatus(responseStatus.value());  
                    } else {  
                        try {  
                            response.sendError(responseStatus.value(), reason);  
                        } catch (IOException e) { }  
                    }  
                }
                if("java.lang.Boolean".equals(method.getReturnType().getName())){
                	return handleResponseBoolean(method,request, response);  
                }
                // 如果没有ExceptionHandler注解那么returnValue就为空  
                if (returnValue == null) {  
                	Map<String, Object> model = new HashMap<String, Object>();
            		model.put("msg", "操作异常:"+exception.getClass().getName());
            		model.put("success", false);
                    handleResponseError(model, request, response);  
                    return new ModelAndView();  
                }  
                returnValue.addObject("msg", "操作异常:"+exception.getClass().getName());
                returnValue.addObject("success", false);
                return handleResponseBody(returnValue, request, response);  
            } catch (Exception e) {  
                return null;  
            }  
        }  
  
        if( null == returnValue) {  
            returnValue = new ModelAndView();  
        }
        returnValue.addObject("msg", sw.toString());
        if(exception instanceof org.apache.shiro.authz.UnauthorizedException){
        	returnValue.setViewName("error/pageAccessRestricted");
        	log.warn("[没有权限]",sw.toString());
        	return returnValue;
        }
        returnValue.setViewName("error/pageException");  
        return returnValue;  
    }  
  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private ModelAndView handleResponseBoolean(Method method,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
    	 HttpInputMessage inputMessage = new ServletServerHttpRequest(request);  
         List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();  
         if (acceptedMediaTypes.isEmpty()) {  
             acceptedMediaTypes = Collections.singletonList(MediaType.ALL);  
         }  
         MediaType.sortByQualityValue(acceptedMediaTypes);  
         HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);  
         Class<?> returnValueType = method.getReturnType();  
         List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();  
         if (messageConverters != null) {  
             for (MediaType acceptedMediaType : acceptedMediaTypes) {  
                 for (HttpMessageConverter messageConverter : messageConverters) {  
                     if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {  
                         messageConverter.write(false, acceptedMediaType, outputMessage);  
                         return new ModelAndView();  
                     }  
                 }  
             }  
         }  
         if (logger.isWarnEnabled()) {  
             logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);  
         }  
         return null;  
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })  
    private ModelAndView handleResponseBody(ModelAndView returnValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Map value = returnValue.getModelMap();  
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);  
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();  
        if (acceptedMediaTypes.isEmpty()) {  
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);  
        }  
        MediaType.sortByQualityValue(acceptedMediaTypes);  
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);  
        Class<?> returnValueType = value.getClass();  
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();  
        if (messageConverters != null) {  
            for (MediaType acceptedMediaType : acceptedMediaTypes) {  
                for (HttpMessageConverter messageConverter : messageConverters) {  
                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {  
                        messageConverter.write(value, acceptedMediaType, outputMessage);  
                        return new ModelAndView();  
                    }  
                }  
            }  
        }  
        if (logger.isWarnEnabled()) {  
            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);  
        }  
        return null;  
    }  
   
	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })  
    private ModelAndView handleResponseError(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);  
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();  
        if (acceptedMediaTypes.isEmpty()) {  
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);  
        }  
        MediaType.sortByQualityValue(acceptedMediaTypes);  
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);  
        Class<?> returnValueType = model.getClass();  
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();  
        if (messageConverters != null) {  
            for (MediaType acceptedMediaType : acceptedMediaTypes) {  
                for (HttpMessageConverter messageConverter : messageConverters) {  
                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {  
                        messageConverter.write(model, acceptedMediaType, outputMessage);  
                        return new ModelAndView();  
                    }  
                }  
            }  
        }  
        if (logger.isWarnEnabled()) {  
            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);  
        }  
        return null;  
    } 
  
}  
