package com.boe.sysmgr.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boe.common.utils.SpringContextHolder;
import com.boe.sysmgr.dao.GroupDao;
import com.boe.sysmgr.dao.UserDao;
import org.apache.commons.lang3.StringUtils;

import com.boe.cfc.ou.dao.MGroupOrgDao;
import com.boe.cfc.ou.entity.MGroupOrg;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.User;

public class FnsSysUtils { 
	private static GroupDao groupDao = SpringContextHolder.getBean(GroupDao.class);
	private static MGroupOrgDao mGroupOrgDao = SpringContextHolder.getBean(MGroupOrgDao.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	/**
	 * 显示银行卡
	 *@param bankCode 银行卡号
	 *@return
	 */
	public static String evelBankCode(String bankCode){
		bankCode = replaceBlank(bankCode);
		if(StringUtils.isNotBlank(bankCode)){
			if(bankCode.length()>8){
				String startString = bankCode.substring(0, 4);
				String endString = bankCode.substring(bankCode.length() - 4);
				StringBuffer sb = new StringBuffer();
				for(int i = 0;i<3 ;i++){
					sb.append("*");
				}
				return startString+sb.toString()+endString;
			}
		}
		return bankCode;
	}
	 public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	        return dest;
	    }
	 
	 /**
		 * 显示金额
		 *@param money 金额
		 *@return
		 */
		public static String evelMoney(Double money){
			 NumberFormat nf = new DecimalFormat("#,###.##");
			 String str = nf.format(money);
			 int index =str.indexOf(".");
			 if(index > 0){
				 int i=str.indexOf(".");
				 String tempStr = str.substring(i);
				 if(tempStr.length() == 2){
					 str +="0";
				 }
			 }else{
				 str +=".00"; 
			 }
			 return str;
		}

	/**
	 * 获取项目列表
	 *@return
	 */
	public static List<String> getProjectList(){
		List<String> list = new ArrayList<String>();
		list.add("测试项目");
		return list;
	}

	/**
	 * 获取银行账号列表
	 *@return
	 */
	public static List<String> getBankList(Integer userId){
		List<String> list = new ArrayList<String>();
		list.add("1234****5678");
		list.add("3232****5671");
		return list;
	}
	
	/**
	 * 查询当前组织机构的第一个公司机构
	 * @Title: findFirstCompanyGroup
	 * @Description: TODO
	 * @param group
	 * @return
	 * @return: Group
	 */
	public static Group findFirstCompanyGroup(){
		User user = UserUtils.getUser();
		Group group = groupDao.get(user.getGroup().getId());
		
		if("company".equals(group.getGroupType())){
			return group;
		}
		String[] groupPaths = group.getGroupPath().split("-");
		//倒叙 查询父节点
		for (int i=groupPaths.length-1 ; i>-1 ; i--){
			group = groupDao.get(group.getParentId());
			if(null == group){
				return null;
			}
			if("company".equals(group.getGroupType())){
				return group;
			}
		}
		return null;
	}
	
	/**
	 * 查询当前组织机构的第一个公司机构的OU
	 * @Title: findFirstCompanyGroup
	 * @Description: TODO
	 * @param group
	 * @return
	 * @return: Group
	 */
	public static MGroupOrg findFirstCompanyOu(){
		Group group = findFirstCompanyGroup();
		if(group != null){
			List<MGroupOrg> groupOrgs = mGroupOrgDao.findOrgByGroupId(new MGroupOrg(group.getId()));
			if(groupOrgs!=null && groupOrgs.size()>0){
				return groupOrgs.get(0);
			}else{
				return null;
			}
		}
		return null;
	}

	/**
	 * 返显流程信息
	 * @Title: evelActityInfo
	 * @Description: TODO
	 * @param group
	 * @return
	 * @return: Group
	 */
	public static String evelActityInfo(String status){
		System.out.println(status);
		String[] statusArr = status.split(",");
		String result ="";
		List<String> stutasList = new ArrayList<String>();
		for (int i = 0; i < statusArr.length; i++) {
			if (!stutasList.contains(statusArr[i])) {
				stutasList.add(statusArr[i]);
			}
		}
		for (int i = 0; i < stutasList.size(); i++) {
			if(i == 0){
				result = evelStatus(stutasList.get(i));
			}else{
				result =result +","+ evelActityInfo(stutasList.get(i));
			}
		}
		return result;
	}

	public static String evelStatus(String status){
		if("D".equals(status)){
			return "草稿";
		}else if("GB".equals(status)){
			return "起草人(代办)";
		}else if("BA".equals(status)){
			return "审批中";
		}else if("FR".equals(status)){
			return "财务审核";
		}else if("P".equals(status)){
			return "付款中";
		}else if("E".equals(status)){
			return "流程结束";
		}
		return null;
	}

	/**
	 * 查询用户组织机构的第一个公司机构的OU
	 * @Title: findFirstCompanyGroup
	 * @Description: TODO
	 * @param group
	 * @return
	 * @return: Group
	 */
	public static MGroupOrg findFirstCompanyOuForUser(Integer userId){
		Group group = findFirstCompanyGroupForUser(userId);
		if(group != null){
			List<MGroupOrg> groupOrgs = mGroupOrgDao.findOrgByGroupId(new MGroupOrg(group.getId()));
			try {
				return groupOrgs.get(0);
			} catch (Exception e) {
			}

		}
		return null;
	}

	/**
	 * 查询用户组织机构的第一个公司机构
	 * @Title: findFirstCompanyGroup
	 * @Description: TODO
	 * @param group
	 * @return
	 * @return: Group
	 */
	public static Group findFirstCompanyGroupForUser(Integer userId){
		User user = userDao.get(userId);
		Group group = groupDao.get(user.getGroup().getId());

		if("company".equals(group.getGroupType())){
			return group;
		}
		String[] groupPaths = group.getGroupPath().split("-");
		//倒叙 查询父节点
		for (int i=groupPaths.length-1 ; i>-1 ; i--){
			group = groupDao.get(group.getParentId());
			if(null == group){
				return null;
			}
			if("company".equals(group.getGroupType())){
				return group;
			}
		}
		return null;
	}

	/**
	 * 根据当前组织机构查询当前公司所属的第一个事业部
	 * @Title: findGroups
	 * @Description: TODO
	 * @param group
	 * @return
	 * @throws Exception
	 * @return: List<Group>
	 */
	public static Group findBusinessDeptGroups() throws Exception{
		User user = UserUtils.getUser();
		Group group = groupDao.get(user.getGroup().getId());
		
		Group s_group = null;
		//判断当前传入的group是否为 事业部
		if("business_dept".equals(group.getGroupType())){
			s_group = group;
		}
		//如果传入的值不是事业部，获取当前节点往上找的第一个事业部
		if(null == s_group){
			String[] groupPaths = group.getGroupPath().split("-");
			//倒叙 查询父节点
			for (int i=groupPaths.length-1 ; i>-1 ; i--){
				group = groupDao.get(group.getParentId());
				if(null == group){
					return null;
				}
				if("business_dept".equals(group.getGroupType())){
					s_group = group;
					break;
				}
				
			}
		}
		return s_group;
	}
}

