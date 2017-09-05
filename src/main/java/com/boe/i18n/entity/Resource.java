package com.boe.i18n.entity;

import java.io.Serializable;

import com.boe.common.persistence.DataEntity;

public class Resource extends DataEntity<Resource> implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	//公司id
    private Integer companyId;
    //公司编号
    private String companyNo;
    //公司名称
    private String companyName;
    //app编号
    private String appNo;
    //语言
    private String language;
    //类型
    private String type;
    //国际化编码key
    private String code;
    //国际化编码对应的值
    private String value;
    //备注
    private String remarks;
    
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
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
}
