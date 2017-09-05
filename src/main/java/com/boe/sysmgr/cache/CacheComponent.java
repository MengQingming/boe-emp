package com.boe.sysmgr.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boe.sysmgr.dao.GroupDao;
import com.boe.sysmgr.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.boe.common.config.Global;
import com.boe.common.utils.StringUtils;
import com.boe.i18n.dao.ResourceDao;
import com.boe.i18n.entity.Resource;
import com.boe.sysmgr.dao.AreaDao;
import com.boe.sysmgr.dao.DictDao;
import com.boe.sysmgr.dao.MenuDao;
import com.boe.sysmgr.dao.RoleDao;
import com.boe.sysmgr.dao.SysParameterDao;
import com.boe.sysmgr.dao.UserDao;
import com.boe.sysmgr.entity.Area;
import com.boe.sysmgr.entity.Dict;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.Menu;
import com.boe.sysmgr.entity.Role;
import com.boe.sysmgr.entity.SysParameter;
import com.boe.sysmgr.entity.User;

@Component
public class CacheComponent {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private DictDao dictDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private SysParameterDao sysParameterDao;
	@Autowired
	private AreaDao areaDao;

	/**
	 * 根据ID获取用户信息并缓存信息
	 * @return User
	 */
	@Cacheable(value = "userCache", key = "'id_'+#id",unless = "#result == null")
	public User getUserById(Integer id) {
		User user = userDao.get(id);
		if(user!=null){
			user.setRoleList(roleDao.findList(new Role(user)));
			return user;
		}
		return null;
	}

	/**
	 * 根据用户名获取用户信息并缓存信息
	 * @return User
	 */
	@Cacheable(value = "userCache", key = "'ln'+#userName",unless = "#result == null")
	public User getUserByUName(String userName) {
		User user = userDao.getByUserName(new User(null, userName));
		if(user!=null){
			user.setRoleList(roleDao.findList(new Role(user)));
			return user;
		}
		return null;
	}
	
	@Cacheable(value = "corpCache", key = "'gp'+#user.id",unless = "#result == null")
	public List<Group> getUserGroups(User user) {
		List<User> us = userDao.findUserGroupInfo(user);
		List<Group> gps = new ArrayList<>();
		if(us.size()>0){
			for(User u : us){
				if(u.getGroup()!=null){
					gps.add(u.getGroup());
				}
			}
		}
		return gps;
	}
	
	@CachePut(value = "corpCache", key = "'gp'+#user.id",unless = "#result == null")
	public List<Group> updateUserGroups(User user) {
		List<User> us = userDao.findUserGroupInfo(user);
		List<Group> gps = new ArrayList<>();
		if(us.size()>0){
			for(User u : us){
				if(u.getGroup()!=null){
					gps.add(u.getGroup());
				}
			}
		}
		return gps;
	}
	/**
	 * 根据用户名获取用户信息并缓存信息
	 * @return User
	 */
	@Cacheable(value = "userCache", key = "'oid_'+#group.id",unless = "#result == null")
	public List<User> findUserByGroupId(Group group) {
		User user = new User();
		user.setGroup(group);
		List<User> list = userDao.findUserByGroupId(user);
		return list;
	}
	
	/**
	 * 根据用户名获取用户信息并缓存信息
	 * @return User
	 */
	@CacheEvict(value = "userCache",key="'oid_'+#group.id")
	public void clearUserByGroupId(Group group){}
	
	/**
	 * 获取当前用户角色列表
	 * 
	 * @return
	 */
	@Cacheable(value = "roleCache", key = "'roleList'+#role.currentUser.company.id",unless = "#result == null")
	public List<Role> getRoleList(Role role) {
		List<Role> roleList = roleDao.findAllList(role);
		return roleList;
	}
	
	/**
	 * 清除角色缓存
	 */
	@CacheEvict(value = "roleCache",allEntries=true)
	public void cleanRoleList(){}
	
	/**
	 * 加载表单语言数据
	 * @return
	 */
	@Cacheable(value = "sysCache", key = "'language'",unless = "#result == null")
	public Map<String, String> loadTexts() {
        Map<String, String> mapResource = new HashMap<String, String>();
        List<Resource> resources = resourceDao.findAllList(new Resource());
        for (Resource item : resources) {
            String code = item.getCode() + "|" + item.getLanguage();
            mapResource.put(code, item.getValue());
        }
        return mapResource;
    }

	/**
	 * 获取用户授权菜单
	 * 
	 * @return
	 */
	@Cacheable(value = "menuCache", key = "'menuList'+#user.id",unless = "#result == null")
	public List<Menu> getMenuList(User user) {
		List<Menu> menuList = null;
		if (user.isAdmin()) {
			menuList = menuDao.findAllList(new Menu());
		} else {
			Menu m = new Menu();
			m.setUserId(user.getId());
			menuList = menuDao.findByUserId(m);
		}
		return menuList;
	}
	
