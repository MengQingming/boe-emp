package com.boe.sysmgr.service;

import java.util.ArrayList;
import java.util.List;

import com.boe.common.service.TreeService;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.boe.sysmgr.dao.GroupDao;

/**
 * <p>Description:GroupService 组织机构Service</p>
 * <p>Company:T-ark </p>
 * @author: lxx
 * @created: 2016-12-8下午3:26:42
 * @version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class GroupService extends TreeService<GroupDao, Group> {


	/**
	 * 查询全部的组织机构
	 *@return
	 */
	public List<Group> findAll(){
		String str = "";		
		return UserUtils.getGroupList(str, str);
	}

	public List<Group> findList(Boolean isAll,String compId,String parentId){
		if (isAll != null && isAll){
			return UserUtils.getGroupAllList(compId,parentId);
		}else{
			return UserUtils.getGroupList(compId,parentId);
		}
	}

	/**
	 * 根据对应的参数  查询想要的组织机构信息
	 */
	@Transactional(readOnly = true)
	public List<Group> findList(Group group){
		List<Group> list = new ArrayList<Group>();
		if(group != null){
			/*	group.setParentIds(group.getParentIds()+"%");*/
			group.setGroupPath(group.getGroupPath()+"%");
			list = dao.findByParentIdsLike(group);
		}
		return  list;
	}

	/**
	 * 保存或修改组织机构
	 *@param group
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateGroup(Group group) {
		super.save(group);
		UserUtils.removeCache(UserUtils.CACHE_GROUP_LIST);
	}

	/**
	 * 删除组织机构
	 *@param group
	 */
	@Transactional(readOnly = false)
	public void deleteGroup(Group group) {
		super.delete(group);
		UserUtils.removeCache(UserUtils.CACHE_GROUP_LIST);
	}

	@Transactional(readOnly = false)
	public List<Group> findListByTreeBox(Integer companyId) throws Exception{
		return UserUtils.findListByTreeBox(companyId);
	}

	/**
	 * 查询父节点下直属子节点
	 *@param prentId
	 *@return
	 *@throws Exception
	 */
	public List<Group> findListByPrentId(Group group) throws Exception{
		return dao.findListByPrentId(group);
	}

	/**
	 * 根据条件查询组织机构信息
	 *@param prentId
	 *@return
	 *@throws Exception
	 */
	public List<Group> findListByGroup(Group group) throws Exception{
		return dao.findListByGroup(group);
	}

	/**
	 * 通过path like的方式查询组织机构，可以根据group_type进行筛选
	 * @author Prosper
	 * @param group
	 * @return
	 */
	public List<Group> findByPathLike(Group group)
	{
	    return dao.findByPathLike(group);
	}
	
	/**
	 * 查询当前组织机构的第一个部门机构
	 * @Title: findFirstDeptGroup
	 * @Description: TODO
	 * @param group
	 * @return
	 * @return: Group
	 */
	public Group findFirstDeptGroup(Group group){
		if("department".equals(group.getGroupType())){
			return group;
		}
		String[] groupPaths = group.getGroupPath().split("-");
		//倒叙 查询父节点
		for (int i=groupPaths.length-1 ; i>-1 ; i--){
			group = dao.get(group.getParentId());
			if(null == group){
				return null;
			}
			if("department".equals(group.getGroupType())){
				return group;
			}
		}
		return null;
	}
	
	/**
	 * 查询当前组织机构的第一个公司机构
	 * @Title: findFirstCompanyGroup
	 * @Description: TODO
	 * @param group
	 * @return
	 * @return: Group
	 */
	public Group findFirstCompanyGroup(Group group){
		if("company".equals(group.getGroupType())){
			return group;
		}
		String[] groupPaths = group.getGroupPath().split("-");
		//倒叙 查询父节点
		for (int i=groupPaths.length-1 ; i>-1 ; i--){
			group = dao.get(group.getParentId());
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
	 * 根据当前组织机构查询所属   事业部下的  公司
	 * @Title: findGroups
	 * @Description: TODO
	 * @param group
	 * @return
	 * @throws Exception
	 * @return: List<Group>
	 */
	public List<Group> findGroups(Group group) throws Exception{
		if (group == null) {
			return null;
		}
		if (StringUtils.isEmpty(group.getBusinessLineCode())) {
			group = dao.get(group.getId());
		}
		List<Group> groups = new ArrayList<>();
		if (!StringUtils.isEmpty(group.getBusinessLineCode())) {
			groups = dao.findChildRenByPrentGroup(group);
		}
		return groups;
	}

	/**
	 * 根据当前组织机构查询当前公司所属的第一个事业部
	 * @Title: findBusinessDeptGroups
	 * @Description: TODO
	 * @param group
	 * @return
	 * @throws Exception
	 * @return: List<Group>
	 */
	public Group findBusinessDeptGroups(Group group) throws Exception{
		Group s_group = null;
		//判断当前传入的group是否为 事业部
		if (group == null) {
			return null;
		}
		if (StringUtils.isEmpty(group.getBusinessLineCode())) {
			group = dao.get(group.getId());
		}
		if (!StringUtils.isEmpty(group.getBusinessLineCode())) {
			s_group = group;
		}else{
			//如果传入的值不是事业部，获取当前节点往上找的第一个事业部
			String[] groupPaths = group.getGroupPath().split("-");
			//倒叙 查询父节点
			for (int i=groupPaths.length-1 ; i>-1 ; i--){
				group = dao.get(group.getParentId());
				if(null == group){
					return null;
				}
				if (!StringUtils.isEmpty(group.getBusinessLineCode())) {
					s_group = group;
					break;
				}
			}
		}
		return s_group;
	}

	public List<Group> queryChilds(Integer groupId) {
		return dao.queryChilds(groupId);
	}

}
