package com.boe.sysmgr.utils;

import com.boe.cfc.ou.entity.MOrg;
import com.boe.common.utils.SpringContextHolder;
import com.boe.common.utils.StringUtils;
import com.boe.cfc.ou.dao.MGroupOrgDao;
import com.boe.sysmgr.entity.User;

import java.util.List;

/**
 * OU工具类
 *
 * @author baipan
 * @ClassName: OUUtils
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @date 2016年12月23日 下午2:13:27
 */
public class OUUtils {

	private static MGroupOrgDao MorgDao = SpringContextHolder.getBean(MGroupOrgDao.class);

	/**
	 * 根据 组织id 查询 OU 的本位币 (* 新希望项目使用 改. )
	 * @return
	 */
	public static String getOUFunctionalCurrency() {
		User user = UserUtils.getUser();
		Integer groupId = user.getGroup().getId();
		if (groupId == null || groupId < 1) {
			return null;
		}
		List<MOrg> mOrgs = MorgDao.queryOrgByGroupId(groupId);
		if(mOrgs!=null && mOrgs.size()>0){
			if (StringUtils.isEmpty(mOrgs.get(0).getCurrency())){
				mOrgs.get(0).setCurrency("人民币");
			}
			return mOrgs.get(0).getCurrency();
		}else{
			return null;
		}
	}

	/**
	 * 根据 组织id 查询 OU
	 * @return
	 */
	public static MOrg getOrg() {
		User user = UserUtils.getUser();
		Integer groupId = user.getGroup().getId();
		if (groupId == null || groupId < 1) {
			return null;
		}
		List<MOrg> mOrgs = MorgDao.queryOrgByGroupId(groupId);
		if(mOrgs!=null && mOrgs.size()>0){
			return mOrgs.get(0);
		}else{
			return null;
		}
	}
}
