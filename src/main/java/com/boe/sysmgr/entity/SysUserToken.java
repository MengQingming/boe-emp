package com.boe.sysmgr.entity;

import com.boe.common.persistence.DataEntity;

import java.util.Date;

/**
 * 影像平台需要的token
 * 
 * @author Prosper
 * @time 2017年3月21日
 */
public class SysUserToken extends DataEntity<SysUserToken> {


	/**
     * 
     */
    private static final long serialVersionUID = -5248394794063066207L;

    /** TOKEN */
	private String token;

	/** 单位ID */
	private Integer companyId;

	/** 单位编号 */
	private String companyNo;

	/** 单位名称 */
	private String companyName;

	/** 用户ID */
	private Integer userId;

	/** 用户登录名 */
	private String userName;

	/** 员工编号 */
	private String userNum;

	/** 员工名称 */
	private String userFullname;

	/** 开始日期 */
	private Date startDate;

	/** 结束日期 */
	private Date endDate;

	/** 创建日期 */
	private Date creationDate;

	/** 最后更新日期 */
	private Date lastUpdateDate;


	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserFullname() {
		return this.userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
