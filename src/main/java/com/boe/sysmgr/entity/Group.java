package com.boe.sysmgr.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.boe.common.persistence.TreeEntity;


/**
* <p>Description:Group 组织机构Entity</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:17:16
* @version: 1.0
*/
public class Group extends TreeEntity<Group> {
	private static final long serialVersionUID = 1L;
    //组织机构编号
	private String groupNo;
	//组织机构名称
	private String groupName;
	//组织机构简称
	private String groupShortName;
	//显示顺序
	private Integer displayOrder;
	//备注
	private String remarks;
	//组织全路径
	private String groupPath;
	//组织机构层次
	private Integer groupLayer;
	//组织机构类型
	private String groupType;
	//组织机构区域代码
	private Area area;
	//是否有子节点
	private String hasChild;
	//机构状态
	private String status;
	//开始日期
	private Date startDate;
	//结束日期
	private Date endDate;
	
	/*//删除标志
	private String delFlag;*/
	//属性1....
	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	private String attribute6;

	private String attribute7;

	private String attribute8;

	private String attribute9;
	//属性10
	private String attribute10;
	//数据来源
	private String dataSource;
	//数据来源业务主键
	private String dataSourceKey;
	//数据最后同步日期
	private Date lastSyncDate;
	//创建日期
	private Date creationDate;
	//最后更新日期
	private Date lastUpdateDate;
	//公司id
	private Integer companyId;
	//公司id
	private String companyNo;
	//公司id
	private String companyName;
	//成本中心组
	private String ccGroup;
	//业务线代码
	private String businessLineCode;
	//业务线名称
	private String businessLineName;
	
	/** 分管领导ID */
	private Integer directorId;

	/** 分管领导编号 */
	private String directorNum;

	/** 分管领导名称 */
	private String directorName;
	
	/** 组织机构负责人ID */
	private Integer managerId;

	/** 组织机构负责人编号 */
	private String managerNum;

	/** 组织机构负责人名称 */
	private String managerName;

	/** 部门属性 */
	private String deptAttribute;

	public String getDeptAttribute() {
		return deptAttribute;
	}

	public void setDeptAttribute(String deptAttribute) {
		this.deptAttribute = deptAttribute;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerNum() {
		return managerNum;
	}

	public void setManagerNum(String managerNum) {
		this.managerNum = managerNum;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	//临时存储用户和机构的关系信息
	private String relationType;
	//临时存放上级领导信息
	private Integer leaderId;
	
	private String leaderName;
	
	private String leaderNum;
	
	//临时存放
	private Boolean selected;
	
	public Group(){
		super();
		this.status = "1";
		this.delFlag = "1";
	}
	
	public Group(Integer id){
		super(id);
	}
	
	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	
	public String getBusinessLineCode() {
		return businessLineCode;
	}

	public void setBusinessLineCode(String businessLineCode) {
		this.businessLineCode = businessLineCode;
	}

	public String getBusinessLineName() {
		return businessLineName;
	}

	public void setBusinessLineName(String businessLineName) {
		this.businessLineName = businessLineName;
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

	public String getGroupShortName() {
		return groupShortName;
	}

	public void setGroupShortName(String groupShortName) {
		this.groupShortName = groupShortName;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getGroupPath() {
		return groupPath;
	}

	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}

	public Integer getGroupLayer() {
		return groupLayer;
	}

	public void setGroupLayer(Integer groupLayer) {
		this.groupLayer = groupLayer;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	@NotNull
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

	public Date getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public Integer getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderNum() {
		return leaderNum;
	}

	public void setLeaderNum(String leaderNum) {
		this.leaderNum = leaderNum;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	

	public String getCcGroup() {
		return ccGroup;
	}

	public void setCcGroup(String ccGroup) {
		this.ccGroup = ccGroup;
	}

	public Integer getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}

	public String getDirectorNum() {
		return directorNum;
	}

	public void setDirectorNum(String directorNum) {
		this.directorNum = directorNum;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	@Override
	public String toString() {
		return "Group [groupNo=" + groupNo + ", groupName=" + groupName
				+ ", groupShortName=" + groupShortName + ", displayOrder="
				+ displayOrder + ", remarks=" + remarks + ", groupPath="
				+ groupPath + ", groupLayer=" + groupLayer + ", groupType="
				+ groupType + ", area=" + area + ", hasChild=" + hasChild
				+ ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", delFlag=" + delFlag
				+ ", attribute1=" + attribute1 + ", attribute2=" + attribute2
				+ ", attribute3=" + attribute3 + ", attribute4=" + attribute4
				+ ", attribute5=" + attribute5 + ", attribute6=" + attribute6
				+ ", attribute7=" + attribute7 + ", attribute8=" + attribute8
				+ ", attribute9=" + attribute9 + ", attribute10=" + attribute10
				+ ", dataSource=" + dataSource + ", dataSourceKey="
				+ dataSourceKey + ", lastSyncDate=" + lastSyncDate
				+ ", creationDate=" + creationDate + ", lastUpdateDate="
				+ lastUpdateDate + "]";
	}
}