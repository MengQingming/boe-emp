package com.boe.sysmgr.entity;

import java.io.Serializable;
import java.util.Date;

import com.boe.common.persistence.DataEntity;

/**
 *
 *
 */
public class SysMessage extends DataEntity<SysMessage> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 应用NO */
	private String appNo;

	/** 单位ID */
	private Integer companyId;

	/** 单位编号 */
	private String companyNo;

	/** 单位名称 */
	private String companyName;

	/** 请求ID */
	private String requestId;

	/** 发送人ID */
	private Integer senderId;

	/** 发送人工号 */
	private String senderNum;

	/** 发送人名称 */
	private String senderName;

	/** 发送人使用的工具如 手机号 邮箱名称等 */
	private String senderTool;

	/** 分发类型: 在线 site 短信 sms 邮件 email */
	private String distType;

	/** 分发列表 */
	private String distList;

	/** 消息头 */
	private String msgTitle;

	/** 消息体 */
	private String msgBody;

	/** 是否有附件 */
	private String hasAttachement;
	
	/** 附件地址 */
	private String attachfilePath;

	/** 发送状态 */
	private String sendStatus;

	/** 发送时间 */
	private Date sendTime;

	/** 发送日志 */
	private String sendLog;

	/** 最大发送次数 */
	private Integer maxTryTimes;

	/** 当前发送次数 */
	private Integer currTryTimes;

	/** 信息读取状态 */
	private String readStatus;

	/** 信息读取时间 */
	private Date readTime;

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

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getSenderNum() {
		return this.senderNum;
	}

	public void setSenderNum(String senderNum) {
		this.senderNum = senderNum;
	}

	public String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderTool() {
		return this.senderTool;
	}

	public void setSenderTool(String senderTool) {
		this.senderTool = senderTool;
	}

	public String getDistType() {
		return this.distType;
	}

	public void setDistType(String distType) {
		this.distType = distType;
	}

	public String getDistList() {
		return this.distList;
	}

	public void setDistList(String distList) {
		this.distList = distList;
	}

	public String getMsgTitle() {
		return this.msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgBody() {
		return this.msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getHasAttachement() {
		return this.hasAttachement;
	}

	public void setHasAttachement(String hasAttachement) {
		this.hasAttachement = hasAttachement;
	}
	
	public String getAttachfilePath() {
		return attachfilePath;
	}

	public void setAttachfilePath(String attachfilePath) {
		this.attachfilePath = attachfilePath;
	}

	public String getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendLog() {
		return this.sendLog;
	}

	public void setSendLog(String sendLog) {
		this.sendLog = sendLog;
	}

	public Integer getMaxTryTimes() {
		return this.maxTryTimes;
	}

	public void setMaxTryTimes(Integer maxTryTimes) {
		this.maxTryTimes = maxTryTimes;
	}

	public Integer getCurrTryTimes() {
		return this.currTryTimes;
	}

	public void setCurrTryTimes(Integer currTryTimes) {
		this.currTryTimes = currTryTimes;
	}

	public String getReadStatus() {
		return this.readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public Date getReadTime() {
		return this.readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
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
