/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.cfc.ou.web;

import com.boe.cfc.ou.entity.SscGroupNhOrg;
import com.boe.cfc.ou.entity.SscGroupNhOu;
import com.boe.cfc.ou.service.SscGroupNhOrgService;
import com.boe.common.config.Global;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.GroupService;
import com.boe.sysmgr.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 *  组织结构 OU Controller
 * <p>Description: SscGroupOuController </p>
 * <p>Company:T-ark </p>
 * @author:  xt
 * @created: 2017/1/12
 * @version: 1.0
 */
@Controller
	@RequestMapping(value = "${adminPath}/cfc/ou")
public class SscGroupNhOuController extends BaseController {

	@Autowired
	private SscGroupNhOrgService sscGroupNhOuService;
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
		@RequiresPermissions("groupOrg:sscGroupOrg:view")
	@RequestMapping(value = {"index"})
	public String index() {
		return "modules/cfc/sscGroupNhOu/groupOuIndex";
	}


	@RequiresPermissions("groupOrg:sscGroupOrg:edit")
	@RequestMapping(value = {"ou_list", ""})
	public String ouList(Group group, Model model) {

		// tree 组织结构
		if(null == group.getGroupPath()){
			group.setGroupPath("");
		}
		if(group.getId() == null){
			group.setId(0);
		}
		// ou机构查询
		// 通过 机构id 查询是否有OU关联信息
		SscGroupNhOrg nhOrg = sscGroupNhOuService.findOrgByGroupId(group.getId());

		if (nhOrg == null){
			nhOrg = new SscGroupNhOrg();
			nhOrg.setGroupId(group.getId());
			nhOrg.setGroupName(group.getGroupName());
			nhOrg.setGroupNo(group.getGroupNo());
			nhOrg.setOuId(0);
		}

		// ou 列表
		List<SscGroupNhOu> sscGroupNhOuList = sscGroupNhOuService.findOuList(new SscGroupNhOu());

		model.addAttribute("sscGroupNhOuList", sscGroupNhOuList);
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", UserUtils.getUser().getLang());
		model.addAttribute("group", group);
		model.addAttribute("nhOrg", nhOrg);

		return "modules/cfc/sscGroupNhOu/groupOuForm";
	}

	@RequiresPermissions("groupOrg:sscGroupOrg:edit")
	@RequestMapping(value = "save")
	public String save(SscGroupNhOrg nhOrg, Model model, RedirectAttributes redirectAttributes) {
		// 更新
		if (nhOrg.getId() != null){
			sscGroupNhOuService.updateOrgAndOu(nhOrg);
		}else {
			User user=UserUtils.getUser();
			nhOrg.setCompanyId(user.getCompany().getId());
			nhOrg.setCompanyNo(user.getCompany().getCompanyNo());
			nhOrg.setCompanyName(user.getCompany().getCompanyName());

			nhOrg.setCreationDate(new Date());
			nhOrg.setLastUpdateDate(new Date());
			nhOrg.setStatus("1");
			sscGroupNhOuService.save(nhOrg);
		}

		addMessage(redirectAttributes, "保存机构OU成功");
		return "redirect:"+ Global.getAdminPath()+"/cfc/ou/ou_list?repage&id="+nhOrg.getGroupId();
	}


}