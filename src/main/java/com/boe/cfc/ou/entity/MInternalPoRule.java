package com.boe.cfc.ou.entity;

import com.boe.common.persistence.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * null
 *
 */

public class MInternalPoRule extends DataEntity<MInternalPoRule> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 订单号 */
	private String poNumber;

	/** 备注 */
	private String remarks;

	/** 创建日期 */
	private Date creationDate;

	/** 最后更新日期 */
	private Date lastUpdateDate;

	/** 数据来源 */
	private String dataSource;

	/** 数据来源业务主键 */
	private String dataSourceKey;

	/** 最后同步日期 */
	private Date lastSyncDate;

	/** 单位ID */
	private Integer companyId;

	/** 单位编号 */
	private String companyNo;

	/** 单位名称 */
	private String companyName;

	/** OU组织ID */
	private Integer orgId;

	/** OU组织代码 */
	private String orgCode;

	/** OU组织名称 */
	private String orgName;

	/** 规则类型 */
	private String ruleType;

	/** 车牌号 */
	private String plateNumber;

	/** 员工号 */
	private String employeeNumber;

	/** 业务小类ID */
	private Integer item2Id;

	/** 业务小类编号 */
	private String item2No;

	/** 业务小类名称 */
	private String item2Name;

	/** 成本中心代码 */
	private String ccCode;

	/** 成本中心名称 */
	private String ccName;


	public String getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceKey() {
		return this.dataSourceKey;
	}

	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

	public Date getLastSyncDate() {
		return this.lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
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

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRuleType() {
		return this.ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getEmployeeNumber() {
		return this.employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Integer getItem2Id() {
		return this.item2Id;
	}

	public void setItem2Id(Integer item2Id) {
		this.item2Id = item2Id;
	}

	public String getItem2No() {
		return this.item2No;
	}

	public void setItem2No(String item2No) {
		this.item2No = item2No;
	}

	public String getItem2Name() {
		return this.item2Name;
	}

	public void setItem2Name(String item2Name) {
		this.item2Name = item2Name;
	}

	public String getCcCode() {
		return this.ccCode;
	}

	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}

	public String getCcName() {
		return this.ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

}
