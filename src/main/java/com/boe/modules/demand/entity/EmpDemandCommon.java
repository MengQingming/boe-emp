/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.demand.entity;

import com.boe.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;


/**
 * demandEntity
 * @author YYY
 * @version 2017-08-16
 */
public class EmpDemandCommon extends DataEntity<EmpDemandCommon> {
	
	private static final long serialVersionUID = 1L;
	private Long recordId;		// 备案ID
	private String demandTitle;		// 标题
	private String demandCode;		// 需求编码
	private String demandCategory;		// 需求分类
	private String demandType;		// 需求类型
	private String demandSource;		// 需求收集渠道
	private String demandBp;		// 需求收集BP
	private String proposePerson;		// 需求实际提出人
	private String proposePersonName;		// 需求实际提出人名
	private String involveSys;		// 涉及系统
	private Date expectTime;		// 期望解决时间
	private Date invalidDate;		// 实际提出时间
	private String planCover;		// 事业计划涵盖
	private String describe;		// 需求详细描述
	private String validity;		// 有效性
	private String feasibility;		// 可行性
	private String valueContribution;		// 价值贡献
	private Date demandUpdateDate;		// 需求更新日期
	private String demandUpdateUser;		// 需求更新人
	private String suggesetRecordType;		// 建议备案类型
	private String suggesetRecordName;		// 建议备案名称
	private String isExpert;		// 是否需要专家组决策
	private String responsibilityDept;		// 建议主责部门
	private String tentativeSolution;		// 初步解决方案
	private String approveOpinion;		// 审核意见
	private Date analysisUpdateDate;		// 需求分析更新日期
	private String analysisUpdateUser;		// 需求分析创建人
	private String expertGroup;		// 专家组成员
	private String expertGroupName;		// 专家组成员名
	private String solution;		// 解决方案
	private Date expertReviewDate;		// 专家评审日期
	private String expertReviewUser;		// 专家评审人
	private String expertReviewUserName;		// 专家评审人名
	private Date planFinishDate;		// 计划完成日期
	private String proposal;		// 意见建议
	private Date deptUpdateDate;		// 承接部门更新时间
	private String deptUpdateUser;		// 承接部门更新人
	private String status;		// 状态CDOE
	private String statusName;		// 状态名
	private String yn;		// 是否可用
	private Date creationDate;		// 创建日期
	private String createdBy;		// 创建人
	private Date lastUpdateDate;		// 最后更新日期
	private String lastUpdatedBy;		// 最后更新人
	private Date beginInvalidDate;		// 开始 实际提出时间
	private Date endInvalidDate;		// 结束 实际提出时间
	
	public EmpDemandCommon() {
		super();
	}

	public EmpDemandCommon(Integer id){
		super(id);
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	
	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getDemandTitle() {
		return demandTitle;
	}

	public void setDemandTitle(String demandTitle) {
		this.demandTitle = demandTitle;
	}
	
	@Length(min=0, max=16, message="需求编码长度必须介于 0 和 16 之间")
	public String getDemandCode() {
		return demandCode;
	}

	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}
	
	@Length(min=0, max=10, message="需求分类长度必须介于 0 和 10 之间")
	public String getDemandCategory() {
		return demandCategory;
	}

	public void setDemandCategory(String demandCategory) {
		this.demandCategory = demandCategory;
	}
	
	@Length(min=0, max=10, message="需求类型长度必须介于 0 和 10 之间")
	public String getDemandType() {
		return demandType;
	}

	public void setDemandType(String demandType) {
		this.demandType = demandType;
	}
	
	@Length(min=0, max=100, message="需求收集渠道长度必须介于 0 和 100 之间")
	public String getDemandSource() {
		return demandSource;
	}

	public void setDemandSource(String demandSource) {
		this.demandSource = demandSource;
	}
	
	@Length(min=0, max=100, message="需求收集BP长度必须介于 0 和 100 之间")
	public String getDemandBp() {
		return demandBp;
	}

	public void setDemandBp(String demandBp) {
		this.demandBp = demandBp;
	}
	
	@Length(min=0, max=20, message="需求实际提出人长度必须介于 0 和 20 之间")
	public String getProposePerson() {
		return proposePerson;
	}

	public void setProposePerson(String proposePerson) {
		this.proposePerson = proposePerson;
	}
	
	@Length(min=0, max=30, message="需求实际提出人名长度必须介于 0 和 30 之间")
	public String getProposePersonName() {
		return proposePersonName;
	}

	public void setProposePersonName(String proposePersonName) {
		this.proposePersonName = proposePersonName;
	}
	
	@Length(min=0, max=50, message="涉及系统长度必须介于 0 和 50 之间")
	public String getInvolveSys() {
		return involveSys;
	}

	public void setInvolveSys(String involveSys) {
		this.involveSys = involveSys;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Date expectTime) {
		this.expectTime = expectTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}
	
	@Length(min=0, max=500, message="事业计划涵盖长度必须介于 0 和 500 之间")
	public String getPlanCover() {
		return planCover;
	}

	public void setPlanCover(String planCover) {
		this.planCover = planCover;
	}
	
