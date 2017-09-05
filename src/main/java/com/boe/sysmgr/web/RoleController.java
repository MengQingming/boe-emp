package com.boe.sysmgr.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.common.config.Global;
import com.boe.common.persistence.Page;
import com.boe.sysmgr.entity.Role;
import com.boe.sysmgr.service.SystemService;
import com.boe.sysmgr.service.UserService;
import com.boe.sysmgr.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.GroupService;


/**
 * @description:   角色Controller
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/role")
public class RoleController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("role")
	public Role get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return systemService.getRole(id);
		}else{
			return new Role();
		}
	}
	
	/**
	 * @description: 角色列表
	 * @param role
	 * @return 
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = {"list", ""})
	public String list(Role role, Model model) {
		List<Role> list = systemService.findAllRole();
		model.addAttribute("list", list);
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		return "modules/sys/role/roleList";
	}

	/**
	 * @description: 编辑角色信息
	 * @param role
	 * @return 
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "form")
	public String form(Role role, Model model) {
		/*if (role.getGroup()==null){
			role.setGroup(UserUtils.getUser().getGroup());
		}*/
		if(role.getId()!=null && role.getId()>0){
			Role nrole = systemService.getRoleById(role);
			role.setGroup(nrole.getGroup());
			role.setDisplayOrder(nrole.getDisplayOrder());
			role.setRoleType(nrole.getRoleType());
			role.setCompany(nrole.getCompany());
		}
		model.addAttribute("role", role);
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", groupService.findAll());
		return "modules/sys/role/roleForm";
	}
	
	/**
	 * @description: 编辑角色信息
	 * @param role
	 * @return 
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "formView")
	public String formView(Role role, Model model) {
		/*if (role.getGroup()==null){
			role.setGroup(UserUtils.getUser().getGroup());
		}*/
		if(role.getId()!=null && role.getId()>0){
			Role nrole = systemService.getRoleById(role);
			role.setGroup(nrole.getGroup());
			role.setDisplayOrder(nrole.getDisplayOrder());
			role.setRoleType(nrole.getRoleType());
			role.setCompany(nrole.getCompany());
		}
		model.addAttribute("role", role);
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", groupService.findAll());
		return "modules/sys/role/roleFormView";
	}
	/**
	 * @description: 保存角色信息
	 * @param role
	 * @return 
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request, Role role, Model model, RedirectAttributes redirectAttributes) {
		
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if (!beanValidator(model, role)){
			return form(role, model);
		}
		systemService.saveRole(role);
		addMessage(redirectAttributes, "保存角色'" + role.getRoleName() + "'成功");
		return "redirect:" + adminPath + "/sys/role/?repage";
	}
	
	/**
	 * @description: 删除角色信息
	 * @param role
	 * @return 
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "delete")
	public String delete(Role role, RedirectAttributes redirectAttributes) {
		
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
//		if (Role.isAdmin(id)){
//			addMessage(redirectAttributes, "删除角色失败, 不允许内置角色或编号空");
////		}else if (UserUtils.getUser().getRoleIdList().contains(id)){
////			addMessage(redirectAttributes, "删除角色失败, 不能删除当前用户所在角色");
//		}else{
			systemService.deleteRole(role);
			addMessage(redirectAttributes, "删除角色成功");
//		}
		return "redirect:" + adminPath + "/sys/role/?repage";
	}
	
	/**
	 * 角色分配页面
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assign")
	public String assign(HttpServletRequest request, HttpServletResponse response,User user,String userName,Model model) {
		if(user.getRole().getId()!=null && user.getRole().getId()>0){
			Role nrole = systemService.getRoleById(user.getRole());
			user.setRole(nrole);
		}
		Page<User> page = userService.findUser(new Page<User>(request, response), user);
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "modules/sys/role/roleAssign";
	}
	
	/**
	 * 角色分配 -- 打开角色分配对话框
	 * @param role
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "usertorole")
	public String selectUserToRole(Role role, Model model) throws Exception {
		//List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("role", role);
		//model.addAttribute("userList", null);
		//model.addAttribute("selectIds", Collections3.extractToString(userList, "id", ","));
		model.addAttribute("officeList", groupService.findListByTreeBox(role.getCurrentUser().getCompany().getId()));
		return "modules/sys/role/selectUserToRole";
	}
	
	/**
	 * 角色分配 -- 根据部门编号获取用户列表
	 * @param groupId
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions("sys:role:view")
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(HttpServletRequest request,Integer groupId,String userName,HttpServletResponse response) throws UnsupportedEncodingException {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		user.setGroup(new Group(groupId));
		user.setFullName(userName);
		Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
		for (User e : page.getList()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name",e.getFullName()+"("+e.getUserNum()+")");
			mapList.add(map);			
		}
		return mapList;
	}
	
	/**
	 * 角色分配 -- 从角色中移除用户
	 * @param userId
	 * @param roleId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "outrole")
	public String outrole(Integer userId, Integer roleId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+roleId;
		}
		Role role = systemService.getRole(roleId);
		User user = systemService.getUser(userId);
		if (UserUtils.getUser().getId().equals(userId)) {
			addMessage(redirectAttributes, "无法从角色【" + role.getRoleName() + "】中移除用户【" + user.getFullName() + "】自己！");
		}else {
			if (user.getRoleList().size() <= 1){
				addMessage(redirectAttributes, "用户【" + user.getFullName() + "】从角色【" + role.getRoleName() + "】中移除失败！这已经是该用户的唯一角色，不能移除。");
			}else{
				Boolean flag = systemService.outUserInRole(role, user);
				if (!flag) {
					addMessage(redirectAttributes, "用户【" + user.getFullName() + "】从角色【" + role.getRoleName() + "】中移除失败！");
				}else {
					addMessage(redirectAttributes, "用户【" + user.getFullName() + "】从角色【" + role.getRoleName() + "】中移除成功！");
				}
			}		
		}
		return "redirect:" + adminPath + "/sys/role/assign?role.id="+role.getId();
	}
	
	/**
	 * 角色分配
	 * @param role
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assignrole")
	public String assignRole(Role role, Integer[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = systemService.assignUserToRole(role,new User(idsArr[i]));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getUserName()+user.getFullName() + "】到角色【" + role.getRoleName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个用户"+msg);
		return "redirect:" + adminPath + "/sys/role/assign?role.id="+role.getId();
	}

	/**
	 * 验证角色名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		if (name!=null && name.equals(oldName)) {
			return "true";
		} else if (name!=null && systemService.getRoleByName(name) == null) {
			return "true";
		}
		return "false";
	}


}
