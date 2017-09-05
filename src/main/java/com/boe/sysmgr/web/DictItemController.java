package com.boe.sysmgr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.common.utils.StringUtils;
import com.boe.sysmgr.entity.Company;
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

import com.boe.common.persistence.Page;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.App;
import com.boe.sysmgr.entity.DictItem;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.AppService;
import com.boe.sysmgr.service.CompanyService;
import com.boe.sysmgr.service.DictItemService;
import com.boe.sysmgr.service.DictService;
import com.boe.sysmgr.utils.UserUtils;



/**
* <p>Description:DictItemController 数据字典项</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:32:22
* @version: 1.0
*/
@Controller
@RequestMapping(value = "${adminPath}/sys/dictItem")
public class DictItemController extends BaseController {

	@Autowired
	private DictItemService dictItemService;
	@Autowired
	private DictService dictService;
	@Autowired 
	private AppService appService;
	@Autowired 
	private CompanyService companyService;
	
	@ModelAttribute
	public DictItem get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return dictItemService.get(id);
		}else{
			return new DictItem();
		}
	}
	@RequiresPermissions("sys:dictItem:view")
	@RequestMapping(value = "")
	public String index(String dictCode,String appNo,Model model){
		DictItem dictItem = new DictItem();
		dictItem.setStatus("1");
		dictItem.setAppNo(appNo);
		dictItem.setDictCodeQuery(dictCode);
		model.addAttribute("dictItem", dictItem);
		return "modules/sys/dictItem/index";
	}
	
	/**
	 * 数据字典项列表
	 *@param dictItem
	 *@param request
	 *@param response
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:dictItem:view")
	@RequestMapping(value = "list")
	public String list(DictItem dictItem, String dictCode,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(dictCode)){
			dictItem.setDictCodeQuery(dictCode);
			Dict dict = new Dict();
			dict.setDictCode(dictCode);
			dictItem.setDict(dict);
		}
		User user = UserUtils.getUser();
		if(!user.isAdmin()){
			dictItem.setCompanyId(user.getCompany().getId());
		}
		if(StringUtils.isBlank(dictItem.getLanguage())){
			dictItem.setLanguage("zh_CN");
		}
        Page<DictItem> page = dictItemService.findPage(new Page<DictItem>(request, response), dictItem); 
        model.addAttribute("page", page);
        List<App> apps = appService.findList(new App());
        model.addAttribute("APPMAP", getAppMap(apps));
		return "modules/sys/dictItem/list";
	}
	/**
	 * 进入数据字典项新增或修改
	 *@param dictItem
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:dictItem:view")
	@RequestMapping(value = "form")
	public String form(DictItem dictItem, Model model,HttpServletRequest request) {
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		if(StringUtils.isBlank(dictItem.getStatus())){
			dictItem.setStatus("1");
		}
		model.addAttribute("dictItem", dictItem);
		String query = request.getParameter("query");
		if("details".equals(query)){
			return "modules/sys/dictItem/view";
		}
		return "modules/sys/dictItem/edit";
	}
	/**
	 * 保存
	 *@param dictItem
	 *@param model
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:dictItem:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(DictItem dictItem, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, dictItem)){
			return form(dictItem, model,request);
		}
		Dict dict = new Dict();
		if(!"".equals(dictItem.getDict().getId())){
			dict = dictService.get(dictItem.getDict().getId());
			dictItem.setDict(dict);
		}
		if(null == dictItem.getId() || dictItem.getId() < 0){
			DictItem dictItem2 = new DictItem();
			dictItem2.setAppNo(dictItem.getAppNo());
			dictItem2.setDict(dictItem.getDict());
			dictItem2.setDictCodeQuery(dict.getDictCode());
			dictItem2.setLanguage(dictItem.getLanguage());
			dictItem2.setItemCode(dictItem.getItemCode());
			dictItem2.setItemValue(dictItem.getItemValue());
			List<DictItem> items =new ArrayList<DictItem>();
			items = dictItemService.findList(dictItem2);
			if(items.size() > 0){
				addMessage(redirectAttributes, "该字典项已存在'" + dictItem.getItemValue() + "'");
				return "redirect:" + adminPath + "/sys/dictItem/?repage";
			}
		}
		if(!"".equals(dictItem.getCompanyId())){
			Company company = new Company();
			company = companyService.get(dictItem.getCompanyId());
			dictItem.setCompanyName(company.getCompanyName());
			dictItem.setCompanyNo(company.getCompanyNo());
		}
		if(StringUtils.isBlank(dictItem.getStatus())){
			dictItem.setStatus("1");
		}
		dictItem.setCreationDate(new Date());
		dictItem.setLastUpdateDate(new Date());
		dictItemService.saveOrUpdateDictItem(dictItem);
		addMessage(redirectAttributes, "保存字典'" + dictItem.getItemValue() + "'成功");
		return "redirect:" + adminPath + "/sys/dictItem/?repage";
	}
	
	/**
	 * 删除字典项
	 *@param dictItem
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:dictItem:edit")
	@RequestMapping(value = "delete")
	public String delete(DictItem dictItem, RedirectAttributes redirectAttributes) {
		dictItemService.deleteDictItem(dictItem);
		addMessage(redirectAttributes, "删除字典成功");
		return "redirect:" + adminPath + "/sys/dictItem/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<DictItem> listData(@RequestParam(required=false) String itemCode) {
		DictItem d = new DictItem();
		d.setItemCode(itemCode);
		return dictItemService.findList(d);
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
	 * 进入数据字典项新增或修改
	 *@param dictItem
	 *@param model
	 *@return
	 */
	@ResponseBody
	@RequiresPermissions("sys:dictItem:view")
	@RequestMapping(value = "getDictByAppNo")
	public Object getDictByAppNo(String appNo, Model model) {
		 Dict dict = new Dict();
		 if(!StringUtils.isBlank(appNo)){
			 dict.setAppNo(appNo);
		 }
		 List<Dict> dicts = dictService.findList(dict);
		return dicts;
	} 
}
