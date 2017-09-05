package com.boe.i18n.utils;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import com.boe.common.utils.SpringContextHolder;
import com.boe.sysmgr.utils.UserUtils;
import org.apache.shiro.session.Session;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * @description: 国际化标签类
 * @author: guoq
 * @created: 2016-12-15 17:23:16
 * @version: 0.2
 */
public class MessageTag extends RequestContextAwareTag{  
    /**标签事例 
     * <h:message key='questionnairsurvey' language="${param.language}"/> 
     */  
	
    private static final long serialVersionUID = -7395069023491526881L;  
    private static MessageResource databaseMessageSource = SpringContextHolder.getBean(MessageResource.class);
    private String code;  
    private String language;  
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLanguage() {  
        return language;  
    }  
  
    public void setLanguage(String language) {  
        this.language = language;  
    }  
  
    @Override  
    protected int doStartTagInternal() throws Exception {  
        try {  
            //获得期望得到的内容  
            String msg = resolveMessage();  
            //将获得的内容输出到jsp中标签对应的位置  
            writeMessage(msg);  
            return EVAL_BODY_INCLUDE;
        }  
        catch (NoSuchMessageException ex) {  
            throw new JspTagException(ex.getMessage());  
        }  
    }  
    protected String resolveMessage() throws JspException, NoSuchMessageException {  
        //<span style="color:#ff0000;"></span>//获取spring中缓存国际化资源文件内容的类  
        //若key中包含el表达式，获取解析后的值  
//        String resolvedKey = ExpressionEvaluationUtils.evaluateString("code", this.key, pageContext);  
        String resolvedKey = (String) ExpressionEvaluatorManager.evaluate("value", code.toString(), Object.class, this, pageContext);
        //若language中包含el表达式，获取解析后的值  
//        String resolvedLanguage = (String) ExpressionEvaluatorManager.evaluate("value", language.toString(), Object.class, this, pageContext);
        //String language = "en_US";
        Session session = UserUtils.getSession();
        String language = session.getAttribute("language").toString();
        Locale locale = null;  
        if(language.equals(language) && language!=null){
        	String[] lan = language.split("_");
        	if(lan!=null && lan.length>0 && lan.length<=1){
        		locale = new Locale(lan[0]);
        	}else if(lan!=null && lan.length>1 && lan.length<=2){
        		locale = new Locale(lan[0], lan[1]);
        	}else{
        		locale = Locale.getDefault();
        	}
        }else{
        	locale = Locale.getDefault();
        }
          
        if (resolvedKey != null ) {  
            // <span style="color:#ff0000;">//获取特定语言文件中key对应的value</span> 
        	String msg = databaseMessageSource.resolveCodeWithoutArguments(resolvedKey, locale);
        	return msg; 
        }  
        return resolvedKey;  
    }  
  
    protected void writeMessage(String msg) throws IOException {  
        pageContext.getOut().write(String.valueOf(msg));  
    }  
} 