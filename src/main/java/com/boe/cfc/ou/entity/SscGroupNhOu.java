package com.boe.cfc.ou.entity;

import com.boe.common.persistence.DataEntity;

/**
 *  NewHope项目 OU信息 实体类
 * <p>Description: SscGroupNhOu </p>
 * <p>Company:T-ark </p>
 * @author:  xt
 * @created: 2017-1-12 11:52:02
 * @version: 1.0
 */
public class SscGroupNhOu extends DataEntity<SscGroupNhOu> {

	private static final long serialVersionUID = 1L;

	/** 表ID */
	private Integer id;

	/** OU名称 */
	private String ouName;

	/** 本位币 */
	private String functionalCurrency;

	/** ORG_ID */
	private Integer orgId;

	@Override
	public String toString() {
		return "SscGroupNhOu{" +
				"id=" + id +
				", ouName='" + ouName + '\'' +
				", functionalCurrency='" + functionalCurrency + '\'' +
				", orgId=" + orgId +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOuName() {
		return ouName;
	}

	public void setOuName(String ouName) {
		this.ouName = ouName;
	}

	public String getFunctionalCurrency() {
		return functionalCurrency;
	}

	public void setFunctionalCurrency(String functionalCurrency) {
		this.functionalCurrency = functionalCurrency;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
}
