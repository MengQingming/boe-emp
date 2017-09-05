package com.boe.cfc.ou.entity;

import com.boe.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * NewHope项目 OU 与组织机构 关联表 实体类
 * <p>Description: SscGroupNhOrg </p>
 * <p>Company:T-ark </p>
 * @author:  xt
 * @created: 2017/1/12
 * @version: 1.0
 */
public class SscGroupNhOrg extends DataEntity<SscGroupNhOrg> {

	private static final long serialVersionUID = 1L;

	/** 表ID */
	private Integer id;

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

	/** OU组织ID */
	private Integer orgId;

	/** OU组织名称 */
	private String orgName;

	/** 是否启用 */
	private String status;

	/** 创建日期 */
	private Date creationDate;

	/** 最后更新日期 */
	private Date lastUpdateDate;

	/** Ou单选框 列表*/
	private List<Integer> radioOuList;

	/** 关联 ou信息*/
	private SscGroupNhOu groupNhOu;

	/** OU关联ID */
	private Integer ouId;

	public SscGroupNhOu getGroupNhOu() {
		return groupNhOu;
	}

	public void setGroupNhOu(SscGroupNhOu groupNhOu) {
		this.groupNhOu = groupNhOu;
	}

	public List<Integer> getRadioOuList() {
		return radioOuList;
	}

	public void setRadioOuList(List<Integer> radioOuList) {
		this.radioOuList = radioOuList;
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

	public Integer getOuId() {
		return ouId;
	}

	public void setOuId(Integer ouId) {
		this.ouId = ouId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SscGroupNhOrg{" +
				"id=" + id +
				", companyId=" + companyId +
				", companyNo='" + companyNo + '\'' +
				", companyName='" + companyName + '\'' +
				", groupId=" + groupId +
				", groupNo='" + groupNo + '\'' +
				", groupName='" + groupName + '\'' +
				", orgId=" + orgId +
				", orgName='" + orgName + '\'' +
				", status='" + status + '\'' +
				", creationDate=" + creationDate +
				", lastUpdateDate=" + lastUpdateDate +
				", radioOuList=" + radioOuList +
				", groupNhOu=" + groupNhOu +
				", ouId=" + ouId +
				'}';
	}
}