	@Length(min=0, max=1100, message="需求详细描述长度必须介于 0 和 1100 之间")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	@Length(min=0, max=500, message="有效性长度必须介于 0 和 500 之间")
	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	@Length(min=0, max=500, message="可行性长度必须介于 0 和 500 之间")
	public String getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility;
	}
	
	@Length(min=0, max=500, message="价值贡献长度必须介于 0 和 500 之间")
	public String getValueContribution() {
		return valueContribution;
	}

	public void setValueContribution(String valueContribution) {
		this.valueContribution = valueContribution;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDemandUpdateDate() {
		return demandUpdateDate;
	}

	public void setDemandUpdateDate(Date demandUpdateDate) {
		this.demandUpdateDate = demandUpdateDate;
	}
	
	@Length(min=0, max=20, message="需求更新人长度必须介于 0 和 20 之间")
	public String getDemandUpdateUser() {
		return demandUpdateUser;
	}

	public void setDemandUpdateUser(String demandUpdateUser) {
		this.demandUpdateUser = demandUpdateUser;
	}
	
	@Length(min=0, max=50, message="建议备案类型长度必须介于 0 和 50 之间")
	public String getSuggesetRecordType() {
		return suggesetRecordType;
	}

	public void setSuggesetRecordType(String suggesetRecordType) {
		this.suggesetRecordType = suggesetRecordType;
	}
	
	@Length(min=0, max=100, message="建议备案名称长度必须介于 0 和 100 之间")
	public String getSuggesetRecordName() {
		return suggesetRecordName;
	}

	public void setSuggesetRecordName(String suggesetRecordName) {
		this.suggesetRecordName = suggesetRecordName;
	}
	
	@Length(min=0, max=10, message="是否需要专家组决策长度必须介于 0 和 10 之间")
	public String getIsExpert() {
		return isExpert;
	}

	public void setIsExpert(String isExpert) {
		this.isExpert = isExpert;
	}
	
	@Length(min=0, max=20, message="建议主责部门长度必须介于 0 和 20 之间")
	public String getResponsibilityDept() {
		return responsibilityDept;
	}

	public void setResponsibilityDept(String responsibilityDept) {
		this.responsibilityDept = responsibilityDept;
	}
	
	@Length(min=0, max=1100, message="初步解决方案长度必须介于 0 和 1100 之间")
	public String getTentativeSolution() {
		return tentativeSolution;
	}

	public void setTentativeSolution(String tentativeSolution) {
		this.tentativeSolution = tentativeSolution;
	}
	
	@Length(min=0, max=150, message="审核意见长度必须介于 0 和 150 之间")
	public String getApproveOpinion() {
		return approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAnalysisUpdateDate() {
		return analysisUpdateDate;
	}

	public void setAnalysisUpdateDate(Date analysisUpdateDate) {
		this.analysisUpdateDate = analysisUpdateDate;
	}
	
	@Length(min=0, max=10, message="需求分析创建人长度必须介于 0 和 10 之间")
	public String getAnalysisUpdateUser() {
		return analysisUpdateUser;
	}

	public void setAnalysisUpdateUser(String analysisUpdateUser) {
		this.analysisUpdateUser = analysisUpdateUser;
	}
	
	@Length(min=0, max=100, message="专家组成员长度必须介于 0 和 100 之间")
	public String getExpertGroup() {
		return expertGroup;
	}

	public void setExpertGroup(String expertGroup) {
		this.expertGroup = expertGroup;
	}
	
	@Length(min=0, max=300, message="专家组成员名长度必须介于 0 和 300 之间")
	public String getExpertGroupName() {
		return expertGroupName;
	}

	public void setExpertGroupName(String expertGroupName) {
		this.expertGroupName = expertGroupName;
	}
	
	@Length(min=0, max=1000, message="解决方案长度必须介于 0 和 1000 之间")
	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpertReviewDate() {
		return expertReviewDate;
	}

	public void setExpertReviewDate(Date expertReviewDate) {
		this.expertReviewDate = expertReviewDate;
	}
	
	@Length(min=0, max=20, message="专家评审人长度必须介于 0 和 20 之间")
	public String getExpertReviewUser() {
		return expertReviewUser;
	}

	public void setExpertReviewUser(String expertReviewUser) {
		this.expertReviewUser = expertReviewUser;
	}
	
	@Length(min=0, max=30, message="专家评审人名长度必须介于 0 和 30 之间")
	public String getExpertReviewUserName() {
		return expertReviewUserName;
	}

	public void setExpertReviewUserName(String expertReviewUserName) {
		this.expertReviewUserName = expertReviewUserName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanFinishDate() {
		return planFinishDate;
	}

	public void setPlanFinishDate(Date planFinishDate) {
		this.planFinishDate = planFinishDate;
	}
	
	@Length(min=0, max=1000, message="意见建议长度必须介于 0 和 1000 之间")
	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeptUpdateDate() {
		return deptUpdateDate;
	}

	public void setDeptUpdateDate(Date deptUpdateDate) {
		this.deptUpdateDate = deptUpdateDate;
	}
	
	@Length(min=0, max=20, message="承接部门更新人长度必须介于 0 和 20 之间")
	public String getDeptUpdateUser() {
		return deptUpdateUser;
	}

	public void setDeptUpdateUser(String deptUpdateUser) {
		this.deptUpdateUser = deptUpdateUser;
	}
	
	@Length(min=0, max=10, message="状态CDOE长度必须介于 0 和 10 之间")
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
	
	public Date getBeginInvalidDate() {
		return beginInvalidDate;
	}

	public void setBeginInvalidDate(Date beginInvalidDate) {
		this.beginInvalidDate = beginInvalidDate;
	}
	
	public Date getEndInvalidDate() {
		return endInvalidDate;
	}

	public void setEndInvalidDate(Date endInvalidDate) {
		this.endInvalidDate = endInvalidDate;
	}
		
}