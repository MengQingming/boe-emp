package com.boe.sysmgr.entity;

import java.util.Date;

import com.boe.common.persistence.DataEntity;

/**
* <p>Description:App APP应用Entity</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:14:36
* @version: 1.0
*/
public class App extends DataEntity<App> {

	private static final long serialVersionUID = 1L;
	//应用编号
	private String appNo;
	//应用名称
	private String appName;
	//应用版本
	private String versionNum;
	//应用状态
	private String status;
	
	
	private Date creationDate;
	private Date lastUpdateDate;
	
	
	
	public App() {
		super();
	}
	
	public App(Integer id){
		super(id);
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
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

	@Override
	public String toString() {
		return "App [appNo=" + appNo + ", appName=" + appName + ", versionNum="
				+ versionNum + ", status=" + status + ", creationDate="
				+ creationDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
}