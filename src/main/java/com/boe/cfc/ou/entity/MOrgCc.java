package com.boe.cfc.ou.entity;

import java.io.Serializable;
import java.util.Date;

import com.boe.common.persistence.DataEntity;

/**   
 * 成本中心
 * @ClassName:  MOrgCc   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author baipan 
 * @date 2017年2月7日 下午3:35:24      
 */  
public class MOrgCc extends DataEntity<MOrgCc> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 表ID */
	private Integer id;

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

	/** 成本中心代码 */
	private String ccCode;

	/** 成本中心名称 */
	private String ccName;

	/** 成本中心类型代码 */
	private String ccAttributeCode;

	/** 成本中心类型名称 */
	private String ccAttributeName;

	/** 利润中心标志 */
	private String profitCenterFlag;

	/** 成本中心组 */
	private String ccGroup;

	/** 是否可用 */
	private String status;

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


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCcAttributeCode() {
		return this.ccAttributeCode;
	}

	public void setCcAttributeCode(String ccAttributeCode) {
		this.ccAttributeCode = ccAttributeCode;
	}

	public String getCcAttributeName() {
		return this.ccAttributeName;
	}

	public void setCcAttributeName(String ccAttributeName) {
		this.ccAttributeName = ccAttributeName;
	}

	public String getProfitCenterFlag() {
		return this.profitCenterFlag;
	}

	public void setProfitCenterFlag(String profitCenterFlag) {
		this.profitCenterFlag = profitCenterFlag;
	}

	public String getCcGroup() {
		return this.ccGroup;
	}

	public void setCcGroup(String ccGroup) {
		this.ccGroup = ccGroup;
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
