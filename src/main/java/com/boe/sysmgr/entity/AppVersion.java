package com.boe.sysmgr.entity;

import java.util.Date;

import com.boe.common.persistence.DataEntity;

/**
* <p>Description:AppVersion APP应用版本信息Entity</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:15:09
* @version: 1.0
*/
public class AppVersion extends DataEntity<AppVersion> {

	private static final long serialVersionUID = 1L;
	//应用编号
	private String appNo;
	//版本号
	private String versionNum;
	//版本名称
	private String versionName;
	//版本说明
	private String remarks;
	//版本日期
	private Date versionDate;
	
	private Date creationDate;
	private Date lastUpdateDate;
	public AppVersion() {
		super();
	}
	
	public AppVersion(Integer id){
		super(id);
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
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

	@Override
	public String toString() {
		return "AppVersion [appNo=" + appNo + ", versionNum=" + versionNum
				+ ", versionName=" + versionName + ", remarks=" + remarks
				+ ", versionDate=" + versionDate + ", creationDate="
				+ creationDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
}