/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.demand.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.boe.common.config.Global;
import com.boe.common.persistence.Page;
import com.boe.common.utils.StringUtils;
import com.boe.common.web.BaseController;
import com.boe.modules.demand.entity.EmpDemandCommon;
import com.boe.modules.demand.service.EmpDemandCommonService;
import com.boe.scheduler.entity.SchedulerJob;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * demandController
 * @author YYY
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/demand/empDemandCommon")
public class EmpDemandCommonController extends BaseController {

	@Autowired
	private EmpDemandCommonService empDemandCommonService;
	
	@ModelAttribute
	public EmpDemandCommon get(@RequestParam(required=false) Integer id) {
		EmpDemandCommon entity = null;
		if (id != null){
			entity = empDemandCommonService.get(id);
		}
		if (entity == null){
			entity = new EmpDemandCommon();
		}
		return entity;
	}
	
	@RequiresPermissions("demand:empDemandCommon:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmpDemandCommon empDemandCommon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EmpDemandCommon> page = empDemandCommonService.findPage(new Page<EmpDemandCommon>(request, response), empDemandCommon);
		model.addAttribute("page", page);
		return "modules/emp/demand/empDemandCommonList1";
	}

	@RequiresPermissions("demand:empDemandCommon:view")
	@RequestMapping(value = "form")
	public String form(EmpDemandCommon empDemandCommon, Model model) {
		model.addAttribute("empDemandCommon", empDemandCommon);
		return "modules/emp/demand/empDemandCommonForm";
	}

	@RequiresPermissions("demand:empDemandCommon:edit")
	@RequestMapping(value = "save")
	public String save(EmpDemandCommon empDemandCommon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, empDemandCommon)){
			return form(empDemandCommon, model);
		}
		empDemandCommonService.save(empDemandCommon);
		addMessage(redirectAttributes, "保存demand成功");
		return "redirect:"+ Global.getAdminPath()+"/demand/empDemandCommon/?repage";
	}
	
	@RequiresPermissions("demand:empDemandCommon:edit")
	@RequestMapping(value = "delete")
	public String delete(EmpDemandCommon empDemandCommon, RedirectAttributes redirectAttributes) {
		empDemandCommonService.delete(empDemandCommon);
		addMessage(redirectAttributes, "删除demand成功");
		return "redirect:"+Global.getAdminPath()+"/demand/empDemandCommon/?repage";
	}
	@RequiresPermissions("demand:empDemandCommon:edit")
	@RequestMapping(value = "deleteBatch1")
	public String deleteBatch1(String ids,RedirectAttributes redirectAttributes) {
		empDemandCommonService.deleteBatch(ids.split(","));
		addMessage(redirectAttributes, "删除demand成功");
		return "redirect:"+Global.getAdminPath()+"/demand/empDemandCommon/?repage";
	}
	@ResponseBody
	@RequiresPermissions("demand:empDemandCommon:edit")
	@RequestMapping(value = "deleteBatch")
	public JSONObject deleteBatch(String ids) {
		JSONObject jsonObject = new JSONObject();
		Boolean success = false;
		try {
			empDemandCommonService.deleteBatch(ids.split(","));
			success = true;
		} catch (Exception e) {
			jsonObject.put("error", e.toString());
		}
		jsonObject.put("success", success);
		return jsonObject;
	}

}