	/**
	 * 清除菜单缓存
	 */
	@CacheEvict(value = "menuCache",allEntries=true)
	public void cleanMenuList(){}
	
	
	@Cacheable(value = "sysCache", key = "'menuNamePathMap'",unless = "#result == null")
	public String findMenuNamePath(String requestUri, String permission){
		String href = StringUtils.substringAfter(requestUri, Global.getAdminPath());
		Map<String, String> menuMap = null;
		if (menuMap == null){
			menuMap = Maps.newHashMap();
			List<Menu> menuList = menuDao.findAllList(new Menu());
			for (Menu menu : menuList){
				// 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
				String namePath = "";
				if (menu.getParentIds() != null){
					List<String> namePathList = Lists.newArrayList();
					for (String id : StringUtils.split(menu.getParentIds(), ",")){
						if (Menu.getRootId().equals(id)){
							continue; // 过滤跟节点
						}
						for (Menu m : menuList){
							if (m.getId().equals(id)){
								namePathList.add(m.getMenuName());
								break;
							}
						}
					}
					namePathList.add(menu.getMenuName());
					namePath = StringUtils.join(namePathList, "-");
				}
				// 设置菜单名称路径
				if (StringUtils.isNotBlank(menu.getUrl())){
					menuMap.put(menu.getUrl(), namePath);
				}else if (StringUtils.isNotBlank(menu.getPermission())){
					for (String p : StringUtils.split(menu.getPermission())){
						menuMap.put(p, namePath);
					}
				}
				
			}
		}
		String menuNamePath = menuMap.get(href);
		if (menuNamePath == null){
			for (String p : StringUtils.split(permission)){
				menuNamePath = menuMap.get(p);
				if (StringUtils.isNotBlank(menuNamePath)){
					break;
				}
			}
			if (menuNamePath == null){
				return "";
			}
		}
		return menuNamePath;
	}

	/**
	 * 组织机构缓存
	 */
	@Cacheable(value = "groupCache", key = "'groupList'+#companyId",unless = "#result == null")
	public List<Group> findListByTreeBox(Integer companyId) throws Exception {
		Group group = new Group();
		if (null != companyId && companyId > 0) {
			group.setCompanyId(companyId);
		}
		return groupDao.findListByTreeBox(group);
	}
	
	/**
	 * 清除组织机构缓存
	 */
	@CacheEvict(value = "groupCache",allEntries=true)
	public void cleanListByTreeBox(){}
	
	/**
	 * 组织机构缓存
	 */
	@Cacheable(value = "sysCache", key = "'dictMap'+#appNo",unless = "#result == null")
	public Map<String, List<Dict>> findDictList(String appNo) {
		Map<String, List<Dict>> dictMap = null;
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getAppNo());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getAppNo(), Lists.newArrayList(dict));
				}
			}
		}
		return dictMap;
	}
	
	@Cacheable(value = "sysCache", key = "'loginFailMap'",unless = "#result == null")
	public Map<String, Integer> findLoginFailMap(){
		Map<String, Integer> loginFailMap = Maps.newHashMap();
		return loginFailMap;
	}
	/**
	 * 移除系统缓存
	 */
	@Caching(evict ={@CacheEvict(value = "userCache"),@CacheEvict(value = "sysCache")})
	public void clearCache(){}
	
	/**
	 * 移除指定用户缓存
	 */
	@Caching(put ={@CachePut(value = "userCache",key = "'id_'+#user.id"),@CachePut(value = "userCache", key = "'ln'+#user.userName")})
	public User clearCache(User user) {
		user = userDao.get(user.getId());
		if(user!=null){
			user.setRoleList(roleDao.findList(new Role(user)));
			return user;
		}
		return null;
	}
	
	/**
	 * 移除指定的系统缓存
	 */
	@CacheEvict(value = "sysCache",key="#cacheKey")
	public void clearSysCache(String cacheKey) {}
	
	/**
	 * 系统参数缓存
	 * @param paramGroup
	 * @return
	 */
	@Cacheable(value = "sysCache", key = "'sysParam'+#paramGroup",unless = "#result == null")
	public List<SysParameter> getSysParam(String paramGroup){
		SysParameter sysParameter = new SysParameter();
		sysParameter.setStatus("1");
		sysParameter.setParamGroup(paramGroup);
		return sysParameterDao.findByGroupStatusName(sysParameter);
	}
	
	/**
	 * 重置系统参数缓存
	 * @param paramGroup
	 * @return
	 */
	@CachePut(value = "sysCache", key = "'sysParam'+#paramGroup",unless = "#result == null")
	public List<SysParameter> updateSysParam(String paramGroup){
		SysParameter sysParameter = new SysParameter();
		sysParameter.setStatus("1");
		sysParameter.setParamGroup(paramGroup);
		return sysParameterDao.findByGroupStatusName(sysParameter);
	}
	
	/**
	 * 地域信息缓存
	 * 
	 */
	@Cacheable(value = "sysCache", key = "'areaList'",unless = "#result == null")
	public List<Area> findListByTreeBox(){
		Area area = new Area();
		//当前登录用户
		User u = UserUtils.getUser();
		if(!u.isAdmin()){
			area.setCompanyId(u.getCompany().getId());
		}
		return areaDao.findListByTreeBox(area);
	}
	
	/**
	 * 清除地域信息缓存
	 */
	@CacheEvict(value = "sysCache",key = "'areaList'")
	public void cleanArea(){}
}
