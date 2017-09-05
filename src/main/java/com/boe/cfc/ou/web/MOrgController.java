package com.boe.cfc.ou.web;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.boe.cfc.ou.entity.MGroupOrg;
import com.boe.cfc.ou.entity.MOrg;
import com.boe.cfc.ou.service.MGroupOrgService;
import com.boe.cfc.ou.service.MOrgService;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.GroupService;
import com.boe.sysmgr.utils.UserUtils;

/**
 * 第三版OU设置 对应数据库以 M- 开头
 * @author WangShengDong
 * @date 2017年2月17日17:50:53
 */
@Controller
@RequestMapping(value = "${adminPath}/cfc/mOrg")
public class MOrgController extends BaseController {
	
	@Autowired
	private MOrgService mOrgService;

	@Autowired
	private MGroupOrgService groupOrgService;
	
	@Autowired
	private GroupService groupService;

	@ModelAttribute("group")
	public Group get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return groupService.get(id);
		}else{
			return new Group();
		}
	}

	/**
	 *  ou 配置基础页面 跳转
	 */
	@RequiresPermissions("cfc:mOrg:view")
	@RequestMapping(value = "")
	public String index() {
		return "modules/cfc/mOrg/index";
	}


	@RequiresPermissions("cfc:mOrg:edit")
	@RequestMapping(value = "ou_list")
	public String ouList(Group group, Model model) {

		// tree 组织结构
		if(null == group.getGroupPath()){
			group.setGroupPath("");
		}
		if(group.getId() == null){
			group.setId(0);
		}
		MGroupOrg mGroupOrg = groupOrgService.getByGroupId(group.getId());
		mGroupOrg.setGroupId(group.getId());
		mGroupOrg.setGroupNo(group.getGroupNo());
		mGroupOrg.setGroupName(group.getGroupName());
		mGroupOrg.setGroupPath(group.getGroupPath());
		// ou机构查询
		// 通过 机构id 查询是否有OU关联信息
		model.addAttribute("mGroupOrg", mGroupOrg);
		// ou 列表
		MOrg mOrg = new MOrg();
		mOrg.setCompanyId(UserUtils.getUser().getCompany().getId());
		
		model.addAttribute("mOrgList", mOrgService.findList(mOrg));
		
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", UserUtils.getUser().getLang());
		model.addAttribute("group", group);

		return "modules/cfc/mOrg/form";
	}

	@RequiresPermissions("cfc:mOrg:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public JSONObject save(MGroupOrg mGroupOrg) {
		boolean success = false;
		JSONObject jsonObject = new JSONObject();
		try {
			User user=UserUtils.getUser();
			mGroupOrg.setCompanyId(user.getCompany().getId());
			mGroupOrg.setCompanyNo(user.getCompany().getCompanyNo());
			mGroupOrg.setCompanyName(user.getCompany().getCompanyName());
			mGroupOrg.setCreationDate(new Date());
			mGroupOrg.setLastUpdateDate(new Date());
			mGroupOrg.setStatus("1");
			
			groupOrgService.deleteByGroupId(mGroupOrg.getGroupId()); //根据 机构NO 删除 对应的ou 信息
			
			if (mGroupOrg.getOuList() != null) {
				for (Integer ouId : mGroupOrg.getOuList()) {
					MOrg mOrg = mOrgService.get(ouId);
					mGroupOrg.setOrgId(mOrg.getOrgId());
					mGroupOrg.setOrgCode(mOrg.getOrgCode());
					mGroupOrg.setOrgName(mOrg.getOrgName());
					mGroupOrg.setId(null);
					groupOrgService.save(mGroupOrg);
				}
				success = true;
				jsonObject.put("msg", "保存成功");
			} else {
				jsonObject.put("error", "未选择任何OU信息");
			}
		} catch (Exception e) {
			jsonObject.put("error", "保存失败");
			e.getLocalizedMessage();
		}
		jsonObject.put("success", success);
		return jsonObject;
	}


}