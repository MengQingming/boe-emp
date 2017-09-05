package com.boe.sysmgr.tag.control;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.boe.sysmgr.tag.base.FormControlTag;

/**
 * 
* <p>Title: ContainerTag</p>
* <p>Description: </p>
* <p>Company: </p>
* @author guoq
* @date 2017-1-10下午1:27:22
 */
public class ContainerTag extends FormControlTag{
	
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException{
		
		try {
			String printTag = getFromControl();
			pageContext.getOut().print(printTag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
}
