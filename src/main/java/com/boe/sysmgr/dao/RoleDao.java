package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.Role;

/**
 * @description: 角色DAO接口
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@com.boe.common.persistence.annotation.MyBatisDao
public interface RoleDao extends CrudDao<Role> {

	
	/**
	 * @description: 根据角色编号进行搜索
	 * @param  Role
	 * @return Role
	 */
	public Role getRoleByRoleNo(Role role);
	
	/**
	 * @description: 根据角色名称进行模糊搜索
	 * @param  Role
	 * @return Role
	 */
	public Role getByName(Role role);
	
	/**
	 * @description: 根据角色ID进行搜索
	 * @param  Role
	 * @return Role
	 */
	public Role getById(Role role);
	
	/**
	 * @description: 根据角色的英文名称进行查询
	 * @param  Role
	 * @return Role
	 */
	public Role getByEnname(Role role);

	/**
	 * @description: 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	/**
	 * @description:添加角色和菜单的关系
	 * @param  Role
	 * @return 
	 */
	public int insertRoleMenu(Role role);
	
}
