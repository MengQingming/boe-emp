package com.boe.sysmgr.tag.base;

import com.boe.common.utils.SpringContextHolder;
import com.boe.common.utils.StringUtils;
import com.boe.sysmgr.dao.FormControlDao;
import com.boe.sysmgr.entity.FormControl;

/**
 * 
* <p>Title: FormControlTag</p>
* <p>Description: 表单控制标签处理</p>
* <p>Company: </p>
* @author guoq
* @date 2017-1-10下午1:26:38
 */
public class FormControlTag extends BaseTag{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String getFromControl(){
		//表单控制配置信息
		FormControl form = new FormControl();
		//配置编号
        if(getFormCode()!=null && !getFormCode().equals("")) {
            form.setFormCode(getFormCode());
            //配置名称
            if (StringUtils.isNotBlank(getConfigName())) {
                form.setConfigName(getConfigName());
            }
            //业务类型
            if (StringUtils.isNotBlank(getBusinessItem1())) {
                form.setBusinessItem1(getBusinessItem1());
            }
            if (StringUtils.isNotBlank(getBusinessItem2())) {
                form.setBusinessItem2(getBusinessItem2());
            }
            if (StringUtils.isNotBlank(getBusinessItem3())) {
                form.setBusinessItem3(getBusinessItem3());
            }
            if (StringUtils.isNotBlank(getBusinessItem4())) {
                form.setBusinessItem4(getBusinessItem4());
            }
            if (StringUtils.isNotBlank(getBusinessItem5())) {
                form.setBusinessItem5(getBusinessItem5());
            }
            FormControlDao formControlDao = SpringContextHolder.getBean(FormControlDao.class);
            form = formControlDao.getFormControlByName(form);
            String tag = renderDisplay(form.getConfigContent());
//		    "<input id=\""+getConfigName()+"\""+"name=\"formControlName\""+" value=\""+form.getConfigContent()+"\""+" style='display: none'"+"\"/>"
            return tag;
		}else{
			return "";
		}
	}

	private String renderDisplay(String configContent) {
		StringBuffer handlers = new StringBuffer();
		prepareValue(handlers, "<input ");
		prepareIdAttribute(handlers, getFormCode());
		prepareNameAttribute(handlers, "formControlName");
		prepareValueAttribute(handlers, configContent);
		prepareStyleAttribute(handlers, "display: none");
		prepareValue(handlers, "/>");
		System.out.println(handlers.toString());
		return handlers.toString();
	}
}
