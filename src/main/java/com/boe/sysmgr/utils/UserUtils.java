package com.boe.sysmgr.utils;

import java.util.List;

import com.boe.common.utils.SpringContextHolder;
import com.boe.common.utils.StringUtils;
import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.dao.GroupDao;
import com.boe.sysmgr.entity.Area;
import com.boe.sysmgr.entity.Role;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.boe.common.service.BaseService;
import com.boe.sysmgr.dao.AreaDao;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.Menu;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.security.SystemAuthorizingRealm.Principal;


/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static CacheComponent cacheComponent = SpringContextHolder.getBean(CacheComponent.class);
	

	private static GroupDao groupDao = SpringContextHolder.getBean(GroupDao.class);

	
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	
	
	public static final String CACHE_GROUP_LIST = "groupList";
	public static final String CACHE_GROUP_ALL_LIST = "groupAllList";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */

	public static User get(Integer id){
		User user = cacheComponent.getUserById(id);
		Session session = getSession();
		if(session.getAttribute("groupId")!=null){
			Integer groupId = (Integer) session.getAttribute("groupId");
			List<Group> gps = cacheComponent.getUserGroups(user);
			if(gps!=null && gps.size()>0){
				for(Group gp : gps){
					if(gp.getId().equals(groupId)){
						gp.setSelected(true);
					}else{
						gp.setSelected(false);
					}
				}
				user.setGroupList(gps);
			}
		}
		return user;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param userName
	 * @return 取不到返回null
	 */
	public static User getByUserName(String userName){
		User user = cacheComponent.getUserByUName(userName);
		if(user!=null){
			cacheComponent.getUserById(user.getId());
			Session session = getSession();
			//设置语言
			session.setAttribute("language", user.getLang());
			//设置组织机构
			Integer groupId=0;
			if(user.getGroupId()!=null){
				groupId = user.getGroupId();
			}
			session.setAttribute("groupId", groupId);
		}
		return user;
	}
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		cacheComponent.clearCache();
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		cacheComponent.clearCache(user);
		cacheComponent.updateUserGroups(user);
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){

			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		List<Role> roleList = cacheComponent.getRoleList(new Role());
		return roleList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(){
		User user = getUser();
		List<Menu> menuList = cacheComponent.getMenuList(user);
		return menuList;
	}
	
	/**
	 * 根据公司ID获取公司下的组织机构信息
	 * @return List<Group> 
	 */ 
	public static List<Group> findListByTreeBox(Integer companyId) throws Exception{
		return cacheComponent.findListByTreeBox(companyId);
	}
	
	/**缓存方法到此结束end*/
	
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(Area area){
		return areaDao.findAllList(area);
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Group> getOfficeList(){
		List<Group> groupList = null;
		if (groupList == null){
			User user = getUser();
			if (user.isAdmin()){
				groupList = groupDao.findAllList(new Group());
			}else{
				Group group = new Group();
				group.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				groupList = groupDao.findList(group);
			}
		}
		return groupList;
	}
	

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Group> getOfficeAllList(){
		List<Group> groupList = null;
		if (groupList == null){
			groupList = groupDao.findAllList(new Group());
		}
		return groupList;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key){
//		getCacheMap().remove(key);
		if(CACHE_ROLE_LIST.equals(key)){
			cacheComponent.cleanRoleList();
			//清楚菜单缓存
			cacheComponent.cleanMenuList();
		}else if(CACHE_GROUP_LIST.equals(key)){
			try {
				cacheComponent.cleanListByTreeBox();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(CACHE_MENU_LIST.equals(key)){
			cacheComponent.cleanMenuList();
		}
		getSession().removeAttribute(key);
	}
	//更新角色缓存
	public static void updateRoleCahce() {
		cacheComponent.cleanRoleList();
	}
	
//	public static Map<String, Object> getCacheMap(){
//		Principal principal = getPrincipal();
//		if(principal!=null){
//			return principal.getCacheMap();
//		}
//		return new HashMap<String, Object>();
//	}
	
	
	
	
	
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Group> getGroupAllList(String compId,String parentId){
		List<Group> groupList = null;
		if (groupList == null){
			Group group = new Group();
			if(StringUtils.isNotBlank(compId)){
				group.setCompanyId(Integer.parseInt(compId));
			}
			Group parent= new Group();
			if(StringUtils.isNoneBlank(parentId)){
				parent.setId(Integer.parseInt(parentId));
				group.setParent(parent);
			}
			groupList = groupDao.findAllList(group);
		}
		return groupList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Group> getGroupList(String compId,String parentId){
		List<Group> grouplList = null;
		if (grouplList == null){
			User user = getUser();
			Group group = new Group();
			if(StringUtils.isNotBlank(compId)){
				if(Integer.parseInt(compId) > 0){
					group.setCompanyId(Integer.parseInt(compId));
				}
			}
			Group parent= new Group();
			if(StringUtils.isNoneBlank(parentId)){
				parent.setId(Integer.parseInt(parentId));
				group.setParent(parent);
			}
			if (user.isAdmin()){
				grouplList = groupDao.findAllList(group);
			}else{
				group.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				grouplList = groupDao.findList(group);
			}
		}
		return grouplList;
	}
}
