package com.boe.sysmgr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.sysmgr.entity.Company;
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
import com.boe.common.utils.StringUtils;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.CompanyService;
import com.boe.sysmgr.service.GroupService;
import com.boe.sysmgr.utils.UserUtils;


/**
* <p>Description:GroupController 组织机构</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:32:35
* @version: 1.0
*/
@Controller
@RequestMapping(value = "${adminPath}/sys/group")
public class GroupController extends BaseController {

	@Autowired
	private GroupService groupService;
	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute("group")
	public Group get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return groupService.get(id);
		}else{
			return new Group();
		}
	}
	
	@RequiresPermissions("sys:group:view")
	@RequestMapping(value = {""})
	public String index(Group group, Model model) {
		return "modules/sys/group/groupIndex";
	}
	/**
	 * 组织机构列表	
	 *@param group
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:group:view")
	@RequestMapping(value = {"list"})
	public String list(Group group, Model model,HttpServletRequest request, HttpServletResponse response) {
		if(null == group.getGroupPath()){
			group.setGroupPath("");
		}
		if(group == null || group.getId() == null){
			group.setId(0);
		}
		Group group2 = new Group();
		group2.setParent(group);
		List<Group> groups = new ArrayList<Group>();
		User user = UserUtils.getUser();
		try {
			if(group.getId() != 0 || user.isAdmin()){
				groups = groupService.findListByPrentId(group2);
			}else{
				groups.add(groupService.get(user.getGroup().getId()));
			}
		} catch (Exception e) {
			logger.error("查询子节点异常:",e);
		} 
	    model.addAttribute("groups", groups);
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", UserUtils.getUser().getLang());
        model.addAttribute("group", group);
        return "modules/sys/group/groupList";
	}
	/**
	 * 进入组织机构新增修改界面
	 *@param group
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:group:view")
	@RequestMapping(value = "form")
	public String form(Group group, Model model, HttpServletRequest request) {
		User user = UserUtils.getUser();
		group.setParent(groupService.get(group.getParent().getId()));
		if (group.getArea()==null){
			if(!user.getCurrentUser().isAdmin()){
				group.setArea(user.getGroup().getArea());
			}
		}
		// 自动获取排序号
		if ((null==group.getId() || group.getId()<0) && group.getParent()!=null){
			int size = 0;
			List<Group> list = groupService.findAll();
			for (int i=0; i<list.size(); i++){
				Group e = list.get(i);
				if (e.getParent()!=null && e.getParent().getId()!=null
						&& e.getParent().getId().equals(group.getParent().getId())){
					size++;
				}
			}
			group.setGroupNo(group.getParent().getGroupNo()+ StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
		}
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", UserUtils.getUser().getLang());
		if(StringUtils.isBlank(group.getStatus())){
			group.setStatus("1");
		}
		model.addAttribute("group", group);
		String query = request.getParameter("query");
		if("details".equals(query)){
			return "modules/sys/group/view";
		}
		return "modules/sys/group/groupForm";
	}
	
	/**
	 * 保存
	 *@param group
	 *@param model
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:group:edit")
	@RequestMapping(value = "save")
	public String save(Group group, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, group)){
			return form(group, model,request);
		}
		if(null == group.getId() || group.getId() <= 0){
			Group group2 = new Group();
			group2.setGroupNo(group.getGroupNo());
			try {
				if(groupService.findListByGroup(group2).size() > 0){
					addMessage(redirectAttributes, "机构："+group.getGroupShortName()+",机构编码'" + group.getGroupNo() + "' 已存在");
					return "redirect:" + adminPath + "/sys/group/list?id="+group.getParentId()+"&parentIds="+group.getParentIds();
				}
			} catch (Exception e){
					logger.error("查询机构信息异常：",e);
			}
		}
		
		
		//设置全路径
		if(null == group.getParent() || "".equals(group.getParent().getId()) || null == group.getParent().getId()){
			group.setGroupPath(group.getGroupNo());
		}else{
			group.setGroupPath(groupService.get(group.getParentId()).getGroupPath()+"-"+group.getGroupNo());
		}
		if(null != group.getCompanyId() || group.getCompanyId() > 0){
			Company company=companyService.get(group.getCompanyId());
			group.setCompanyName(company.getCompanyName());
			group.setCompanyNo(company.getCompanyNo());
		}
        
		group.setCreationDate(new Date());
		group.setLastUpdateDate(new Date());
		
		User user = UserUtils.getUser();
		if(!user.getCurrentUser().isAdmin()){
			group.setCompanyId(user.getCompany().getId());
			group.setCompanyNo(user.getCompany().getCompanyNo());
			group.setCompanyName(user.getCompany().getCompanyName());
		}
		try{
			groupService.saveOrUpdateGroup(group);
			addMessage(redirectAttributes, "保存机构：'" + group.getGroupName() + "' 成功");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Integer id = group.getParentId();
		return "redirect:" + adminPath + "/sys/group/list?id="+id+"&parentIds="+group.getParentIds();
	}
	
	/**
	 * 删除组织机构
	 *@param group
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:group:edit")
	@RequestMapping(value = "delete")
	public String delete(Group group, RedirectAttributes redirectAttributes) {
		groupService.deleteGroup(group);
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + adminPath + "/sys/group/list?id="+group.getParentId()+"&parentIds="+group.getParentIds();
	}

	/**
	 * 获取机构JSON数据。
	 * @param 
	 * @param 
	 * @param 
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData1(@RequestParam(required=false) Boolean isAll,String companyId, String id,HttpServletResponse response,@RequestParam(required=false) String type) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = UserUtils.getUser();
		List<Group> list = new ArrayList<Group>();
		if(StringUtils.isBlank(id)){
			id = "0";
		}
		if(!user.getCurrentUser().isAdmin()){
			companyId = user.getCompany().getId().toString();
		}
		list = groupService.findList(isAll,companyId,id);
		
		for (int i=0; i<list.size(); i++){
			Group e = list.get(i);
			if ("1".equals(e.getDelFlag())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("groupPath", e.getGroupPath());
				map.put("name", e.getGroupName());
				map.put("companyId", e.getCompanyId());
				map.put("isParent", true);
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 获取机构JSON数据。
	 * @param 
	 * @param 
	 * @param 
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData1")
	public List<Map<String, Object>> treeData(Integer companyId) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Group> list = new ArrayList<Group>();
		try {
			list = groupService.findListByTreeBox(companyId);
		} catch (Exception e1) {
			logger.error("查询组织机构异常：",e1);
		}
		
		for (int i=0; i<list.size(); i++){
			Group e = list.get(i);
			if ("1".equals(e.getDelFlag())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("groupPath", e.getGroupPath());
				map.put("name", e.getGroupName());
				map.put("companyId", e.getCompanyId());
				map.put("groupNo", e.getGroupNo());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 组织机构编号
	 *@param companyNo
	 *@return
	 */
	@ResponseBody
	@RequiresPermissions("sys:group:edit")
	@RequestMapping(value = "validateGroup")
	public String validateGroup(String groupNo,Integer id,Integer companyId){
		String[] str= groupNo.split(",");
		Integer ind = str.length-1;
		Group group = new Group();
		group.setGroupNo(str[ind]);
		User user = UserUtils.getUser();
		if(!user.getCurrentUser().isAdmin()){
			companyId = user.getCompany().getId();
		}
		group.setCompanyId(companyId);
		List<Group> groups = new ArrayList<Group>();;
		try {
			groups = groupService.findListByGroup(group);
		} catch (Exception e) {
		}
		if(groups.size() > 0){
			if(groups.get(0).getId().equals(id)){
				return "true";
			}
			return "false";
		}
		return "true";
	}
	
	/**
	 * 删除验证其是否有下级子节点
	 *@param id
	 *@return
	 */
	@ResponseBody
	@RequiresPermissions("sys:group:edit")
	@RequestMapping(value = "validateGroupDel")
	public String validateGroupDel(Integer id){
			Group group = new Group();
			group.setId(id);
			Group group2= new Group();
			group2.setParent(group);
			try {
				List<Group> groups = groupService.findListByPrentId(group2);
				if(groups.size() > 0){
					return "true";
				}
			} catch (Exception e) {
				logger.error("查询子节点异常：",e);
			}
		return "false";
	}
}
