package com.boe.sysmgr.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.boe.common.utils.DateUtils;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class FreeMarkertUtil {
	public static void analysisTemplate(String templatePath,String templateName, String fileName, Map<?,?> root) {  
        try {
            //初使化FreeMarker配置   
            Configuration config = new Configuration();   
            // 设置要解析的模板所在的目录，并加载模板文件   
            config.setDirectoryForTemplateLoading(new File(templatePath));
            // 设置包装器，并将对象包装为数据模型   
            config.setObjectWrapper(new DefaultObjectWrapper());   
  
            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致   
            // 否则会出现乱码   
            Template template = config.getTemplate(templateName, "UTF-8");   
            // 合并数据模型与模板   
            FileOutputStream fos = new FileOutputStream(fileName);   
            Writer out = new OutputStreamWriter(fos, "UTF-8");   
            template.process(root, out);   
            out.flush();   
            out.close();   
        } catch (IOException e) {
            e.printStackTrace();   
        } catch (TemplateException e) {
            e.printStackTrace();   
        }   
    }
	
	/**
	 * 将模板转成字符串
	 * @param templateContent
	 * @param root
	 * @return
	 */
	public static String getStr(String templateContent,Map<String, Object> root){
		String re = "";
		Configuration cfg = new Configuration();  
        StringTemplateLoader stringLoader = new StringTemplateLoader();  
        stringLoader.putTemplate("myTemplate",templateContent);  
        cfg.setTemplateLoader(stringLoader);
        try {  
            Template template = cfg.getTemplate("myTemplate","utf-8");  
            StringWriter writer = new StringWriter();
            template.process(root, writer);  
            re = writer.toString();
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
		return re;
	}
	
	/**
	 * 将实体转成MAP
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> transBean2Map(Object obj) {  
	    if (obj == null) {  
	        return null;  
	    }  
	    Map<String, Object> map = new HashMap<String, Object>();  
	    try {  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        for(int i=0;i<propertyDescriptors.length;i++){
	        	PropertyDescriptor property = propertyDescriptors[i];
	        	String key = property.getName();
	            // 过滤class属性  
	            if (!key.equals("class")) {  
	                // 得到property对应的getter方法  
	                Method getter = property.getReadMethod();
	                Object value = getter.invoke(obj);
	                if(null!=value && Date.class.equals(value.getClass())){
						value = DateUtils.formatDate((Date) value);
					}
	                map.put(key, value); 
	            }
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }  
	    return map;  
	  
	} 
}
