/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.sysmgr.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.common.persistence.Page;
import com.boe.common.utils.StringUtils;
import com.boe.sysmgr.entity.Company;
import com.boe.sysmgr.service.AppService;
import com.boe.sysmgr.service.SysParameterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.boe.common.config.Global;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.App;
import com.boe.sysmgr.entity.SysParameter;
import com.boe.sysmgr.service.CompanyService;
import com.boe.sysmgr.utils.UserUtils;



/**
 * 系统参数Controller
 * @author zhou
 * @version 2016-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/parameter/sysParameter")
public class SysParameterController extends BaseController {

	@Autowired
	private SysParameterService sysParameterService;
	@Autowired
	private AppService appService;
	@Autowired
	private CompanyService companyService;
	
	@ModelAttribute
	public SysParameter get(@RequestParam(required=false) String id) {
		SysParameter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysParameterService.get(Integer.parseInt(id));
		}
		if (entity == null){
			entity = new SysParameter();
		}
		return entity;
	}
	
	/**
	 * 更换语言类别
	 */
	@ResponseBody
	@RequestMapping("changeLan")
	public String changeLan(String language){
		JSONObject json = new JSONObject();
		if(language==null || language.equals("")){
			json.put("status", false);
			return json.toJSONString();
		}
		Session session = UserUtils.getSession();
		session.setAttribute("language", language);
		json.put("status", true);
		return json.toJSONString();
	}
	
	/**
	 * 更换组织机构
	 */
	@ResponseBody
	@RequestMapping("changeGroup")
	public String changeGroup(Integer groupId){
		JSONObject json = new JSONObject();
		if(groupId==null || groupId<=0){
			json.put("status", false);
			return json.toJSONString();
		}
		Session session = UserUtils.getSession();
		session.setAttribute("groupId", groupId);
		json.put("status", true);
		return json.toJSONString();
	}
	
	@RequiresPermissions("parameter:sysParameter:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysParameter sysParameter, HttpServletRequest request, HttpServletResponse response, Model model) {
		//app集合
		List<App> apps =  appService.findList(new App());
		model.addAttribute("apps", apps);
		Page<SysParameter> page = sysParameterService.findPage(new Page<SysParameter>(request, response), sysParameter);
		//获取app的名称  ，为的是列表显示名称
		if(page.getList().size()>0){
			for (int i = 0; i < page.getList().size(); i++) {
				SysParameter s=page.getList().get(i);
				App app=new App();
				app.setAppNo(s.getAppNo());
				appService.findByAppNo(app);
				s.setAppNo(appService.findByAppNo(app).getAppName());
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("sysParameter", sysParameter);
		return "modules/sys/parameter/sysParameterList";
	}

	@RequiresPermissions("parameter:sysParameter:view")
	@RequestMapping(value = "form")
	public String form(SysParameter sysParameter, Model model, HttpServletRequest request) {
		
		//app集合
		List<App> apps =  appService.findList(new App());
		model.addAttribute("apps", apps);
		if(sysParameter.getId()==null){
			sysParameter.setStatus("1");
		}
		String query = request.getParameter("query");
		if (StringUtils.isNotEmpty(query) && query.equals("query")){
			return "modules/sys/parameter/sysParameterFormView";
		}else {
			return "modules/sys/parameter/sysParameterForm";
		}
	//	model.addAttribute("sysParameter", sysParameter);
	//	return "modules/sys/parameter/sysParameterForm";
	}

	/**
	 * 保存系统参数的信息
	 * @param sysParameter
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("parameter:sysParameter:edit")
	@RequestMapping(value = "save")
	public String save(SysParameter sysParameter, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, sysParameter)){
			return form(sysParameter, model,request);
		}
		if(sysParameter.getCompanyId()!=null){
			Company g=companyService.get(sysParameter.getCompanyId());
			sysParameter.setCompanyName(g.getCompanyName());
			sysParameter.setCompanyNo(g.getCompanyNo());
		}
		if(sysParameter.getCreationDate()==null){
			sysParameter.setCreationDate(new Date());
		}
		sysParameter.setLastUpdateDate(new Date());
		sysParameterService.save(sysParameter);
		addMessage(redirectAttributes, "保存系统参数成功");
		return "redirect:"+Global.getAdminPath()+"/parameter/sysParameter/?repage";
	}
	
	/**
	 * 验证名称是否有效
	 * @param oldLoginName
	 * @param paramName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("parameter:sysParameter:edit")
	@RequestMapping(value = "checkParamName")
	public String checkParamName(String paramName,String companyNo,String paramGroup,String appNo,String id) {
		String[] str= paramName.split(",");
		Integer ind = str.length-1;
		paramName=str[ind];
		//根据 paramName companyNo paramGroup appNo查询数据
		if(paramName!=null && companyNo!=null && paramGroup!=null  && appNo!=null){
			List<SysParameter> list=sysParameterService.checkParamName(paramName, companyNo, paramGroup, appNo);
			if(list.size()>0){
				SysParameter s=list.get(0);
				if(s.getId().toString().equals(id)){//等于自己的id  就是修改  不用提示
					return "true";
				}else{
					return "false";//提示
				}
			}else{
				return "true";
			}
			
		}else{
			return "false";
		}
		
		
	}
	
	/**
	 * 根据id  删除
	 * @param sysParameter
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("parameter:sysParameter:edit")
	@RequestMapping(value = "delete")
	public String delete(SysParameter sysParameter, RedirectAttributes redirectAttributes) {
		sysParameterService.delete(sysParameter);
		addMessage(redirectAttributes, "删除系统参数成功");
		return "redirect:"+Global.getAdminPath()+"/parameter/sysParameter/?repage";
	}

}