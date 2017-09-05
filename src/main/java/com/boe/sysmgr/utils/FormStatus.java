package com.boe.sysmgr.utils;
/**
 * 报账单节点与状态枚举类
 * <p>Description:FormStatus</p>
 * <p>Company:T-ark</p>
 * @author: liuxx
 * @date: 2017年3月3日 上午10:36:53
 */
public enum FormStatus {
	/**开始节点*/
	STARTACTIVITY("startActivity", "D"), 
	
	/**起草人草稿*/
	ROOTDRAFTERACTIVITY("rootDrafterActivity", "D"), 
	
	/**起草人代办*/
	DRAFTERACTIVITY("drafterActivity","D"),
	
	/**实际报销人核实*/
	CLAIMERCHECKACTIVITY("claimerCheckActivity","BA"),
	
	/**业务组负责任审核*/
	BIZGROUPACTIVITY("bizGroupActivity","BA"),
	
	/**部门负责人审批*/
	DEPTMANAGERACTIVITY("deptManagerActivity ","BA"),
	
	/**分管领导*/
	DIRECTORACTIVITY("directorActivity","BA"),
	
	/**行政总监*/
	ADMINDIRECTORACTIVITY("adminDirectorActivity","BA"),
	
	/**财务总监*/
	FINANCEDIRECTORACTIVITY("financeDirectorActivity","BA"),
	
	/**共享会计审核*/
	FINANCEPREREVIEWACTIVITY("financePreReviewActivity","FR"),
	
	/**复核会计审核*/
	FINANCEDOUBLECHECKACTIVITY("financeDoubleCheckActivity","FR"),
	
	/**导入ERP*/
	FINANCEERPACTIVITY("financeErpActivity","FR"),
	
	/**当地财务确认收票*/
	LOCALFINANCECONFIRMACTIVITY("localFinanceConfirmActivity","P"),
	
	/**资金结算岗支付*/
	TREASURERACTIVITY("treasurerActivity","P"),
	
	/**流程结束*/
	ENDACTIVITY("endActivity","E");
	// 成员变量     
	private String activityKey;     
	private String activityValue;       
	// 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化    
	private FormStatus(String activityKey, String activityValue) {       
		this.activityKey = activityKey;      
		this.activityValue = activityValue;     
		}
	
	// 普通方法    
	public static String getActivityValue(String activityKey) {
			for (FormStatus f : FormStatus .values()) { 
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
