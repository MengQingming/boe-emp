package com.boe.sysmgr.dao;


import java.util.List;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.User;



 /**
 * @description: 用户DAO接口
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	/**
	 * @description:根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByUserName(User user);

	 /**
	  * @description:根据员工号查询用户
	  * @param user
	  * @return
	  */
	 public User getByUserNum(User user);
	 
	 /**
	  * 
	  * @author Prosper
	  * @param user
	  * @return
	  */
	 public User getOnlyByUserNum(User user);

	/**
	 * @description:通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return List<User>
	 */
	public List<User> findUserByGroupId(User user);
	
	/**
	 * @description:查询全部用户数目
	 * @param User
	 * @return long
	 */
	public long findAllCount(User user);
	
	/**
	 * @description:查询用户和角色是否存在
	 * @param User
	 * @return long
	 */
	public long findUserAndRole(User user);
	
	/**
	 * @description:根据角色ID和groupID查询是否有用户
	 * @param User
	 * @return long
	 */
	public User findRoleAndGroup(User user);
	
	/**
	 * @description: 更新用户密码
	 * @param User
	 * @return int
	 */
	public int updatePasswordById(User user);
	
	/**
	 * @description: 更新登录信息，如：登录IP、登录时间
	 * @param User
	 * @return int
	 */
	public int updateLoginInfo(User user);
	/**
	 * @description: 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * @description: 移除用户和角色的关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserAndRole(User user);
	
	/**
	 * @description: 删除用户全职组织机构数据
	 * @param user
	 * @return
	 */
	public int deleteUserFGroup(User user);
	
	/**
	 * @description: 删除用户兼职组织机构数据
	 * @param user
	 * @return
	 */
	public int deleteUserPGroup(User user);
	
	/**
	 * @description:插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * @description:给角色追加用户
	 * @param user
	 * @return
	 */
	public int insertRoleUser(User user);
	
	/**
	 * @description: 插入用户兼职组织机构数据
	 * @param user
	 * @return
	 */
	public int insertUserPGroup(User user);
	
	/**
	 * @description: 插入用户全职组织机构数据
	 * @param user
	 * @return
	 */
	public int insertUserFGroup(User user);
	
	/**
	 * @description: 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	/**
	 * @description: 获取用户关联的组织机构信息
	 * @param user
	 * @return
	 */
	public List<User> findUserGroupInfo(User user);
	
	/**
	 * 设置上级领导
	 * @param user
	 * @return
	 */
	public int udapteLeader(User user);
	
	/**
	 * 根据用户ID和组织机构ID查询是否已经存在
	 * @param user
	 * @return
	 */
	public long findUserGroupCount(User user);
	
	/**
	 * 通过开始时间和结束时间段查询最后同步时间用户集合
	 * @author Prosper
	 * @param user
	 * @return
	 */
	public List<User> findListByPeriod(User user);
	
	public List<User> findListLL(User user);

	public List<User> queryByGroup(Integer groupId);

	public List<String> findUserIdByRoleId(User user);
	
     List<User> findListOrderByUserNum(User user);
 }
