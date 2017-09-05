package com.boe.sysmgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boe.common.persistence.Page;
import com.boe.sysmgr.entity.Company;
import com.boe.sysmgr.entity.Role;
import com.boe.sysmgr.utils.DictItemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.common.service.BaseService;
import com.boe.sysmgr.dao.RoleDao;
import com.boe.sysmgr.dao.UserDao;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.utils.UserUtils;


/**
 * @description: 用户管理service
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService{
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private com.boe.sysmgr.dao.GroupDao GroupDao;
	@Autowired
	private GroupService GroupService;
	
	/**
	 * @description: 根据用户的ID获取用户信息
	 * @param id
	 * @return User
	 */
	public User getUserById(Integer id){
		return userDao.get(id);
	}
	
	/**
	 * @description: 获取用户分页列表信息
	 * @param page user
	 * @return Page<User>
	 */
	public Page<User> findUser(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDao.findList(user));
		
		return page;
	}
	
	/**
	 * @description: 保存用户信息
	 * @param user
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		//设置公司信息
		Company company = new Company();
		company.setId(user.getGroup().getCompanyId());
		company.setCompanyName(user.getGroup().getCompanyName());
		company.setCompanyNo(user.getGroup().getCompanyNo());
		user.setCompany(company);
		if (null==user.getId() || user.getId() <= 0){
			user.preInsert();
			user.setPassword(SystemService.entryptPassword(user.getPassword()));
			user.setDisplayOrder(10);
			user.setCreationDate(new Date());
			user.setLastUpdateDate(new Date());
			//职级名称，岗位级别名称，用户类型名称
			saveInformation(user);
			userDao.insert(user);
			//添加全职信息
			userDao.insertUserFGroup(user);
			//添加角色关联
			Group companyGroup = GroupService.findFirstCompanyGroup(user.getGroup());
			List<Role> rlist = new ArrayList<>();
			if(null!=user.getRoleList() && user.getRoleList().size()>0){
				for(Role r : user.getRoleList()){
					r = roleDao.getById(r);
					rlist.add(r);
				}
				user.setRoleList(rlist);
				user.setGroup(companyGroup);
				userDao.insertUserRole(user);
			}
		}else{
			if(user.getPassword()==null || user.getPassword().equals("")){
				user.setPassword(userDao.get(user.getId()).getPassword());
			}else{
				user.setPassword(SystemService.entryptPassword(user.getPassword()));
				user.setLastPasswordDate(new Date());
			}
			user.setLastUpdateDate(new Date());
			//职级名称，岗位级别名称，用户类型名称
			saveInformation(user);
			userDao.update(user);
			//删除角色关联
			userDao.deleteUserRole(user);
			user.setGroupId(user.getGroup().getId());
			long c  = userDao.findUserGroupCount(user);
			if(c<=0){
				//更新全职信息
				userDao.deleteUserFGroup(user);
				
				userDao.insertUserFGroup(user);
			}
			//更新角色关联
			Group companyGroup = GroupService.findFirstCompanyGroup(user.getGroup());
			List<Role> rlist = new ArrayList<>();
			if(null!=user.getRoleList() && user.getRoleList().size()>0){
				for(Role r : user.getRoleList()){
					r = roleDao.getById(r);
					rlist.add(r);
				}
				user.setRoleList(rlist);
				user.setGroup(companyGroup);
				userDao.insertUserRole(user);
			}
			//更新缓存
			UserUtils.clearCache(user);
			UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		}
	}

	private void saveInformation(User user) {
		//岗位级别
		if(user.getPositionCode()!=null && !user.getPositionCode().equals("")){
			String positionName = DictItemUtils.getDictItemValueL(user.getPositionCode(), "sys_user_position", "T", "未知");
			user.setPositionName(positionName);
		}
		//职级代码 sys_user_level
		if(user.getRankCode()!=null && !user.getRankCode().equals("")){
			String rankName = DictItemUtils.getDictItemValueL(user.getRankCode(), "sys_user_level", "T", "未知");
			user.setRankName(rankName);
		}
		//用户类型 sys_user_category
		if(user.getUserTypeCode()!=null && !user.getUserTypeCode().equals("")){
			String userTypeName = DictItemUtils.getDictItemValueL(user.getUserTypeCode(), "sys_user_category", "T", "未知");
			user.setUserTypeName(userTypeName);
		}
		//补助级别 sys_user_allowance_level
		if(user.getAllowanceLevelCode()!=null && !user.getAllowanceLevelCode().equals("")){
			String AllowanceLevelName = DictItemUtils.getDictItemValueL(user.getAllowanceLevelCode(), "sys_user_category", "T", "未知");
			user.setAllowanceLevelName(AllowanceLevelName);
		}
	}

	/**
	 * @description: 标记删除用户信息
	 * @param user
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		userDao.delete(user);
	}
	
	/**
	 * @description: 获取用户信息
	 * @param user
	 * @return List<User>
	 */
	public List<User> findUserGroup(User user){
		return userDao.findUserGroupInfo(user);
	}
	
	/**
	 * @description: 保存用户的兼职组织机构数据
	 * @param user
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void saveUserPGroup(User user){
		userDao.insertUserPGroup(user);
	}
	
	@Transactional(readOnly = false)
	public void deleteUserPGroup(User user){
		userDao.deleteUserPGroup(user);
	}
	
	@Transactional(readOnly = false)
	public void udapteLeader(User user){
		userDao.udapteLeader(user);
	}
	/**
	 * 胖墩用户和组织机构关系是否已经存在
	 * @param user
	 * @return
	 */
	public long findUserGroupCount(User user){
		return userDao.findUserGroupCount(user);
	}

	/**
	 * @description:根据登录名称查询用户
	 * @return
	 */
	public User getByUserName(User user){
		return userDao.getByUserName(user);
	}

	/**
	 * @description:根据员工号查询用户
	 * @return
	 */
	public User getByUserNum(User user){
		return userDao.getByUserNum(user);
	}

	public List<User> findList(User user) {
		return userDao.findList(user);
	}

	public List<User> queryByGroup(Integer groupId) {
		return userDao.queryByGroup(groupId);
	}
	
	public List<User> findAllList() {
		return userDao.findAllList();
	}
}
