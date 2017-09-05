package com.boe.sysmgr.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.boe.sysmgr.entity.App;
import com.boe.sysmgr.entity.AppVersion;
import com.boe.sysmgr.service.AppService;
import com.boe.sysmgr.service.AppVersionService;



/**
* <p>Description:AppVersionController 应用版本</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:31:38
* @version: 1.0
*/
@Controller
@RequestMapping(value = "${adminPath}/sys/appVersion")
public class AppVersionController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;
	@Autowired
	private AppService appService;
	@ModelAttribute
	public AppVersion get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return appVersionService.get(id);
		}else{
			return new AppVersion();
		}
	}
	
	@RequiresPermissions("sys:appVersion:view")
	@RequestMapping(value = "")
	public String index(String appNo,Model model){
		List<App> apps =  appService.findList(new App());
		model.addAttribute("apps", apps);
		model.addAttribute("appquery", appNo);
		return "modules/sys/appVersion/index";
	}
	
	/**
	 * 版本信息列表
	 *@param appVersion
	 *@param request
	 *@param response
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:appVersion:view")
	@RequestMapping(value = "list")
	public String list(AppVersion appVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<App> apps =  appService.findList(new App());
		Page<AppVersion> page = appVersionService.findPage(new Page<AppVersion>(request, response), appVersion); 
        model.addAttribute("APPMAPS", getAppMap(apps));
        model.addAttribute("page", page);
		return "modules/sys/appVersion/list";
	}
	/**
	 * 进入版本修改或者新增界面
	 *@param appVersion
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:appVersion:view")
	@RequestMapping(value = "form")
	public String form(AppVersion appVersion, Model model,HttpServletRequest request) {
		List<App> apps = appService.findList(new App());
		model.addAttribute("apps", apps);
		model.addAttribute("appVersion", appVersion);
		String query = request.getParameter("query");
		if("details".equals(query)){
			return "modules/sys/appVersion/view";
		}
		return "modules/sys/appVersion/edit";
	}
	/**
	 * 保存
	 *@param appVersion
	 *@param model
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:appVersion:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(AppVersion appVersion, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, appVersion)){
			return form(appVersion, model,request);
		}
		if(appVersionService.findList(appVersion).size()>0 && appVersion.getId() == null){
			addMessage(redirectAttributes, "App:"+appVersion.getAppNo()+ ",'"+ appVersion.getVersionName() + "'版本已存在");
			return "redirect:" + adminPath + "/sys/appVersion/?repage&versionNum="+appVersion.getVersionNum();
		}
		appVersion.setCreationDate(new Date());
		appVersion.setLastUpdateDate(new Date());
		App app = new App();
		//if(StringUtils.isBlank(appVersion.getId())){
			app.setAppNo(appVersion.getAppNo());
			app = appService.findByAppNo(app);
			app.setVersionNum(appVersion.getVersionNum());
			appVersionService.saveOrUpdateAppVersion(appVersion);
			appService.saveOrUpdateApp(app);
		addMessage(redirectAttributes, "保存App版本'" + appVersion.getVersionName() + "'成功");
		return "redirect:" + adminPath + "/sys/appVersion/?repage";
	}
	/**
	 * 删除
	 *@param appVersion
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:appVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(AppVersion appVersion, RedirectAttributes redirectAttributes) {
		appVersionService.deleteAppVersion(appVersion);
		addMessage(redirectAttributes, "删除app版本成功");
		return "redirect:" + adminPath + "/sys/appVersion/?repage";
	}
	
	private Map<String, String> getAppMap(List<App> apps){
		Map<String, String> map = new HashMap<String,String>();
		for (int i = 0; i < apps.size(); i++) {
			map.put(apps.get(i).getAppNo(), apps.get(i).getAppName());
		}
		return map;
	}
}
