package com.boe.sysmgr.entity;

import com.boe.common.persistence.DataEntity;

/**
 * @description：表单控制
 * @author：guoq
 * @created：2016-12-20 17:02:12
 * @version 1.0
 */
public class FormControl extends DataEntity<FormControl> {

	private static final long serialVersionUID = 1L;

	//应用No
	private String appNo;
	//表单代码
	private String formCode;
	//公司id
	private Integer companyId;
	//公司No
	private String companyNo;
	//公司名称
	private String companyName;
	//配置名称
	private String configName;
	//配置描述
	private String configDesp;
	//配置内容
	private String configContent;
	//实例配置
	private String configDemo;
	//是否启用
	private String status;
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
	//应用名称
	private String appName;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
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
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigDesp() {
		return configDesp;
	}
	public void setConfigDesp(String configDesp) {
		this.configDesp = configDesp;
	}
	public String getConfigContent() {
		return configContent;
	}
	public void setConfigContent(String configContent) {
		this.configContent = configContent;
	}
	public String getConfigDemo() {
		return configDemo;
	}
	public void setConfigDemo(String configDemo) {
		this.configDemo = configDemo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
}
