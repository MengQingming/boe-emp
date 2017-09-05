/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.record.entity;

import com.boe.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * recordEntity
 * @author YYY
 * @version 2017-08-16
 */
public class EmpRecordInfo extends DataEntity<EmpRecordInfo> {
	
	private static final long serialVersionUID = 1L;
	private Long projectId;		// 项目ID
	private String recordName;		// 备案名称
	private Long includeDemand;		// 包含需求
	private String responsibilityDept;		// 主责部门
	private String responsibilityCenter;		// 备案责任中心
	private String recordDept;		// 备案部门
	private String responsibilityPerson;		// 备案负责人
	private String recordTpye;		// 备案类别
	private String projectType;		// 项目类别
	private String recordSummary;		// 备案简要说明
	private String implementDept;		// 协同实施部门
	private String implementOrgRange;		// 项目实施组织范围
	private String implementFunctionRange;		// 项目实施功能范围
	private String budgetOrg;		// 项目预算承担主体
	private Double projectCostBudget;		// 项目费用预算
	private Double projectInvestBudget;		// 项目投资预算
	private Double planTime;		// 项目计划开展时间
	private String recordCode;		// 备案编号
	private String status;		// 状态编码
	private String statusName;		// 状态名
	private String yn;		// 是否可用
	private Date creationDate;		// 创建日期
	private String createdBy;		// 创建人
	private Date lastUpdateDate;		// 最后更新日期
	private String lastUpdatedBy;		// 最后更新人
	
	public EmpRecordInfo() {
		super();
	}

	public EmpRecordInfo(Integer id){
		super(id);
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=0, max=100, message="备案名称长度必须介于 0 和 100 之间")
	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	
	public Long getIncludeDemand() {
		return includeDemand;
	}

	public void setIncludeDemand(Long includeDemand) {
		this.includeDemand = includeDemand;
	}
	
	@Length(min=0, max=100, message="主责部门长度必须介于 0 和 100 之间")
	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}
	
	@Length(min=0, max=100, message="备案责任中心长度必须介于 0 和 100 之间")
	public String getResponsibilityCenter() {
		return responsibilityCenter;
	}

	public void setResponsibilityCenter(String responsibilityCenter) {
		this.responsibilityCenter = responsibilityCenter;
	}
	
	@Length(min=0, max=100, message="备案部门长度必须介于 0 和 100 之间")
	public String getRecordDept() {
		return recordDept;
	}

	public void setRecordDept(String recordDept) {
		this.recordDept = recordDept;
	}
	
	@Length(min=0, max=50, message="备案负责人长度必须介于 0 和 50 之间")
	public String getResponsibilityPerson() {
		return responsibilityPerson;
	}

	public void setResponsibilityPerson(String responsibilityPerson) {
		this.responsibilityPerson = responsibilityPerson;
	}
	
	@Length(min=0, max=30, message="备案类别长度必须介于 0 和 30 之间")
	public String getRecordTpye() {
		return recordTpye;
	}

	public void setRecordTpye(String recordTpye) {
		this.recordTpye = recordTpye;
	}
	
	@Length(min=0, max=30, message="项目类别长度必须介于 0 和 30 之间")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Length(min=0, max=1000, message="备案简要说明长度必须介于 0 和 1000 之间")
	public String getRecordSummary() {
		return recordSummary;
	}

	public void setRecordSummary(String recordSummary) {
		this.recordSummary = recordSummary;
	}
	
	@Length(min=0, max=100, message="协同实施部门长度必须介于 0 和 100 之间")
	public String getImplementDept() {
		return implementDept;
	}

	public void setImplementDept(String implementDept) {
		this.implementDept = implementDept;
	}
	
	@Length(min=0, max=100, message="项目实施组织范围长度必须介于 0 和 100 之间")
	public String getImplementOrgRange() {
		return implementOrgRange;
	}

	public void setImplementOrgRange(String implementOrgRange) {
		this.implementOrgRange = implementOrgRange;
	}
	
	@Length(min=0, max=100, message="项目实施功能范围长度必须介于 0 和 100 之间")
	public String getImplementFunctionRange() {
		return implementFunctionRange;
	}

	public void setImplementFunctionRange(String implementFunctionRange) {
		this.implementFunctionRange = implementFunctionRange;
	}
	
	@Length(min=0, max=100, message="项目预算承担主体长度必须介于 0 和 100 之间")
	public String getBudgetOrg() {
		return budgetOrg;
	}

	public void setBudgetOrg(String budgetOrg) {
		this.budgetOrg = budgetOrg;
	}
	
	public Double getProjectCostBudget() {
		return projectCostBudget;
	}

	public void setProjectCostBudget(Double projectCostBudget) {
		this.projectCostBudget = projectCostBudget;
	}
	
	public Double getProjectInvestBudget() {
		return projectInvestBudget;
	}

	public void setProjectInvestBudget(Double projectInvestBudget) {
		this.projectInvestBudget = projectInvestBudget;
	}
	
	public Double getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Double planTime) {
		this.planTime = planTime;
	}
	
	@Length(min=0, max=20, message="备案编号长度必须介于 0 和 20 之间")
	public String getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	
	@Length(min=0, max=10, message="状态编码长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=20, message="状态名长度必须介于 0 和 20 之间")
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	@Length(min=0, max=1, message="是否可用长度必须介于 0 和 1 之间")
	public String getYn() {
		return yn;
	}

	public void setYn(String yn) {
		this.yn = yn;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建日期不能为空")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Length(min=0, max=20, message="创建人长度必须介于 0 和 20 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="最后更新日期不能为空")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	@Length(min=0, max=20, message="最后更新人长度必须介于 0 和 20 之间")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
}