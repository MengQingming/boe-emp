package com.boe.cfc.ou.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.boe.common.persistence.DataEntity;

/**
 *
 * null
 *
 */
public class MGroupOrg extends DataEntity<MGroupOrg> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** OU组织名称 */
	private String orgName;

	/** 是否可用 */
	private String status;

	/** 创建日期 */
	private Date creationDate;

	/** 最后更新日期 */
	private Date lastUpdateDate;

	/** 单位ID */
	private Integer companyId;

	/** 单位编号 */
	private String companyNo;

	/** 单位名称 */
	private String companyName;

	/** 组织机构ID */
	private Integer groupId;

	/** 组织机构编号 */
	private String groupNo;

	/** 组织机构名称 */
	private String groupName;

	/** 组织全路径 */
	private String groupPath;

	/** OU组织ID */
	private Integer orgId;

	/** OU组织代码 */
	private String orgCode;
	
	//定义搜索
	private List<Integer> ouList;

	public MGroupOrg(){
		
	}
	
	public MGroupOrg(Integer groupId){
		this.groupId = groupId;
	}
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupPath() {
		return this.groupPath;
	}

	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<Integer> getOuList() {
		return ouList;
	}

	public void setOuList(List<Integer> ouList) {
		this.ouList = ouList;
	}

}
