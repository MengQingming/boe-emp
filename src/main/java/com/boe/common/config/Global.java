package com.boe.common.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.boe.common.utils.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;
import com.boe.common.utils.PropertiesLoader;

/**
 * 全局配置类
 * @author 
 * @version 2016-11-16
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("config.properties");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 消息读取状态
	 */
	public static final String rend = "1";
	public static final String unread = "0";
	
	/**
	 * 消息发送状态
	 */
	public static final String msgsending = "0";
	public static final String msgsendFail = "2";
	public static final String msgsendSuccess = "1";

	public static final String SAP = "SAP";
	public static final long COMPANY_ID = 6L;
	public static final String COMPANY_NO = "NH";
	public static final String COMPANY_NAME = "新希望集团";
	public static final String FLEX_VALUE_SET = "NH_COA_AC";
	public static final String SAP_LOG_TYPE_GENERATE_ACC = "generate";
	public static final String SAP_LOG_TYPE_WRITEOFF_ACC = "writeoff";

	/**
	 * 任务类型 实际
	 */
	public static final String WF_TASK_TYPE_REAL = "1";
	public static final String WF_TASK_TYPE_REAL_DESC = "实际任务";
	/**
	 * 任务类型 虚拟
	 */
	public static final String WF_TASK_TYPE_VIRTUAL = "2";
	public static final String WF_TASK_TYPE_VIRTUAL_DESC = "虚拟任务";

	
	/**
	 * 工作流模块 待办状态
	 */
	public static final String WF_STATUS_TODO = "10";

	/**
	 * 工作流模块 待领取 待分配
	 */
	public static final String WF_STATUS_ALLOT = "11";

	/**
	 * 工作流模块 已办状态
	 */
	public static final String WF_STATUS_DONE = "12";

	/**
	 * 工作流模块 待阅状态
	 */
	public static final String WF_STATUS_TOREAD = "13";

	/**
	 * 工作流模块 已阅状态
	 */
	public static final String WF_STATUS_READED = "14";
	
	/**
	 * 工作流模块 待回复状态
	 */
	public static final String WF_STATUS_TO_ANSWERED = "15";
	/**
	 * 工作流模块 待回复状态描述
	 */
	public static final String WF_STATUS_TO_ANSWERED_DESC = "待回复";
	

	/**
	 * 工作流模块 起草人(草稿)环节定义ID
	 */
	public static final String WF_ACT_ID_ROOT = "rootDrafterActivity";
	public static final String WF_ACT_NAME_ROOT = "起草人";

	public static final String WF_ACT_ID_DRAFTER = "drafterActivity";
	public static final String WF_ACT_NAME_DRAFTER = "起草人";

	public static final String WF_COMMENT_KEY = "comment";

	public static final String WF_OLD_TASKINST_ID = "oldTaskInstId";
	
	public static final String WF_TEAM_ID = "teamId";
	public static final String WF_TEAM_NO = "teamNo";

	/**
	 * 银行转账
	 */
	public static final String BANK_ON_LINE="001";//银企
	public static final String BANK_OFF_LINE="002";//线下网银转账
	public static final String RY="003";//财企通
	public static final String CHEQUE="004";//支票
	public static final String ACCEPTANCE_BILL="005";//承兑汇票
	
	/**
	 * 票据小类
	 */
	public static final String CHEQUE_CASH="004001";//现金支票
	public static final String CHEQUE_TRANSFER_ACC="004002";//转账支票
	public static final String CHEQUE_COMMON="004003";//普通支票
	
	public static final String ACCEPTANCE_BILL_BANK_C="005001";//应收-银行汇票
	public static final String ACCEPTANCE_BILL_BANK_P="005002";//应付-银行汇票
	public static final String ACCEPTANCE_BILL_BUSINESS_C="005003";//应收-商业汇票
	public static final String ACCEPTANCE_BILL_BUSINESS_P="005004";//应付-商业汇票
	
	/**
	 * 支付单状态
	 */
	public static final String HAVNT_SEND = "0"; // 未发送
	public static final String WAIT_SEND="10";  // 待发送
	public static final String RY_ERR_BEFORM_SEND_AND_WAIT = "14"; // 发送前错误，如检查余额等失败，等待下次支付
	public static final String SENDING="20"; // 发送中
	public static final String SEND_SUCCESS="30"; // 发送成功
    public static final String SEND_EXCEPTION="40"; // 发送异常
	public static final String PAY_SUCCESS="30"; // 支付成功
	public static final String PAY_FAIL="40"; // 支付失败
	
	/**
	 * 影像平台任务状态
	 */
	public static final String OCR_TASK_HAVENT_SEND = "10"; // 未发送
	public static final String OCR_TASK_SENDING = "20"; // 发送中
	public static final String OCR_TASK_SEND_SUCCESS = "30"; // 发送成功
	public static final String OCR_TASK_SEND_FAIL_AND_WAIT = "40"; // 发送失败等待再次发送 
	public static final String OCR_TASK_SEND_FAIL = "50"; // 发送失败
	public static final String OCR_TASK_SEND_EXCEPTION = "60"; // 发送异常

    public static final String OATODO_TASK_HAVENT_SEND = "10"; // 未发送
    public static final String OATODO_TASK_SENDING = "20"; // 发送中
    public static final String OATODO_TASK_SEND_SUCCESS = "30"; // 发送成功
    public static final String OATODO_TASK_SEND_FAIL_AND_WAIT = "40"; // 发送失败等待再次发送 
    public static final String OATODO_TASK_SEND_FAIL = "50"; // 发送失败
    public static final String OATODO_TASK_SEND_EXCEPTION = "60"; // 发送异常
	
    /**  调度因网络等原因失败需重新执行最多执行次数   */
    public static final int EXEC_COUNT_LIMIT = 10;
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	
	/**
	 * AppNo
	 */
	public static final String APP_NO_BPSS_RMBS = "fssc_rmbs";
	
	/**
	 * 手动调用流程结束
	 */
	public static final String HAND_END_ACTIVITY = "hand_end";
	/**
	 * 自动定时调用流程结束
	 */
	public static final String AUTOMATIC_END_ACTIVITY = "automatic_end";
	
	/**
	 * ClaimName
	 */
	public static final String CLAIM_NAME_LEAVE = "HrEmpLeave";
	public static final String CLAIM_NAME_RMBS = "RmbsClaim";
	public static final String CLAIM_NAME_PM_RISK = "PmRiskClaim";
	public static final String CLAIM_NAME_PM_COST = "PmCostClaim";
	public static final String CLAIM_NAME_PM_COLLECTION_PLAN = "PmCollectionPlanClaim";
	public static final String CLAIM_NAME_PM_COLLECTION_PLAN_EXE = "PmCollectionPlanExeClaim";
	public static final String CLAIM_NAME_PM_PAYMENT_PLAN = "PmPaymentPlanClaim";
	public static final String CLAIM_NAME_PM_PAYMENT_PLAN_EXE = "PmPaymentPlanExeClaim";
	
	
	/**
	 * 工时填报日期范围
	 */
	public static final int CODE_CLAIM_DATE_RANGE = 15;
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	/**
	 * 在修改系统用户和角色时是否同步到Activiti
	 
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}*/
    
	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

	/**
	 * 获取上传文件的根目录
	 * @return
	 */
	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtils.isBlank(dir)){
			try {
				dir = ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}
//		System.out.println("userfiles.basedir: " + dir);
		return dir;
	}
	
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
	
}
