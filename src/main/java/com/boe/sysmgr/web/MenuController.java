package com.boe.sysmgr.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boe.common.config.Global;
import com.boe.common.utils.StringUtils;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.Menu;
import com.boe.sysmgr.service.SystemService;
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

/**
 * @description:  菜单Controller
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/menu")
public class MenuController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute("menu")
	public Menu get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return systemService.getMenu(id);
		}else{
			return new Menu();
		}
	}

	/**
	 * @description: 菜单列表
	 * @param 
	 * @return 
	 */
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model) {
		
		List<Menu> list = Lists.newArrayList();
		List<Menu> sourcelist = systemService.findAllMenu();
		Menu.sortList(list, sourcelist, Integer.parseInt(Menu.getRootId()), true);
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
        model.addAttribute("list", list);
		return "modules/sys/menu/menuList";
	}

	/**
	 * @description: 编辑菜单
	 * @param menu
	 * @return 
	 */
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "form")
	public String form(Menu menu, Model model) {
		
		if (menu.getParent()==null||menu.getParent().getId()==null){
			menu.setParent(new Menu(Integer.parseInt(Menu.getRootId())));
		}
		menu.setParent(systemService.getMenu(menu.getParent().getId()));
		// 获取排序号，最末节点排序号+30
		if (null==menu.getId() || menu.getId()<0){
			List<Menu> list = Lists.newArrayList();
			List<Menu> sourcelist = systemService.findAllMenu();
			Menu.sortList(list, sourcelist, menu.getParentId(), false);
			if (list.size() > 0){
				menu.setDisplayOrder(list.get(list.size()-1).getDisplayOrder() + 30);
			}
		}
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("menu", menu);
		return "modules/sys/menu/menuForm";
	}
	
	/**
	 * @description: 菜单详情
	 * @param menu
	 * @return 
	 */
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "formView")
	public String formView(Menu menu, Model model) {
		
		if (menu.getParent()==null||menu.getParent().getId()==null){
			menu.setParent(new Menu(Integer.parseInt(Menu.getRootId())));
		}
		menu.setParent(systemService.getMenu(menu.getParent().getId()));
		// 获取排序号，最末节点排序号+30
		if (null==menu.getId() || menu.getId()<0){
			List<Menu> list = Lists.newArrayList();
			List<Menu> sourcelist = systemService.findAllMenu();
			Menu.sortList(list, sourcelist, menu.getParentId(), false);
			if (list.size() > 0){
				menu.setDisplayOrder(list.get(list.size()-1).getDisplayOrder() + 30);
			}
		}
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("menu", menu);
		return "modules/sys/menu/menuFormView";
	}
	/**
	 * @description: 保存菜单信息
	 * @param menu
	 * @return 
	 */
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "save")
	public String save(Menu menu, Model model, RedirectAttributes redirectAttributes) {
		
		if(!UserUtils.getUser().isAdmin()){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能添加或修改数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
		if (!beanValidator(model, menu)){
			return form(menu, model);
		}
		systemService.saveMenu(menu);
		addMessage(redirectAttributes, "保存菜单'" + menu.getMenuName() + "'成功");
		return "redirect:" + adminPath + "/sys/menu/list?repage";
	}
	
	/**
	 * @description: 删除菜单信息
	 * @param menu
	 * @return 
	 */
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "delete")
	public String delete(Menu menu, RedirectAttributes redirectAttributes) {
		
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
//		if (Menu.isRoot(id)){
//			addMessage(redirectAttributes, "删除菜单失败, 不允许删除顶级菜单或编号为空");
//		}else{
			systemService.deleteMenu(menu);
			addMessage(redirectAttributes, "删除菜单成功");
//		}
		return "redirect:" + adminPath + "/sys/menu/";
	}

	/**
	 * @description: 菜单树结构数据列表
	 * @param parentId
	 * @return 
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "tree")
	public String tree(HttpServletRequest request,Integer parentId) {
		return "modules/sys/menu/menuTree";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(String parentId, Model model) {
		model.addAttribute("parentId", parentId);
		return "modules/sys/menu/menuTreeselect";
	}
	
	/**
	 * 批量修改菜单排序
	 */
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
    	for (int i = 0; i < ids.length; i++) {
    		Menu menu = new Menu(Integer.parseInt(ids[i]));
    		menu.setDisplayOrder(sorts[i]);
    		systemService.updateMenuSort(menu);
    	}
    	addMessage(redirectAttributes, "保存菜单排序成功!");
		return "redirect:" + adminPath + "/sys/menu/";
	}
	
	/**
	 * isShowHide是否显示隐藏菜单
	 * @param extId
	 * @param isShowHidden
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Menu> list = systemService.findAllMenu();
		for (int i=0; i<list.size(); i++){
			Menu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(isShowHide != null && isShowHide.equals("0") && e.getDisplayFlag().equals("0")){
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getMenuName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * @description: 点击菜单切换appNo
	 * @param id
	 * @return 
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "changeAppNo")
	public Object changeAppNo(HttpServletRequest request,String appNo) {
		//将APPNO存储在SESSION中
		if(StringUtils.isNotBlank(appNo)){
			HttpSession session = request.getSession();
			String s_AppNo = (String) session.getAttribute("appNo");
			if(!appNo.equals(s_AppNo)){
				session.setAttribute("appNo", appNo);
			}
		}
		return "";
	}
}
