package com.boe.sysmgr.entity;

import java.util.Date;

import com.boe.common.persistence.DataEntity;

/**
* <p>Description:Dict 数据字典组Entity</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:16:28
* @version: 1.0
*/
public class Dict extends DataEntity<Dict> {

	private static final long serialVersionUID = 1L;
	//应用No
	private String appNo;
	//字典组编码
	private String dictCode;
	//字典组名称
	private String dictName;
	//字典组描述
	private String remarks;
	//语言
	private String language;
	//可用状态
	private String status;
	private Date creationDate;
	private Date lastUpdateDate;
	
	private String isGeneral;
	
	public String getIsGeneral() {
		return isGeneral;
	}

	public void setIsGeneral(String isGeneral) {
		this.isGeneral = isGeneral;
	}

	public Dict() {
		super();
	}
	
	public Dict(Integer id){
		super(id);
		status = "0";
	}
	
	public Dict(String dictCode, String dictName){
		this.dictCode = dictCode;
		this.dictName = dictName;
	}
	
	
	
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}



	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Dict [appNo=" + appNo + ", dictCode=" + dictCode
				+ ", dictName=" + dictName + ", remarks=" + remarks + ", language="
				+ language + ", status=" + status + ", creationDate="
				+ creationDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
}