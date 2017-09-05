package com.boe.sysmgr.exception.entity;

import com.boe.common.persistence.DataEntity;

/**
 *
 * 异常处理 实体类
 *
 * @author xt
 * @date 2017年1月4日 10:52:28
 * @version 1.0
 *
 */
public class SysException extends DataEntity<SysException> {

	private static final long serialVersionUID = 1L;

	/** 语言 */
	private String language;

	/** 应用NO */
	private String appNo;

	/** 模块编号 */
	private String moduleNo;

	/** 异常类型 */
	private String exceptionType;

	/** 异常编码 */
	private String exceptionCode;

	/** 异常消息 */
	private String exceptionMessage;

	/** 描述 */
	private String remarks;

	/** 异常状态 已处理|未处理 */
	private String status;

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getModuleNo() {
		return this.moduleNo;
	}

	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}

	public String getExceptionType() {
		return this.exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getExceptionCode() {
		return this.exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMessage() {
		return this.exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
