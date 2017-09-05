/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.record.web;

import com.boe.common.config.Global;
import com.boe.common.persistence.Page;
import com.boe.common.utils.StringUtils;
import com.boe.common.web.BaseController;
import com.boe.modules.record.entity.EmpRecordInfo;
import com.boe.modules.record.service.EmpRecordInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * recordController
 * @author YYY
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/record/empRecordInfo")
public class EmpRecordInfoController extends BaseController {

	@Autowired
	private EmpRecordInfoService empRecordInfoService;
	
	@ModelAttribute
	public EmpRecordInfo get(@RequestParam(required=false) Integer id) {
		EmpRecordInfo entity = null;
		if (id != null){
			entity = empRecordInfoService.get(id);
		}
		if (entity == null){
			entity = new EmpRecordInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("record:empRecordInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmpRecordInfo empRecordInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EmpRecordInfo> page = empRecordInfoService.findPage(new Page<EmpRecordInfo>(request, response), empRecordInfo);
		model.addAttribute("page", page);
		return "modules/emp/record/empRecordInfoList";
	}

	@RequiresPermissions("record:empRecordInfo:view")
	@RequestMapping(value = "form")
	public String form(EmpRecordInfo empRecordInfo, Model model) {
		model.addAttribute("empRecordInfo", empRecordInfo);
		return "modules/emp/record/empRecordInfoForm";
	}

	@RequiresPermissions("record:empRecordInfo:edit")
	@RequestMapping(value = "save")
	public String save(EmpRecordInfo empRecordInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, empRecordInfo)){
			return form(empRecordInfo, model);
		}
		empRecordInfoService.save(empRecordInfo);
		addMessage(redirectAttributes, "保存record成功");
		return "redirect:"+ Global.getAdminPath()+"/record/empRecordInfo/?repage";
	}
	
	@RequiresPermissions("record:empRecordInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(EmpRecordInfo empRecordInfo, RedirectAttributes redirectAttributes) {
		empRecordInfoService.delete(empRecordInfo);
		addMessage(redirectAttributes, "删除record成功");
		return "redirect:"+Global.getAdminPath()+"/record/empRecordInfo/?repage";
	}

}