package com.boe.utils;

/**
 * 返回结果信息
 * @author alvin
 *
 */
public class RequestResult {
	
	String status;//状态
	String statusCode;//错误代码
	String msg;//消息
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String errorCode) {
		this.statusCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
