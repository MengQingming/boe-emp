package com.boe.sysmgr.tag.base;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 
* <p>Title: BaseTag</p>
* <p>Description: 表单控制标签实体</p>
* <p>Company: </p>
* @author guoq
* @date 2017-1-10下午1:26:06
 */
public class BaseTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	//配置名称
	private String configName;
	//配置编号
	private String formCode;
	//公司id
	private Integer companyId;
	//业务类型1
	private String businessItem1;
	//业务类型2
	private String businessItem2;
	//业务类型3
	private String businessItem3;
	//业务类型4
	private String businessItem4;
	//业务类型5
	private String businessItem5;
	
	private String style;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getBusinessItem1() {
		return businessItem1;
	}

	public void setBusinessItem1(String businessItem1) {
		this.businessItem1 = businessItem1;
	}

	public String getBusinessItem2() {
		return businessItem2;
	}

	public void setBusinessItem2(String businessItem2) {
		this.businessItem2 = businessItem2;
	}

	public String getBusinessItem3() {
		return businessItem3;
	}

	public void setBusinessItem3(String businessItem3) {
		this.businessItem3 = businessItem3;
	}

	public String getBusinessItem4() {
		return businessItem4;
	}

	public void setBusinessItem4(String businessItem4) {
		this.businessItem4 = businessItem4;
	}

	public String getBusinessItem5() {
		return businessItem5;
	}

	public void setBusinessItem5(String businessItem5) {
		this.businessItem5 = businessItem5;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	protected void prepareValue(StringBuffer handlers, String value){
		handlers.append(value);
	}
	
	protected void prepareAttribute(StringBuffer handlers, String name, Object value){
		if (value != null) {
			handlers.append(" ");
			handlers.append(name);
			handlers.append("=\"");
			handlers.append(value);
			handlers.append("\"");
		}
	}
	
	protected void prepareIdAttribute(StringBuffer handlers, Object value){
		prepareAttribute(handlers, "id", value);
	}
	
	protected void prepareNameAttribute(StringBuffer handlers, Object value){
		prepareAttribute(handlers, "name", value);
	}
	
	protected void prepareStyleAttribute(StringBuffer handlers, Object value){
		prepareAttribute(handlers, "style", value);
	}
	protected void prepareValueAttribute(StringBuffer handlers, Object value){
		prepareAttribute(handlers, "value", value);
	}
}
