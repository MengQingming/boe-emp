package com.boe.cfc.ou.entity;

import java.io.Serializable;
import java.util.Date;

import com.boe.common.persistence.DataEntity;
import com.boe.sysmgr.entity.Group;

/**   
 * OU
 * @ClassName:  MOrg   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author baipan 
 * @date 2017年2月7日 下午3:34:27      
 */  
public class MOrg extends DataEntity<MOrg> implements Serializable {

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

	/** 币种 */
	private String currency;
	
	/** 纳税人识别号 */
	private String taxpayerCode;
	
	/** 纳税人名称 */
	private String taxpayerName;
	
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

	/** 是否启动预算 */
	private String budgetFlag;

	/** 事业部代码 */
	private String BusinessLineCode;

	/** 事业部名称 */
	private String businessLineName;
	
	/**
	 * 补充字段：为了在MGroupOrg表中无orgid，orgcode+company不能确定唯一值
	 */
	private Group group;

	public String getBudgetFlag() {
		return budgetFlag;
	}

	public void setBudgetFlag(String budgetFlag) {
		this.budgetFlag = budgetFlag;
	}

	public String getBusinessLineCode() {
		return BusinessLineCode;
	}

	public void setBusinessLineCode(String businessLineCode) {
		BusinessLineCode = businessLineCode;
	}

	public String getBusinessLineName() {
		return businessLineName;
	}

	public void setBusinessLineName(String businessLineName) {
		this.businessLineName = businessLineName;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

    public String getTaxpayerCode()
    {
        return taxpayerCode;
    }

    public void setTaxpayerCode(String taxpayerCode)
    {
        this.taxpayerCode = taxpayerCode;
    }

    public String getTaxpayerName()
    {
        return taxpayerName;
    }

    public void setTaxpayerName(String taxpayerName)
    {
        this.taxpayerName = taxpayerName;
    }

}
