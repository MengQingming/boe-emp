package com.boe.sysmgr.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boe.common.persistence.Page;
import com.boe.common.utils.StringUtils;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.App;
import com.boe.sysmgr.entity.AppVersion;
import com.boe.sysmgr.service.AppService;
import com.boe.sysmgr.service.AppVersionService;



/**
* <p>Description:AppController 应用</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:31:23
* @version: 1.0
*/
@Controller
@RequestMapping(value = "${adminPath}/sys/app")
public class AppController extends BaseController {

	@Autowired
	private AppService appService;
	@Autowired
	private AppVersionService appVersionService;
	
	@ModelAttribute
	public App get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return appService.get(id);
		}else{
			return new App();
		}
	}
	
	@RequiresPermissions("sys:app:view")
	@RequestMapping(value = "")
	public String index(Model model){
		return "modules/sys/app/index";
	}
	
	/**
	 * 查询App应用list
	 *@param app
	 *@param request
	 *@param response
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:app:view")
	@RequestMapping(value = "list")
	public String list(App app, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<App> page = appService.findPage(new Page<App>(request, response), app); 
        model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
        model.addAttribute("page", page);
		return "modules/sys/app/list";
	}
	
	/**
	 * 进入App修改或者新增界面
	 *@param app
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:app:view")
	@RequestMapping(value = "form")
	public String form(App app, Model model,HttpServletRequest request) {
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		if(StringUtils.isBlank(app.getStatus())){
			app.setStatus("1");
		}
		model.addAttribute("app", app);
		String query = request.getParameter("query");
		if("details".equals(query)){
			return "modules/sys/app/view";
		}
		return "modules/sys/app/edit";
	}
	/**
	 * 保存
	 *@param app
	 *@param model
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:app:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(App app, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, app)){
			return form(app, model,request);
		}
		if(null == app.getId() || app.getId() <0){
			App app2 = new App();
			app2.setAppNo(app.getAppNo());
			List<App> apps = appService.findList(app2);
			if(apps.size() > 0){
				addMessage(redirectAttributes, "该app已存在'" + app.getAppNo() + "'");
				return "redirect:" + adminPath + "/sys/app/?repage&appNo="+app.getAppNo();
			}
		}
		app.setCreationDate(new Date());
		app.setLastUpdateDate(new Date());
		if(StringUtils.isBlank(app.getVersionNum())){
			app.setVersionNum("0.1.0-SNAPSHOT");
			appService.saveOrUpdateApp(app);
			AppVersion appVersion = new AppVersion();
			appVersion.setCreationDate(new Date());
			appVersion.setLastUpdateDate(new Date());
			appVersion.setDelFlag("1");
			appVersion.setAppNo(app.getAppNo());
			appVersion.setVersionNum("0.1.0-SNAPSHOT");
			appVersion.setVersionName("第一个版本");
			appVersion.setVersionDate(new Date());
			appVersion.setRemarks("系统默认添加的一个版本");
			appVersionService.saveOrUpdateAppVersion(appVersion);
		}else{
			appService.saveOrUpdateApp(app);
		}
		addMessage(redirectAttributes, "保存App版本'" + app.getAppNo() + "'成功");
		return "redirect:" + adminPath + "/sys/app/?repage&status="+app.getStatus();
	}
	
	/**
	 * 删除APP应用
	 *@param app
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:app:edit")
	@RequestMapping(value = "delete")
	public String delete(App app, RedirectAttributes redirectAttributes) {
		appService.deleteApp(app);
		addMessage(redirectAttributes, "删除app应用成功");
		return "redirect:" + adminPath + "/sys/app/?repage";
	}
	
	/**
	 *AppNo
	 *@param companyNo
	 *@return
	 */
	@ResponseBody
	@RequiresPermissions("sys:app:edit")
	@RequestMapping(value = "validateApp")
	public String validateCompany(String appNo,Integer id){
		String[] str= appNo.split(",");
		Integer ind = str.length-1;
		App app = new App();
		app.setAppNo(str[ind]);
		List<App> apps = appService.findList(app);
		if(apps.size() > 0){
			if(apps.get(0).getId().equals(id)){
				return "true";
			}
			return "false";
		}
		return "true";
	}
}
