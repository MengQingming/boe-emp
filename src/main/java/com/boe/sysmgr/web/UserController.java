package com.boe.sysmgr.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.boe.common.config.Global;
import com.boe.common.persistence.Page;
import com.boe.common.utils.DateUtils;
import com.boe.common.utils.StringUtils;
import com.boe.common.utils.excel.ExportExcel;
import com.boe.common.utils.excel.ImportExcel;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.SystemService;
import com.boe.sysmgr.service.UserService;
import com.boe.sysmgr.utils.UserUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.boe.common.beanvalidator.BeanValidators;
import com.boe.sysmgr.service.GroupService;

/**
 * @description:  用户Controller
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private CacheComponent cacheComponent;
	
	@ModelAttribute("user")
	public User get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}

	/**
	 * @description: 用户初始页面
	 * @param user
	 * @return 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/sys/user/userIndex";
	}

	/**
	 * @description: 用户分页列表
	 * @param user
	 * @return 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException{
		if(null!=request.getParameter("group.groupName") && request.getMethod().equals("GET")){
			String groupName = new String(request.getParameter("group.groupName").getBytes("ISO8859-1"), "UTF-8");
			Group gp = user.getGroup();
			gp.setGroupName(groupName);
		}
		if(null != user.getGroup() && null != user.getGroup().getId() && user.getGroup().getId() > 0){
			user.setGroup(groupService.get(user.getGroup().getId()));
		}
		Page<User> page = userService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/sys/user/userList";
	}
	
	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"listData"})
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		return page;
	}

	/**
	 * @description: 编辑用户
	 * @param user
	 * @return 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/user/userForm";
	}
	
	/**
	 * @description: 查看用户详情
	 * @param user
	 * @return 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "formView")
	public String formView(User user, Model model) {
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/user/userFormView";
	}

	/**
	 * @description: 保存用户兼职信息
	 * @param user
	 * @return 
	 * @throws Exception 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "partTime")
	public String partTime(User user, Model model) throws Exception {
		user = userService.getUserById(user.getId());
		List<User> us = userService.findUserGroup(user);
		List<Group> gps = new ArrayList<>();
		for(User u : us){
			if(u.getGroup().getRelationType().equals("P")){
				gps.add(u.getGroup());
			}
		}
		model.addAttribute("groups", gps);
		model.addAttribute("user", user);
		
		return "modules/sys/user/userPartTime";
	}
	
	/**
	 * @description: 保存单条用户兼职信息
	 * @param groupId userId
	 * @return json
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "savePartTime")
	public String savePartTime(Integer groupId,Integer userId,Model model){
		JSONObject json = new JSONObject();
		if(groupId==null || groupId<=0){
			json.put("status", false);
			json.put("msg", "组织机构信息不能为空");
			return json.toJSONString();
		}
		if(userId==null || userId<=0){
			json.put("status", false);
			json.put("msg", "用户信息不能为空");
			return json.toJSONString();
		}
		User user  = userService.getUserById(userId);
		if(user==null){
			json.put("status", false);
			json.put("msg", "用户信息不存在");
			return json.toJSONString();
		}
		Group group = groupService.get(groupId);
		if(group==null){
			json.put("status", false);
			json.put("msg", "组织机构信息不存在");
			return json.toJSONString();
		}
		user.setGroupId(groupId);
		long c = userService.findUserGroupCount(user);
		if(c>0){
			json.put("status", false);
			json.put("msg", "兼职信息已存在，不可重复添加");
			return json.toJSONString();
		}
		List<Group> gps = new ArrayList<>();
		gps.add(group);
		user.setGroupList(gps);
		userService.saveUserPGroup(user);
		//更新机构缓存
		cacheComponent.updateUserGroups(user);
		json.put("status", true);
		json.put("msg", "兼职信息添加成功");
		return json.toJSONString();
	}
	
	/**
	 * @description: 单条删除用户兼职信息
	 * @param groupId userId
	 * @return json
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "deletePartTime")
	public String deletePartTime(Integer groupId,Integer userId,Model model){
		JSONObject json = new JSONObject();
		if(groupId==null || groupId<=0){
			json.put("status", false);
			json.put("msg", "组织机构信息不能为空");
			return json.toJSONString();
		}
		if(userId==null || userId<=0){
			json.put("status", false);
			json.put("msg", "用户信息不能为空");
			return json.toJSONString();
		}
		User user  = userService.getUserById(userId);
		if(user==null){
			json.put("status", false);
			json.put("msg", "用户信息不存在");
			return json.toJSONString();
		}
		Group group = groupService.get(groupId);
		if(group==null){
			json.put("status", false);
			json.put("msg", "组织机构信息不存在");
			return json.toJSONString();
		}
		user.setGroup(group);
		userService.deleteUserPGroup(user);
		//更新缓存
		cacheComponent.updateUserGroups(user);
		json.put("status", true);
		json.put("msg", "兼职信息删除成功");
		return json.toJSONString();
	}
	/**
	 * @description: 保存用户信息
	 * @param user
	 * @return 
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		
		if(null!=user.getGroup().getId() && user.getGroup().getId()>0){
			Group gp = groupService.get(user.getGroup().getId());
			user.setGroup(gp);
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		if ("false".equals(checkLoginName("",user.getUserName(),null)) && user.getId()==null){
			addMessage(redirectAttributes, "用户名重复，请重新输入！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 保存用户信息
		userService.saveUser(user);
		addMessage(redirectAttributes, "保存用户'" + user.getUserName() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	/**
	 * @description: 标记删除用户信息
	 * @param user
	 * @return 
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		userService.deleteUser(user);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<User> page = userService.findUser(new Page<User>(request, response, -1), user);
    		new ExportExcel("用户数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list){
				try{
					Group gp = groupService.get(user.getGroupId());
					if ("true".equals(checkLoginName("",user.getUserName(),null)) && null!=gp){
						user.setPassword(SystemService.entryptPassword("123456"));
						user.setLang("zh_CN");
						user.setDisplayOrder(10);
						user.setCreationDate(new Date());
						user.setLastUpdateDate(new Date());
						user.setGroup(gp);
						BeanValidators.validateWithException(validator, user);
						userService.saveUser(user);
						successNum++;
					}else if(gp==null){
						failureMsg.append("<br/>机构组织ID "+user.getGroupId()+" 不存在关联组织机构 ");
						failureNum++;
					}else{
						failureMsg.append("<br/>登录名 "+user.getUserName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getUserName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getUserName()+" 导入失败："+ex.getMessage());
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<User> list = Lists.newArrayList(); list.add(UserUtils.getUser());
    		new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String unStr,String userName,Integer id) {
		String[] str= userName.split(",");
		Integer ind = str.length-1;
		User u = systemService.getUserByLoginName(str[ind]);
		if (userName !=null && u == null) {
			return "true";
		}else if(u!=null && u.getId().equals(id)){
			return "true";
		}
		return "false";
	}

	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user,String leaderinfo,HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getFullName())){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/user/userInfo";
			}
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			currentUser.setLastLoginDate(new Date());
			//设置上级信息
			JSONArray jsona = JSONArray.parseArray(leaderinfo);
			if(jsona.size()>0){
				for(int i=0;i<jsona.size();i++){
					JSONObject json = jsona.getJSONObject(i);
					User paramUser = new User();
					Integer groupId = (Integer) json.get("groupId");
					Integer leaderId = (Integer) json.get("leaderId");
					User parentU = systemService.getUser(leaderId);
					if(parentU!=null){
						paramUser.setId(currentUser.getId());
						paramUser.setParentId(parentU.getId());
						paramUser.setParentNum(parentU.getUserNum());
						paramUser.setParentFullName(parentU.getFullName());
						paramUser.setGroupId(groupId);
						userService.udapteLeader(paramUser);
					}
				}
			}
			systemService.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/user/userInfo";
	}

	/**
	 * 返回用户信息
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	
	/**
	 * 修改个人用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/user/userModifyPwd";
			}
			if (SystemService.validatePassword(oldPassword, user.getPassword())){
				systemService.updatePasswordById(user.getId(), user.getUserName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/user/userModifyPwd";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) Integer groupId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();

		List<User> list = systemService.findUserByGroupId(groupId);

		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", groupId);
			map.put("name", StringUtils.replace(e.getFullName(), " ", "")+"("+e.getUserNum()+")");
			mapList.add(map);
		}
		return mapList;
	}
    
	/**
	 * 绑定上级 -- 打开绑定对话框
	 * @param role
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "selectUserParent")
	public String selectUserParent(User user, Model model) throws Exception {
		model.addAttribute("user", user);
		model.addAttribute("officeList", groupService.findListByTreeBox(user.getCurrentUser().getCompany().getId()));
		return "modules/sys/user/selectUserParent";
	}
//	@InitBinder
//	public void initBinder(WebDataBinder b) {
//		b.registerCustomEditor(List.class, "roleList", new PropertyEditorSupport(){
//			@Autowired
//			private SystemService systemService;
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				String[] ids = StringUtils.split(text, ",");
//				List<Role> roles = new ArrayList<Role>();
//				for (String id : ids) {
//					Role role = systemService.getRole(Long.valueOf(id));
//					roles.add(role);
//				}
//				setValue(roles);
//			}
//			@Override
//			public String getAsText() {
//				return Collections3.extractToString((List) getValue(), "id", ",");
//			}
//		});
//	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "getUserById")
	public String getUserById(@RequestParam(required=false) Integer id,Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "modules/ef/common/userInfo";
	}
	/**
	 * 选择报账的人
	 * @param role
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "selectUserEf")
	public String selectUserEf(User user, Model model) throws Exception {
		model.addAttribute("user", user);
		model.addAttribute("officeList", groupService.findListByTreeBox(user.getCurrentUser().getCompany().getId()));
		return "modules/ef/common/selecUserEf";
	}
	
}
