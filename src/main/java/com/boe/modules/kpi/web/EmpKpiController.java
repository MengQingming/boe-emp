/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.kpi.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.common.config.Global;
import com.boe.common.persistence.Page;
import com.boe.common.web.BaseController;
import com.boe.modules.kpi.entity.EmpKpi;
import com.boe.modules.kpi.service.EmpKpiService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * kpiController
 * @author YYY
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/kpi/empKpi")
public class EmpKpiController extends BaseController {

	@Autowired
	private EmpKpiService empKpiService;
	
	@ModelAttribute
	public EmpKpi get(@RequestParam(required=false) Integer id) {
		EmpKpi entity = null;
		if (id != null){
			entity = empKpiService.get(id);
		}
		if (entity == null){
			entity = new EmpKpi();
		}
		return entity;
	}
	
	//@RequiresPermissions("kpi:empKpi:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmpKpi empKpi, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EmpKpi> page = empKpiService.findPage(new Page<EmpKpi>(request, response), empKpi);
		model.addAttribute("page", page);
		return "modules/emp/kpi/empKpiList";
	}

	@RequiresPermissions("kpi:empKpi:view")
	@RequestMapping(value = "form")
	public String form(EmpKpi empKpi, Model model) {
		model.addAttribute("empKpi", empKpi);
		return "modules/emp/kpi/empKpiForm";
	}

	@RequiresPermissions("kpi:empKpi:edit")
	@RequestMapping(value = "save")
	public String save(EmpKpi empKpi, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, empKpi)){
			return form(empKpi, model);
		}
		empKpiService.save(empKpi);
		addMessage(redirectAttributes, "保存kpi成功");
		return "redirect:"+ Global.getAdminPath()+"/kpi/empKpi/?repage";
	}
	
	@RequiresPermissions("kpi:empKpi:edit")
	@RequestMapping(value = "delete")
	public String delete(EmpKpi empKpi, RedirectAttributes redirectAttributes) {
		empKpiService.delete(empKpi);
		addMessage(redirectAttributes, "删除kpi成功");
		return "redirect:"+Global.getAdminPath()+"/kpi/empKpi/?repage";
	}

}