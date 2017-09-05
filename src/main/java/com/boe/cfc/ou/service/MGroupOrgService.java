package com.boe.cfc.ou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.boe.cfc.ou.dao.MGroupOrgDao;
import com.boe.cfc.ou.entity.MGroupOrg;
import com.boe.cfc.ou.entity.MOrg;
import com.boe.common.service.CrudService;
import com.boe.sysmgr.entity.Group;

@Service
@Transactional(readOnly = true)
public class MGroupOrgService extends CrudService<MGroupOrgDao, MGroupOrg> {

	@Autowired
	private MGroupOrgDao mGroupOrgDao;

	/**
	 * 根据组织机构ID获取OU
	 * @param groupId
	 * @returnO
	 */
	public List<MGroupOrg> findOrgByGroupId(Integer groupId){

		return mGroupOrgDao.findOrgByGroupId(new MGroupOrg(groupId));
	}
	

	/**
	 * 根据组织机构ID获取OU
	 * @param groups
	 * @return
	 */
	public List<MGroupOrg> findOrgByGroupIds(List<Group> groups){

		return mGroupOrgDao.findOrgByGroupIds(groups);
	}

	/**   
	 * 根据组织机构id获取OU 
	 * @Title: queryOrgByGroupId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param groupId
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月7日 下午4:06:27
	 * @return: List<MOrg>      
	 * @throws   
	 */  
	public List<MOrg> queryOrgByGroupId(Integer groupId) {
		return dao.queryOrgByGroupId(groupId);
	}


	/**
	 * 根据公司id和orgcode和groupid获取MGroupOrg对象
	 * @Title: getByOrgCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param companyId
	 * @param: @param orgCode
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月13日 下午4:05:21
	 * @return: MGroupOrg      
	 * @throws   
	 */  
	public MGroupOrg getByOrgCode(Integer companyId, Integer groupId, String orgCode) {
		if (companyId == null || companyId.intValue() <= 0 || groupId == null || groupId.intValue() <= 0 || StringUtils.isEmpty(orgCode)) {
			return null;
		}
		return dao.getByOrgCode(companyId, groupId, orgCode);
	}


	/**   
	 * 根据组织机构集合获取OU集合
	 * @Title: queryOrgsByGroups   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param groups
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月13日 下午4:35:03
	 * @return: List<MOrg>      
	 * @throws   
	 */  
	public List<MOrg> queryOrgsByGroups(List<Group> groups) throws Exception {
		List<MOrg> list = new ArrayList<>();
		if (groups != null && groups.size() > 0) {
			for (Group temp : groups) {
				List<MOrg> orgs = dao.queryOrgByGroupId(temp.getId());

				if(orgs!=null && orgs.size()>0){
					if (orgs.get(0) != null) {
						orgs.get(0).setGroup(temp);
						list.add(orgs.get(0));
					}
				}

			}
		}
		return list;
	}
	

	/**
	 * 根据groupId获取OU
	 * @param groupId
	 * @return
	 */
	public MGroupOrg getByGroupId(Integer groupId){
		MGroupOrg groupOrg = new MGroupOrg();
		groupOrg.setOuList(dao.getByGroupId(groupId));
		return groupOrg;
	}
	
	/**
	 * 根据groupId 删除关联信息
	 * @param groupId
	 * @return
	 */
	public Integer deleteByGroupId(Integer groupId){
		return dao.deleteByGroupId(groupId);
	};

	public List<MGroupOrg> findMGroupOrgByOrgCode(String orgCode) {
		return mGroupOrgDao.findMGroupOrgByOrgCode(orgCode);
	}

}
