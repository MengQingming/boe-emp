package com.boe.sysmgr.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;


//import org.activiti.engine.IdentityService;
//import org.activiti.engine.identity.Group;
import com.boe.common.security.shiro.session.SessionDAO;
import com.boe.common.web.Servlets;
import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.dao.GroupDao;
import com.boe.sysmgr.entity.Company;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.common.config.Global;
import com.boe.common.persistence.Page;
import com.boe.common.security.Digests;
import com.boe.common.service.BaseService;
import com.boe.common.service.ServiceException;
import com.boe.common.utils.Encodes;
import com.boe.common.utils.StringUtils;
import com.boe.sysmgr.dao.CompanyDao;
import com.boe.sysmgr.dao.MenuDao;
import com.boe.sysmgr.dao.RoleDao;
import com.boe.sysmgr.dao.UserDao;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.Menu;
import com.boe.sysmgr.entity.Role;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.security.SystemAuthorizingRealm;
import com.boe.sysmgr.utils.LogUtils;
import com.boe.sysmgr.utils.UserUtils;

/**
 * @description: 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private SessionDAO sessionDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private SystemAuthorizingRealm systemRealm;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private CacheComponent cacheComponent;
	
	public SessionDAO getSessionDao() {
		return sessionDao;
	}

	//@Autowired
	//private IdentityService identityService;

	//-- User Service --//
	
	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	public User getUser(Integer id) {
		return UserUtils.get(id);
	}

	public User getUserByRole(String roleNo) {
		User user = new User();
		Role role = new Role();
		role.setRoleNo(roleNo);
		role.setRoleType("assignment");
		user.setRole(role);
		List<User> users = findUser(user);
		return users.get(0);
	}
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String userName) {
		return UserUtils.getByUserName(userName);
	}
	
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
	 * 无分页查询人员列表
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user){
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		List<User> list = userDao.findList(user);
		return list;
	}
	
	public List<String> findUserByRoleId(String roleId){
		User user = new User();
		Role role = new Role();
		role.setId(Integer.valueOf(roleId));
		user.setRole(role);
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		List<String> list = userDao.findUserIdByRoleId(user);
		return list;
	}

	/**
	 * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByGroupId(Integer groupId) {
		List<User> list = cacheComponent.findUserByGroupId(new Group(groupId));
		return list;
	}
	
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		if (null==user.getId() || user.getId()<0){
			user.preInsert();
			userDao.insert(user);
		}else{
			// 清除原用户机构用户缓存
			User oldUser = userDao.get(user.getId());
			if (oldUser.getGroup() != null && oldUser.getGroup().getId() != null){
				cacheComponent.clearUserByGroupId(oldUser.getGroup());
			}
			// 更新用户数据
			user.preUpdate();
			userDao.update(user);
		}

		if (null!=user.getId() && user.getId()>0){
			// 更新用户与角色关联
			userDao.deleteUserRole(user);
			if (user.getRoleList() != null && user.getRoleList().size() > 0){
				userDao.insertUserRole(user);
			}else{
				throw new ServiceException(user.getUserName() + "没有设置角色！");
			}
			// 将当前用户同步到Activiti
			//saveActivitiUser(user);
			// 清除用户缓存
			UserUtils.clearCache(user);
//			// 清除权限缓存
//			systemRealm.clearAllCachedAuthorizationInfo();
		}
	}
	
	@Transactional(readOnly = false)
	public void saveImportUser(User user) {
		user.preInsert();
		userDao.insert(user);
	}
	
	@Transactional(readOnly = false)
	public void updateUserInfo(User user) {
		user.preUpdate();
		userDao.updateUserInfo(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		userDao.delete(user);
		// 同步到Activiti
		//deleteActivitiUser(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public void updatePasswordById(Integer id, String userName, String newPassword) {
		User user = new User(id);
		user.setPassword(entryptPassword(newPassword));
		userDao.updatePasswordById(user);
		// 清除用户缓存
		user.setUserName(userName);
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public void updateUserLoginInfo(User user) {
		// 更新本次登录信息
		user.setLastLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
		user.setLastLoginDate(new Date());
		userDao.updateLoginInfo(user);
	}
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 获得活动会话
	 * @return
	 */
	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}
	
	//-- Role Service --//
	/**
	 * @description: 根据角色ID获取角色信息
	 * @param id
	 * @return Role
	 */
	public Role getRole(Integer id) {
		return roleDao.get(id);
	}
	
	public Role getRoleById(Role role) {
		return roleDao.getById(role);
	}
	
	/**
	 * @description: 根据角色名称获取角色信息
	 * @param name
	 * @return Role
	 */
	public Role getRoleByName(String name) {
		Role r = new Role();
		r.setRoleName(name);
		return roleDao.getByName(r);
	}
	
	/**
	 * @description: 根据角色编号获取角色信息
	 * @param name
	 * @return Role
	 */
	public Role getRoleByRoleNo(String roleNo) {
		Role r = new Role();
		r.setRoleNo(roleNo);
		return roleDao.getRoleByRoleNo(r);
	}
	
	
	/**
	 * @description: 获取角色信息
	 * @param role
	 * @return List<Role>
	 */
	public List<Role> findRole(Role role){
		return roleDao.findList(role);
	}
	
	/**
	 * @description: 获取全部的角色信息
	 * @param 
	 * @return List<Role>
	 */
	public List<Role> findAllRole(){
		return UserUtils.getRoleList();
	}
	
	/**
	 * @description: 保存角色信息
	 * @param Role
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		User user = UserUtils.getUser();
		//获取公司ID
		Integer companyId = null;
		if(user.isAdmin()){
			if(role.getCompany()==null || role.getCompany().getId()==null || role.getCompany().getId()<=0){
				return;
			}
			companyId = role.getCompany().getId();
		}else{
			companyId = user.getCompany().getId();
		}
		//获取公司信息
		Company company = companyDao.get(companyId);
		if(null==company){
			return;
		}
		role.setCompany(company);
		
		//获取组织机构信息
		if(role.getGroup()!=null && role.getGroup().getId()!=null && role.getGroup().getId()>0){
			Group gp = groupDao.get(role.getGroup().getId());
			role.setGroup(gp);
		}
		role.setCreationDate(new Date());
		role.setLastUpdateDate(new Date());
		if (null==role.getId() || role.getId()< 1){
			roleDao.insert(role);
			// 同步到Activiti
			//saveActivitiGroup(role);
		}else{
			role.preUpdate();
			roleDao.update(role);
		}
		// 更新角色与菜单关联
		roleDao.deleteRoleMenu(role);
		//如果是功能角色，则允许添加菜单
		if(role.getRoleType().equals("security-role")){
			if (role.getMenuList().size() > 0){
				roleDao.insertRoleMenu(role);
			}
		}
		// 同步到Activiti
		//saveActivitiGroup(role);
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	/**
	 * @description: 标记删除角色信息
	 * @param Role
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void deleteRole(Role role) {
		roleDao.delete(role);
		// 同步到Activiti
		//deleteActivitiGroup(role);
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);

	}
	
	/**
	 * @description: 用户删除角色
	 * @param Role User
	 * @return  Boolean
	 */
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, User user) {
		user.setRole(role);
		userDao.deleteUserAndRole(user);
		return true;
	}
	
	/**
	 * @description: 用户追加角色
	 * @param Role User
	 * @return  User
	 */
	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, User user) {
		if (user == null){
			return null;
		}
		List<Integer> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		
		user=userDao.get(user.getId());
		
		if(null==user){
			return null;
		}
		user.setRole(role);
		long c = userDao.findUserAndRole(user);
		if(c>0){
			return null;
		}
		userDao.insertRoleUser(user);
		
		return user;
	}

	//-- Menu Service --//
	
	/**
	 * @description: 根据菜单ID获取菜单信息
	 * @param id
	 * @return  Menu
	 */
	public Menu getMenu(Integer id) {
		return menuDao.get(id);
	}

	/**
	 * @description: 获取全部的菜单信息
	 * @param 
	 * @return List<Menu>
	 */
	public List<Menu> findAllMenu(){
		return UserUtils.getMenuList();
	}
	
	/**
	 * @description: 保存菜单信息
	 * @param Menu
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {
		
		// 获取父节点实体
		menu.setParent(this.getMenu(menu.getParent().getId()));
		
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = menu.getParentIds(); 
		
		// 设置新的父节点串
		menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");

		// 保存或更新实体
		if (null==menu.getId() || menu.getId()<0){
			menu.preInsert();
			menuDao.insert(menu);
		}else{
			menu.preUpdate();
			menuDao.update(menu);
		}
		
		// 更新子节点 parentIds
		Menu m = new Menu();
		m.setParentIds("%,"+menu.getId()+",%");
		List<Menu> list = menuDao.findByParentIdsLike(m);
		for (Menu e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			menuDao.updateParentIds(e);
		}
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		// 清除日志相关缓存
		cacheComponent.clearSysCache(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	/**
	 * @description: 更新菜单的排列顺序
	 * @param Menu
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void updateMenuSort(Menu menu) {
		menuDao.updateSort(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
	}

	/**
	 * @description: 标记删除菜单
	 * @param Menu
	 * @return 
	 */
	@Transactional(readOnly = false)
	public void deleteMenu(Menu menu) {
		menuDao.delete(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		cacheComponent.clearSysCache(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}
	
	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 "+Global.getConfig("productName")+"  - Powered By http://www.boe.com\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}
	
	///////////////// Synchronized to the Activiti //////////////////
	
	// 已废弃，同步见：ActGroupEntityServiceFactory.java、ActUserEntityServiceFactory.java

	/**
	 * 是需要同步Activiti数据，如果从未同步过，则同步数据。
	 
	private static boolean isSynActivitiIndetity = true;
	public void afterPropertiesSet() throws Exception {
		//if (!Global.isSynActivitiIndetity()){
		//	return;
		//}
		if (isSynActivitiIndetity){
			isSynActivitiIndetity = false;
	        // 同步角色数据
			List<Group> groupList = identityService.createGroupQuery().list();
			if (groupList.size() == 0){
			 	Iterator<Role> roles = roleDao.findAllList(new Role()).iterator();
			 	while(roles.hasNext()) {
			 		Role role = roles.next();
			 		//saveActivitiGroup(role);
			 	}
			}
			
		 	// 同步用户数据
			List<org.activiti.engine.identity.User> userList = identityService.createUserQuery().list();
			if (userList.size() == 0){
			 	Iterator<User> users = userDao.findAllList(new User()).iterator();
			 	while(users.hasNext()) {
			 		saveActivitiUser(users.next());
			 	}
			}
		}
	}*/
	public void afterPropertiesSet() throws Exception {
		
	}

	public Page<User> findUser1(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDao.findListLL(user));
		return page;
	}
	
	/*
	private void saveActivitiGroup(Role role) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		String groupId = role.getEnname();
		
		// 如果修改了英文名，则删除原Activiti角色
		if (StringUtils.isNotBlank(role.getOldEnname()) && !role.getOldEnname().equals(role.getEnname())){
			identityService.deleteGroup(role.getOldEnname());
		}
		
		Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
		if (group == null) {
			group = identityService.newGroup(groupId);
		}
		group.setName(role.getName());
		group.setType(role.getRoleType());
		identityService.saveGroup(group);
		
		// 删除用户与用户组关系
		List<org.activiti.engine.identity.User> activitiUserList = identityService.createUserQuery().memberOfGroup(groupId).list();
		for (org.activiti.engine.identity.User activitiUser : activitiUserList){
			identityService.deleteMembership(activitiUser.getId(), groupId);
		}

		// 创建用户与用户组关系
		List<User> userList = findUser(new User(new Role(role.getId())));
		for (User e : userList){
			String userId = e.getLoginName();//ObjectUtils.toString(user.getId());
			// 如果该用户不存在，则创建一个
			org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
			if (activitiUser == null){
				activitiUser = identityService.newUser(userId);
				activitiUser.setFirstName(e.getName());
				activitiUser.setLastName(StringUtils.EMPTY);
				activitiUser.setEmail(e.getEmail());
				activitiUser.setPassword(StringUtils.EMPTY);
				identityService.saveUser(activitiUser);
			}
			identityService.createMembership(userId, groupId);
		}
	}

	public void deleteActivitiGroup(Role role) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if(role!=null) {
			String groupId = role.getEnname();
			identityService.deleteGroup(groupId);
		}
	}

	private void saveActivitiUser(User user) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		String userId = user.getLoginName();//ObjectUtils.toString(user.getId());
		org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
		if (activitiUser == null) {
			activitiUser = identityService.newUser(userId);
		}
		activitiUser.setFirstName(user.getName());
		activitiUser.setLastName(StringUtils.EMPTY);
		activitiUser.setEmail(user.getEmail());
		activitiUser.setPassword(StringUtils.EMPTY);
		identityService.saveUser(activitiUser);
		
		// 删除用户与用户组关系
		List<Group> activitiGroups = identityService.createGroupQuery().groupMember(userId).list();
		for (Group group : activitiGroups) {
			identityService.deleteMembership(userId, group.getId());
		}
		// 创建用户与用户组关系
		for (Role role : user.getRoleList()) {
	 		String groupId = role.getEnname();
	 		// 如果该用户组不存在，则创建一个
		 	Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
            if(group == null) {
	            group = identityService.newGroup(groupId);
	            group.setName(role.getName());
	            group.setType(role.getRoleType());
	            identityService.saveGroup(group);
            }
			identityService.createMembership(userId, role.getEnname());
		}
	}

	private void deleteActivitiUser(User user) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if(user!=null) {
			String userId = user.getLoginName();//ObjectUtils.toString(user.getId());
			identityService.deleteUser(userId);
		}
	}*/
	
	///////////////// Synchronized to the Activiti end //////////////////
}
