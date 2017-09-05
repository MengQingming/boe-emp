package com.boe.sysmgr.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.boe.sysmgr.cache.CacheComponent;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.boe.common.utils.StringUtils;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.Area;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.AreaService;
import com.boe.sysmgr.utils.UserUtils;

/**
 * 
* <p>Title: AreaController</p>
* <p>Description: 区域管理</p>
* <p>Company: </p>
* @author guoq
* @date 2017-1-10下午5:07:48
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;
	@Autowired
	private CacheComponent cacheComponent;
	
	@ModelAttribute("area")
	public Area get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return areaService.get(id);
		}else{
			return new Area();
		}
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {""})
	public String index(Area area, Model model) {
		User user = UserUtils.getUser();
		Area companyArea = areaService.getTopArea(user.getCompany().getId());
		model.addAttribute("topId",companyArea.getId());
		return "modules/sys/area/areaIndex";
	}
	
	/**
	* @description: 区域列表
	* @author: guoq
	* @created: 2017-1-10
	* @version: 1.0
	* @param 
	* @return
	 */
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list"})
	public String list(Area area, Model model) {
		Area area2 = new Area();
		if(area.getId()!=null){
			area2.setId(area.getId());
		}
		
		area.setParent(area2);
		List<Area> areas = new ArrayList<Area>();
		try {
			areas = areaService.findListByPrentId(area);
		} catch (Exception e) {
			logger.error("查询子节点异常:",e);
		} 
	    model.addAttribute("areas", areas);
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", UserUtils.getUser().getLang());
        model.addAttribute("area", area);
		return "modules/sys/area/areaList";
	}

	/**
	* @description: 跳转到新增或修改页面
	* @author: guoq
	* @created: 2017-1-10
	* @version: 1.0
	* @param 
	* @return
	 */
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		@SuppressWarnings("unused")
		User u = UserUtils.getUser();
//		if (area.getParent()==null||area.getParent().getId()==null){
//			area.setParent(UserUtils.getUser().getGroup().getArea());
//		}
		if(area.getId()!=null){
			area.setParent(areaService.get(area.getParent().getId()));
		}
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("area", area);
		
		return "modules/sys/area/areaForm";	
	}
	
	/**
	* @description: 跳转到区域详情页面
	* @author: guoq
	* @created: 2017-1-10
	* @version: 1.0
	* @param 
	* @return
	**/
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "view")
	public String view(Area area, Model model) {
		@SuppressWarnings("unused")
		User u = UserUtils.getUser();
//		if (area.getParent()==null||area.getParent().getId()==null){
//			area.setParent(UserUtils.getUser().getGroup().getArea());
//		}
		if(area.getId()!=null){
			area.setParent(areaService.get(area.getParent().getId()));
		}
		model.addAttribute("language", getLanguage());
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("area", area);
		
		return "modules/sys/area/areaFormQuery";	
	}
	
	/**
	 * 
	* @description: 新增或保存区域信息
	* @author: guoq
	* @created: 2017-1-10
	* @version: 1.0
	* @param 
	* @return 
	*
	 */
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, area)){
			return form(area, model);
		}
		User user = UserUtils.getUser();
		area.setCompanyId(user.getCompany().getId());
		area.setCompanyNo(user.getCompany().getCompanyNo());
		area.setCompanyName(user.getCompany().getCompanyName());
		areaService.saveOrUpdateArea(area);
		//清楚地域缓存
		cacheComponent.cleanArea();
		addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/area/list?id="+area.getParent().getId();
	}
	
	/**
	 * 
	* @description: 删除区域
	* @author: guoq
	* @created: 2017-1-10
	* @version: 1.0
	* @param: AreaController
	* @return: String
	*
	 */
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
		
//		if(Global.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + adminPath + "/sys/area";
//		}
		Integer parentId = area.getParent().getId();
		area.getParent().setId(area.getId());
		List<Area> list = areaService.findListByPrentId(area);
		if(list!=null && list.size()>0){
			addMessage(redirectAttributes, "删除区域失败, 不允许删除顶级区域");
		}else{
			areaService.deleteArea(area);
			addMessage(redirectAttributes, "删除区域成功");
		}
		//清楚地域缓存
		cacheComponent.cleanArea();
		return "redirect:" + adminPath + "/sys/area/list?id="+parentId;
	}

	/**
	 * 
	* @description: 查询区域树（异步）
	* @author: guoq
	* @created: 2017-1-10
	* @version: 1.0
	* @param: 
	* @return: 
	*
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String id, String extId,HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if(StringUtils.isBlank(id)){
			id = "0";
		}
		
		List<Area> list = areaService.findAll(id);
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
			if ("1".equals(e.getDelFlag())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				map.put("code", e.getCode());
				map.put("isParent", true);
				mapList.add(map);
			}
		}
		return mapList;
	}
	/**
	 * 
	* @description: 查询区域树（一次查询全部）
	* @author: guoq
	* @created: 2017-1-10
	* @version: 1.0
	* @param: 
	* @return: 
	*
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData1")
	public List<Map<String, Object>> treeData1(String id, String extId,HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Integer type = 100000;
		if(extId!=null && !extId.equals("")){
			type = Integer.parseInt(extId);
		}
		List<Area> list = cacheComponent.findListByTreeBox();
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
			Integer eType= Integer.parseInt(e.getType());
			if ("1".equals(e.getDelFlag()) && eType<=type){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				map.put("fullname", e.getFullname());
				map.put("code", e.getCode());
				map.put("code", e.getCode());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 删除验证其是否有下级子节点
	 *@param id
	 *@return
	 */
	@ResponseBody
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "validateAreaDel")
	public String validateGroupDel(Integer id){
			Area area = new Area();
			area.setId(id);
			Area area2= new Area();
			area2.setParent(area);
			try {
				List<Area> areas = areaService.findListByPrentId(area2);
				if(areas!=null && areas.size() > 0){
					return "true";
				}
			} catch (Exception e) {
				logger.error("查询子节点异常：",e);
			}
		return "false";
	}
}
