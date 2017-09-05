package com.boe.sysmgr.entity;

import com.boe.common.persistence.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysServiceCfg 
 * @Description: TODO 系统管理_39用户服务配置 SYS_SERVICE_CFG
 * @author WangShengDong
 * @date 2017年4月24日 下午5:21:11
 */
public class SysServiceCfg extends DataEntity<SysServiceCfg> implements Serializable {

	private static final long	serialVersionUID	= 1L;

	/** 外围系统 */
	private String				systemName;

	/** 服务名称 */
	private String				serviceName;

	/** 服务描述 */
	private String				serviceDesc;
	
	/** 服务运行IP */
	private String				serviceRunIp;

	/** 服务运行环境名称 */
	private String				serviceRunName;

	/** 服务地址 */
	private String				serviceUrl;

	/** 是否启用 */
	private String				status;

	/** 创建日期 */
	private Date				creationDate;

	/** 最后更新日期 */
	private Date				lastUpdateDate;
	
	/** 服务类型 R读 W写 */
	private String				serviceType;

	/** 服务方式 S服务端 C客户端 */
	private String				serviceMode;

	/** 环境类型 DEV开发 TEST测试 UAT用户接受测试 PRD生产 */
	private String				environmentType;

	/** 服务类型名称 */
	private String				serviceTypeName;

	/** 服务方式名称 */
	private String				serviceModeName;

	/** 环境类型名称 */
	private String				environmentTypeName;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getServiceRunIp() {
		return serviceRunIp;
	}

	public void setServiceRunIp(String serviceRunIp) {
		this.serviceRunIp = serviceRunIp;
	}

	public String getServiceRunName() {
		return serviceRunName;
	}

	public void setServiceRunName(String serviceRunName) {
		this.serviceRunName = serviceRunName;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceMode() {
		return serviceMode;
	}

	public void setServiceMode(String serviceMode) {
		this.serviceMode = serviceMode;
	}

	public String getEnvironmentType() {
		return environmentType;
	}

	public void setEnvironmentType(String environmentType) {
		this.environmentType = environmentType;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public String getServiceModeName() {
		return serviceModeName;
	}

	public void setServiceModeName(String serviceModeName) {
		this.serviceModeName = serviceModeName;
	}

	public String getEnvironmentTypeName() {
		return environmentTypeName;
	}

	public void setEnvironmentTypeName(String environmentTypeName) {
		this.environmentTypeName = environmentTypeName;
	}

}
