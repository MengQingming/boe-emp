package com.boe.sysmgr.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.sysmgr.entity.Dict;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boe.common.config.Global;
import com.boe.common.persistence.Page;
import com.boe.common.utils.StringUtils;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.App;
import com.boe.sysmgr.service.AppService;
import com.boe.sysmgr.service.DictItemService;
import com.boe.sysmgr.service.DictService;



/**
* <p>Description:DictController 数据字典组</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:32:07
* @version: 1.0
*/
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;
	@Autowired 
	private AppService appService;
	@Autowired 
	private DictItemService dictItemService;
	
	@ModelAttribute("dict")
	public Dict get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return dictService.get(id);
		}else{
			return new Dict();
		}
	}
	
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "")
	public String index(Model model){
		Dict dict = new Dict();
		dict.setStatus("1");
		return "modules/sys/dict/index";
	}
	/**
	 * 数据字典组列表
	 *@param dict
	 *@param request
	 *@param response
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "list")
	public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> appNoList = dictService.findTypeList();
		List<App> apps = appService.findList(new App());
		
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		model.addAttribute("appNoList", appNoList);
		model.addAttribute("APPMAP", getAppMap(apps));
		
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict); 
        model.addAttribute("page", page);
		return "modules/sys/dict/list";
	}
	/**
	 * 进入数据字典组新增或修改界面
	 *@param dict
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "form")
	public String form(Dict dict, Model model,HttpServletRequest request) {
		List<App> apps = appService.findList(new App());
		
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());model.addAttribute("apps", apps);
		if(StringUtils.isBlank(dict.getStatus())){
			dict.setStatus("1");
		}
		model.addAttribute("dict", dict);
		String query = request.getParameter("query");
		if("details".equals(query)){
			return "modules/sys/dict/view";
		}
		return "modules/sys/dict/edit";
	}
	/**
	 * 保存数据字典组
	 *@param dict
	 *@param model
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(Dict dict, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, dict)){
			return form(dict, model,request);
		}
		
		if(null == dict.getId() || dict.getId() < 0){
			Dict d = new Dict();
			d.setLanguage(dict.getLanguage());
			d.setAppNo(dict.getAppNo());
			d.setDictCode(dict.getDictCode());
			List<Dict> dicts= dictService.findList(d);
			if(dicts.size() > 0){
				addMessage(redirectAttributes, "该字典编码已存在！");
				return "redirect:" + adminPath + "/sys/dict/?repage&dictCode="+dict.getDictCode();
			}
		}
		if(StringUtils.isBlank(dict.getStatus())){
			dict.setStatus("1");
		}
		dict.setCreationDate(new Date());
		dict.setLastUpdateDate(new Date());
		try {
			dictService.saveOrUpdateDict(dict);
			dictItemService.updateByDict(dict);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "保存字典'" + dict.getDictName() + "'成功");
		return "redirect:" + adminPath + "/sys/dict/?repage";
	}
	
	/**
	 * 删除数据字典组
	 *@param dict
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage";
		}
		try {
			dictItemService.deleteByDict(dict);
			dictService.deleteDict(dict);
			addMessage(redirectAttributes, "删除字典成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + adminPath + "/sys/dict/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required=false) String appNo) {
		Dict dict = new Dict();
		dict.setAppNo(appNo);
		return dictService.findList(dict);
	}
	private Map<String, String> getAppMap(List<App> apps){
		Map<String, String> map = new HashMap<String,String>();
		for (int i = 0; i < apps.size(); i++) {
			map.put(apps.get(i).getAppNo(), apps.get(i).getAppName());
		}
		map.put("T", "通用");
		return map;
	}
	/**
	 * 验证通用
	 *@param isGeneral
	 *@return
	 */
	@ResponseBody
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "validateDict")
	public String validateDict(String isGeneral,String appNo){
		if("T".equals(isGeneral) && !StringUtils.isBlank(appNo)){
			return "false";
		}
		if("F".equals(isGeneral) && StringUtils.isBlank(appNo)){
			return "false";
		}
		return "true";
	}
}
