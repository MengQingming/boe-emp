package com.boe.cfc.ou.entity;

import java.io.Serializable;
import java.util.Date;
import com.boe.common.persistence.DataEntity;

/**
 *
 * null
 *
 */

public class MOrgPa extends DataEntity<MOrgPa> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 单位ID */
	private Integer companyId;

	/** 单位编号 */
	private String companyNo;

	/** 单位名称 */
	private String companyName;

	/** 所属OU组织ID */
	private Integer orgId;

	/** 所属OU组织代码 */
	private String orgCode;

	/** 所属OU组织名称 */
	private String orgName;

	/** 值集ID */
	private Integer valueSetId;

	/** 值集名称 */
	private String valueSetName;

	/** 值代码 */
	private String valueCode;

	/** 值描述 */
	private String valueDesc;

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

	public Integer getValueSetId() {
		return this.valueSetId;
	}

	public void setValueSetId(Integer valueSetId) {
		this.valueSetId = valueSetId;
	}

	public String getValueSetName() {
		return this.valueSetName;
	}

	public void setValueSetName(String valueSetName) {
		this.valueSetName = valueSetName;
	}

	public String getValueCode() {
		return this.valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public String getValueDesc() {
		return this.valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
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

}
