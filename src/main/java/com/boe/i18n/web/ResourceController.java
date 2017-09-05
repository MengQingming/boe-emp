package com.boe.i18n.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boe.common.persistence.Page;
import com.boe.common.web.BaseController;
import com.boe.i18n.entity.Resource;
import com.boe.i18n.service.ResourceService;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.utils.UserUtils;

/**
* @description: 国际化管理
* @author: guoq
* @created: 2016-12-15 17:23:16
* @version: 0.2
*/


@Controller
@RequestMapping(value = "${adminPath}/i18n/resource")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService resourceService;
	private User user;
	
	/**
	 * @description
	 * @author guoq
	 * @created: 2016-12-15 17:27:01
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public Resource get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return resourceService.get(id);
		}else{
			return new Resource();
		}
	}
	
	@RequiresPermissions("i18n:resource:view")
	@RequestMapping(value = "")
	public String index(Model model){
		model.addAttribute("user", UserUtils.getUser());
		return "modules/i18n/resource/resourceIndex";
	}
	
	/**
	 * 国际化配置列表
	 * @param resource
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("i18n:resource:view")
	@RequestMapping(value = "list")
	public String list(Resource resource, HttpServletRequest request, HttpServletResponse response, Model model) {
		//区分公司
		user = UserUtils.getUser();
		if(!user.getCurrentUser().isAdmin()){
			resource.setCompanyId(user.getCompany().getId());
		}
        Page<Resource> page = resourceService.findPage(new Page<Resource>(request, response), resource);
        model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
        model.addAttribute("page", page);
		return "modules/i18n/resource/resourceList";
	}
	
	/**
	 * 跳转到国际化修改或新增页面
	 * @param resource
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("i18n:resource:edit")
	@RequestMapping(value = "form")
	public String form(Resource resource, Model model, HttpServletRequest request) {
		
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		model.addAttribute("user", UserUtils.getUser());
		
		return "modules/i18n/resource/resourceForm";
	}
	
	/**
	 * 跳转到国际化配置详情页面
	 * @param resource
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("i18n:resource:view")
	@RequestMapping(value = "view")
	public String view(Resource resource, Model model, HttpServletRequest request) {
		
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		model.addAttribute("user", UserUtils.getUser());
		
		return "modules/i18n/resource/resourceFormQuery";
	}
	
	/**
	 * 保存国际化配置信息
	 * @param resource
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("i18n:resource:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(Resource resource, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, resource)){
			return form(resource, model,request);
		}
		resource.setCreateDate(new Date());
		resource.setUpdateDate(new Date());
		resourceService.save(resource);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/i18n/resource/";
	}
	
	/**
	 * 删除国际化配置信息
	 * @param resource
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("i18n:resource:edit")
	@RequestMapping(value = "delete")
	public String delete(Resource resource, RedirectAttributes redirectAttributes) {
		resourceService.delete(resource);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/i18n/resource/";
	}
}
