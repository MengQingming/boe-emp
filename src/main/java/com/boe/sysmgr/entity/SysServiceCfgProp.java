package com.boe.sysmgr.entity;

import com.boe.common.persistence.DataEntity;

import java.io.Serializable;

/**
 * @ClassName: SysServiceCfgPmt
 * @Description: TODO 服务接口参数
 * @author WangShengDong
 * @date 2017年3月27日 下午4:41:45
 */
public class SysServiceCfgProp extends DataEntity<SysServiceCfgProp> implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private Integer				cfgId;

	private String				code;

	private String				value;

	private String				status;
	
	public SysServiceCfgProp() {
		this.status = "1";
	}

	public Integer getCfgId() {
		return cfgId;
	}

	public void setCfgId(Integer cfgId) {
		this.cfgId = cfgId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
