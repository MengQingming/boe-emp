package com.boe.sysmgr.entity;

import com.boe.common.persistence.DataEntity;

import java.util.Date;



/**
* <p>Description:DictItem 数据字典项Entity</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:16:55
* @version: 1.0
*/
public class DictItem extends DataEntity<DictItem> {

	private static final long serialVersionUID = 1L;
	//字典组信息
	private Dict dict;
	//应用no
	private String appNo;
	//字典项编码
	private String itemCode;
	//字典项名称
	private String itemValue;
	//排序编号
	private Integer displayOrder; 
	//可用状态
	private String status;
	//备注
	private String remarks;
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
	//语言
	private String language;
	//字典英文标识
	private String itemMark;
	
	private Integer companyId;
	
	private String companyNo;
	
	private String isGeneral;
	
	private Boolean selected;
	
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

	private String companyName;
	
	private String dictCodeQuery;
	private Date creationDate;
	private Date lastUpdateDate;
	
	
	public DictItem() {
		super();
	}
	
	public String getDictCodeQuery() {
		return dictCodeQuery;
	}

	public void setDictCodeQuery(String dictCodeQuery) {
		this.dictCodeQuery = dictCodeQuery;
	}

	public DictItem(Integer id){
		super(id);
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getItemMark() {
		return itemMark;
	}

	public void setItemMark(String itemMark) {
		this.itemMark = itemMark;
	}

	public String getIsGeneral() {
		return isGeneral;
	}

	public void setIsGeneral(String isGeneral) {
		this.isGeneral = isGeneral;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "DictItem [dict=" + dict + ", appNo=" + appNo + ", itemCode="
				+ itemCode + ", itemValue=" + itemValue + ", displayOrder="
				+ displayOrder + ", status=" + status + ", remarks=" + remarks
				+ ", attribute1=" + attribute1 + ", attribute2=" + attribute2
				+ ", attribute3=" + attribute3 + ", attribute4=" + attribute4
				+ ", attribute5=" + attribute5 + ", attribute6=" + attribute6
				+ ", attribute7=" + attribute7 + ", attribute8=" + attribute8
				+ ", attribute9=" + attribute9 + ", attribute10=" + attribute10
				+ ", language=" + language + ", itemMark=" + itemMark
				+ ", dictCodeQuery=" + dictCodeQuery + ", creationDate="
				+ creationDate + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
}