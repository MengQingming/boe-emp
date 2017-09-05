package com.boe.sysmgr.entity;

import java.util.Date;

/**
 *
 * 附件实体json实体
 *
 */
public class SysFileModel  {


	/** 表ID */
	private Integer id;

	/** 应用NO */
	private String appNo;

	/** 单位ID */
	private Integer companyId;

	/** 单位编号 */
	private String companyNo;

	/** 单位名称 */
	private String companyName;

	/** 文件类型 CSV EXCEL等等 */
	private String fileType;

	/** 文件名称 */
	private String fileName;

	/** 文件服务器端路径 */
	private String serverPath;

	/** 文件客户端路径 */
	private String clientPath;

	/** 客户端IP */
	private String clientIp;

	/** 是否启用 */
	private String status;

	/** 数据来源 */
	private String dataSource;

	/** 数据来源业务主键 */
	private String dataSourceKey;

	/** 创建日期 */
	private Date creationDate;

	/** 最后更新日期 */
	private Date lastUpdateDate;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getServerPath() {
		return this.serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getClientPath() {
		return this.clientPath;
	}

	public void setClientPath(String clientPath) {
		this.clientPath = clientPath;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceKey() {
		return this.dataSourceKey;
	}

	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
