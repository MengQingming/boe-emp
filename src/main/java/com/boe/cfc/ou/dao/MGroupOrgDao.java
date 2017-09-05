package com.boe.cfc.ou.dao;

import java.util.List;

import com.boe.cfc.ou.entity.MGroupOrg;
import com.boe.cfc.ou.entity.MOrg;
import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.Group;
import org.apache.ibatis.annotations.Param;

@com.boe.common.persistence.annotation.MyBatisDao
public interface MGroupOrgDao extends CrudDao<MGroupOrg> {
	
	/**
	 * 根据组织机构ID获取OU的ID
	 * @param mGroupOrg
	 * @return123
	 */   
	public List<MGroupOrg> findOrgByGroupId(MGroupOrg mGroupOrg);
	
	
	/**
	 * 根据组织机构IDs获取OU的IDs
	 * @param groups
	 * @return
	 */
	public List<MGroupOrg> findOrgByGroupIds(@Param("list")List<Group> groups);

	
	/**   
	 * 根据组织机构id获取OU 
	 * @Title: queryOrgByGroupId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param groupId
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月7日 下午4:09:09
	 * @return: List<MOrg>      
	 * @throws   
	 */  
	List<MOrg> queryOrgByGroupId(Integer groupId);


	/**
	 * 根据公司id和orgcode和groupid获取MGroupOrg对象
	 * @Title: getByOrgCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param companyId
	 * @param: @param orgCode
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月13日 下午4:10:07
	 * @return: MGroupOrg      
	 * @throws   
	 */  
	public MGroupOrg getByOrgCode(Integer companyId, Integer groupId, String orgCode);
	
	/**
	 * 根据groupId获取OU
	 * @param groupId
	 * @return
	 */
	List<Integer> getByGroupId(Integer groupId);
	
	/**
	 * 根据groupId 删除关联信息
	 * @param groupId
	 * @return
	 */
	Integer deleteByGroupId(Integer groupId);

	List<MGroupOrg> findMGroupOrgByOrgCode(String orgCode);

    List<MGroupOrg> getByCompanyAndGroupId(Integer companyId, Integer groupId);

}
