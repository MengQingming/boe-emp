/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.kpi.entity;

import com.boe.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;


/**
 * kpiEntity
 * @author YYY
 * @version 2017-08-14
 */
public class EmpKpi extends DataEntity<EmpKpi> {
	
	private static final long serialVersionUID = 1L;
	private String kpiLevel;		// 级别（1级、2级）
	private Long kpiYear;		// 年度
	private String kpiName;		// KPI名称
	private String dimension;		// 维度
	private String targetType;		// 指标类型
	private String kpi;		// KPI
	private String yearTarget;		// 年度指标
	private String weight;		// 权重
	private String keyNode;		// 关键节点
	private String scoreStandard;		// 评分标准
	private String dateSource;		// 数据来源
	private String showStage2;		// 是否在二级显示
	private String editStage2;		// 二级是否可编制
	private String yn;		// 是否可用
	private Date creationDate;		// 创建日期
	private String createdBy;		// 创建人
	private Date lastUpdateDate;		// 最后更新日期
	private String lastUpdatedBy;		// 最后更新人
	
	public EmpKpi() {
		super();
	}

	public EmpKpi(Integer id){
		super(id);
	}

	@Length(min=0, max=1, message="级别（1级、2级）长度必须介于 0 和 1 之间")
	public String getKpiLevel() {
		return kpiLevel;
	}

	public void setKpiLevel(String kpiLevel) {
		this.kpiLevel = kpiLevel;
	}
	
	public Long getKpiYear() {
		return kpiYear;
	}

	public void setKpiYear(Long kpiYear) {
		this.kpiYear = kpiYear;
	}
	
	@Length(min=0, max=100, message="KPI名称长度必须介于 0 和 100 之间")
	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	
	@Length(min=0, max=200, message="维度长度必须介于 0 和 200 之间")
	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	@Length(min=0, max=20, message="指标类型长度必须介于 0 和 20 之间")
	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
	@Length(min=0, max=200, message="KPI长度必须介于 0 和 200 之间")
	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	
	@Length(min=0, max=200, message="年度指标长度必须介于 0 和 200 之间")
	public String getYearTarget() {
		return yearTarget;
	}

	public void setYearTarget(String yearTarget) {
		this.yearTarget = yearTarget;
	}
	
	@Length(min=0, max=200, message="权重长度必须介于 0 和 200 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=200, message="关键节点长度必须介于 0 和 200 之间")
	public String getKeyNode() {
		return keyNode;
	}

	public void setKeyNode(String keyNode) {
		this.keyNode = keyNode;
	}
	
	@Length(min=0, max=200, message="评分标准长度必须介于 0 和 200 之间")
	public String getScoreStandard() {
		return scoreStandard;
	}

	public void setScoreStandard(String scoreStandard) {
		this.scoreStandard = scoreStandard;
	}
	
	@Length(min=0, max=100, message="数据来源长度必须介于 0 和 100 之间")
	public String getDateSource() {
		return dateSource;
	}

	public void setDateSource(String dateSource) {
		this.dateSource = dateSource;
	}
	
	@Length(min=0, max=1, message="是否在二级显示长度必须介于 0 和 1 之间")
	public String getShowStage2() {
		return showStage2;
	}

	public void setShowStage2(String showStage2) {
		this.showStage2 = showStage2;
	}
	
	@Length(min=0, max=1, message="二级是否可编制长度必须介于 0 和 1 之间")
	public String getEditStage2() {
		return editStage2;
	}

	public void setEditStage2(String editStage2) {
		this.editStage2 = editStage2;
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