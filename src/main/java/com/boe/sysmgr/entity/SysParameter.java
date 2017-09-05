/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.sysmgr.entity;

import java.util.Date;


import com.boe.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 系统参数Entity
 * @author zhou
 * @version 2016-12-12
 */
public class SysParameter extends DataEntity<SysParameter> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String appNo;		// 应用编号
	private Integer companyId;		// 单位ID
	private String companyNo;		// 单位编号
	private String companyName;		// 单位名称
	private String paramGroup;		// 参数分组
	private String paramName;		// 参数名称
	private String paramValue;		// 参数值
	private String paramFormat;		// 参数格式
	private String status;		// 可用状态
	private Date creationDate;		// 创建日期
	private Date lastUpdateDate;		// 最后更新日期
	
	public SysParameter() {
		super();
	}

	public SysParameter(Integer id){
		super(id);
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
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
	
	public String getParamGroup() {
		return paramGroup;
	}

	public void setParamGroup(String paramGroup) {
		this.paramGroup = paramGroup;
	}
	
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	public String getParamFormat() {
		return paramFormat;
	}

	public void setParamFormat(String paramFormat) {
		this.paramFormat = paramFormat;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}