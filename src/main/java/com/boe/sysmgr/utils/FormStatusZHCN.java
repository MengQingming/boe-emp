package com.boe.sysmgr.utils;
/**
 * 报账单节点与状态枚举类
 * <p>Description:FormStatus</p>
 * <p>Company:T-ark</p>
 * @author: liuxx
 * @date: 2017年3月3日 上午10:36:53
 */
public enum FormStatusZHCN {
	/**开始节点*/
	STARTACTIVITY("startActivity", "开始节点"), 
	
	/**起草人草稿*/
	ROOTDRAFTERACTIVITY("rootDrafterActivity", "起草人草稿"), 
	
	/**起草人代办*/
	DRAFTERACTIVITY("drafterActivity","起草人代办"),
	
	/**实际报销人核实*/
	CLAIMERCHECKACTIVITY("claimerCheckActivity","实际报销人核实"),
	
	/**当地财务审核*/
	LOCALFINANCEACTIVITY("localFinanceActivity","当地财务审核"),
	
	/**当地会计确认*/
	LOCALACCOUNTCONFIRMACTIVITY("localAccountConfirmActivity","当地会计确认"),
	
	/**业务组负责任审核*/
	BIZGROUPACTIVITY("bizGroupActivity","业务组负责任审核"),
	
	/**部门负责人审批*/
	DEPTMANAGERACTIVITY("deptManagerActivity","部门负责人审批"),
	 
	/**分管领导审核*/
	DIRECTORACTIVITY("directorActivity","分管领导"),
	
	/**财务主管审核*/
	FINANCIALSUPERACTIVITY("financialSuperActivity","财务主管审核"),
	
	/**财务负责人审核*/
	FINANCIALMANAGERACTIVITY("financialManagerActivity","财务负责人审核"),
	
	/**退单操作出纳审核*/
	CHARGEBACKCASHIERACTIVITY("chargebackCashierActivity","退单操作出纳审核"),
	
	/**行政总监*/
	ADMINDIRECTORACTIVITY("adminDirectorActivity","行政总监审核"),
	
	/**财务总监*/
	FINANCEDIRECTORACTIVITY("financeDirectorActivity","财务总监审核"),
	
	/**行政人事部负责人审核*/
	PERSONNELDEPTMANAGERACTIVITY("personnelDeptManagerActivity","行政人事部负责人审核"),
	
	/**行政部负责人审核*/
	ADMINDEPTMANAGERACTIVITY("adminDeptManagerActivity","行政部负责人审核"),
	
	/**人事部负责人审核*/
	HRDEPTMANAGERACTIVITY("hrDeptManagerActivity","人事部负责人审核"),
	
	/**财务经理审核*/
	FINANCEMANAGERACTIVITY("financeManagerActivity","财务经理审核"),
	
	/**总经理审核*/
	GENERALMANAGERACTIVITY("generalManagerActivity","总经理审核"),
	
	/**总裁助理审核*/
	CEOASSISTANTACTIVITY("ceoAssistantActivity","总裁助理审核"),
	
	/**销售内勤审核*/
	SALEOFFICEACTIVITY("saleOfficeActinity","销售内勤审核"),
	
	/**上级领导审核*/
	HIGHERLEVELLEADERACTIVITY("higherLevelLeaderActinity","上级领导审核"),
	
	/**销售督导审核*/
	SALESUPERVISORACTIVITY("saleSupervisorActinity","销售督导审核"),
	
	/**市场部负责人审核*/
	MARKEDEPTMANAGERACTIVITY("marketDeptManagerActivity","市场部负责人审核"),
		
	/**影像扫描中(系统)*/
	IMAGESCANACTIVITY1("imagescanActivity1","影像扫描中(系统)"),
	
	/**影像扫描中(系统)*/
	IMAGESCANACTIVITY2("imagescanActivity2","影像扫描中(系统)"),
	
	/**总裁审核*/
	PRESIDENTACTIVITY("presidentActivity","总裁审核"),
	
	/**董事长审批*/
	CHAIRMANACTIVITY("chairmanActivity","董事长审批"),
	
	/**控股采购中心负责人审批*/
	PURCHASINGCENTERACTIVITY("purchasingCenterActivity","控股采购中心负责人审批"),
	
	/**控股财务总监审批*/
	CHIEFFINANCIALACTIVITY("chiefFinancialActivity","控股财务总监审批"),
	
	/**股份财务总监审批*/
	SHARESCHIEFFINANCIALACTIVITY("sharesChiefFinancialActivity","股份财务总监审批"),
	
	/**股份行政总监审批*/
	SHARESADMINDIRECTORACTIVITY("sharesAdminDirectorActivity","股份行政总监审批"),
	
	/**专职财务处理*/
	FULLTIMEFINANCEACTIVITY("fulltimeFinanceActivity","专职财务处理"),
	
	/**影像扫描中*/
	IMAGESCANACTIVITY("imagescanActivity","影像扫描中"),
	
	/**共享会计审核*/
	FINANCEPREREVIEWACTIVITY("financePreReviewActivity","共享会计审核"),
	
	/**复核会计审核*/
	FINANCEDOUBLECHECKACTIVITY("financeDoubleCheckActivity","复核会计审核"),
	
	/**导入ERP*/
	FINANCEERPACTIVITY("financeErpActivity","导入ERP"),
	
	/**导入ERP(系统)*/
	FINANCEERPACTIVITY1("financeErpActivity1","导入ERP(系统)"),
	
	/**导入ERP(系统)*/
	FINANCEERPACTIVITY2("financeErpActivity2","导入ERP(系统)"),
	
	/**当地财务确认收票*/
	LOCALFINANCECONFIRMACTIVITY("localFinanceConfirmActivity","当地财务确认收票"),
	
	/**资金结算岗支付*/
	TREASURERACTIVITY("treasurerActivity","资金结算岗支付"),
	
	/**流程结束*/
	ENDACTIVITY("endActivity","流程结束");
	// 成员变量     
	private String activityKey;     
	private String activityValue;       
	// 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化    
	private FormStatusZHCN(String activityKey, String activityValue) {       
		this.activityKey = activityKey;      
		this.activityValue = activityValue;     
		}
	
	// 普通方法    
	public static String getActivityValue(String activityKey) {
			for (FormStatusZHCN f : FormStatusZHCN.values()) { 
				if (f.getActivityKey().equals(activityKey)) { 
					return f.activityValue;         
					}       
				}       
			return null;     
	}
	public String getActivityKey() {
		return activityKey;
	}
	public void setActivityKey(String activityKey) {
		this.activityKey = activityKey;
	}
	public String getActivityValue() {
		return activityValue;
	}
	public void setActivityValue(String activityValue) {
		this.activityValue = activityValue;
	}  
}
