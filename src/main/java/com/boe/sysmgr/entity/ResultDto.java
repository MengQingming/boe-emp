package com.boe.sysmgr.entity;

import com.boe.common.persistence.DataEntity;

/**
 * 返回结果dto
 * <p>Description:resultDto</p>
 * <p>Company:T-ark</p>
 * @author: liuxx
 * @date: 2017年3月4日 下午2:34:50
 */
public class ResultDto extends DataEntity<ResultDto> {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返回true or false
	 */
	private Boolean flag;
	/**
	 * 返回消息
	 */
	private String msg;
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "resultDto [flag=" + flag + ", msg=" + msg + "]";
	}
}
