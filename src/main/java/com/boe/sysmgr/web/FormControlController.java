package com.boe.sysmgr.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.common.persistence.Page;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.FormControl;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.FormControlService;
import com.boe.sysmgr.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @description：表单配置
 * @author：guoq
 * @created：2016-12-20 17:02:12
 * @version 1.0
 */

/**
 * 
* <p>Title: FormControlController</p>
* <p>Description: </p>
* <p>Company: </p>
* @author guoq
* @date 2017-1-10上午11:58:45
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/formControl")
public class FormControlController extends BaseController {

	@Autowired
	private FormControlService formControlService;
	private User user;
	
	/**
	 * @description
	 * @author guoq
	 * @created: 2016-12-15 17:27:01
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public FormControl get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return formControlService.get(id);
		}else{
			return new FormControl();
		}
	}
	
	@RequiresPermissions("sys:formControl:view")
	@RequestMapping(value = "")
	public String index(Model model){
		model.addAttribute("user", UserUtils.getUser());
		return "modules/sys/formControl/formControlIndex";
	}
	
	/**
	 * 表单配置列表
	 * @param formControl
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:formControl:view")
	@RequestMapping(value = "list")
	public String list(FormControl formControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		//区分公司
		user = UserUtils.getUser();
		if(!user.getCurrentUser().isAdmin()){
			formControl.setCompanyId(user.getCompany().getId());
		}
        Page<FormControl> page = formControlService.findPage(new Page<FormControl>(request, response), formControl);
        model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
        model.addAttribute("page", page);
		return "modules/sys/formControl/formControlList";
	}
	
	/**
	 * 跳转表单配置修改或新增页面
	 * @param formControl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("sys:formControl:edit")
	@RequestMapping(value = "form")
	public String form(FormControl formControl, Model model, HttpServletRequest request) {
		
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		model.addAttribute("user", UserUtils.getUser());
		
		return "modules/sys/formControl/formControlForm";
	}
	
	/**
	 * 表单配置详情
	 * @param formControl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("sys:formControl:view")
	@RequestMapping(value = "view")
	public String view(FormControl formControl, Model model, HttpServletRequest request) {
		
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		model.addAttribute("user", UserUtils.getUser());
		
		return "modules/sys/formControl/formControlFormQuery";
	}
	
	/**
	 * 表单配置新增或修改
	 * @param formControl
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("i18n:resource:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(FormControl formControl, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, formControl)){
			return form(formControl, model,request);
		}
		formControl.setCreateDate(new Date());
		formControl.setUpdateDate(new Date());
		formControlService.save(formControl);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/sys/formControl/";
	}
	
	/**
	 * 表单配置删除
	 * @param formControl
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("i18n:resource:edit")
	@RequestMapping(value = "delete")
	public String delete(FormControl formControl, RedirectAttributes redirectAttributes) {
		formControlService.delete(formControl);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/sys/formControl/";
	}
}
