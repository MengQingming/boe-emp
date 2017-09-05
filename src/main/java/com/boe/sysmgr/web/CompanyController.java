package com.boe.sysmgr.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.common.utils.StringUtils;
import com.boe.sysmgr.entity.Company;
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
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.CompanyService;
import com.boe.sysmgr.utils.UserUtils;



/**
* <p>Description:CompanyController 公司</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:31:53
* @version: 1.0
*/
@Controller
@RequestMapping(value = "${adminPath}/sys/company")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;
	
	private SimpleDateFormat myFmt1=new SimpleDateFormat("yy-MM-dd"); 
	
	@ModelAttribute
	public Company get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return companyService.get(id);
		}else{
			return new Company();
		}
	}
	
	@RequiresPermissions("sys:company:view")
	@RequestMapping(value = "")
	public String index(Model model){
		return "modules/sys/company/index";
	}
	
	/**
	 * 公司列表
	 *@param company
	 *@param request
	 *@param response
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:company:view")
	@RequestMapping(value = "list")
	public String list(Company company, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(!user.isAdmin()){
			company.setId(user.getCompany().getId());
		}
		Page<Company> page = companyService.findPage(new Page<Company>(request, response), company); 
        model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
        model.addAttribute("page", page);
		return "modules/sys/company/list";
	}
	
	/**
	 * 进入公司新增或者修改界面
	 *@param company
	 *@param model
	 *@return
	 */
	@RequiresPermissions("sys:company:view")
	@RequestMapping(value = "form")
	public String form(Company company, Model model,HttpServletRequest request) {
		model.addAttribute("s_appNo", getAppNo());
		model.addAttribute("language", getLanguage());
		if(StringUtils.isBlank(company.getStatus())){
			company.setStatus("1");
		}
		model.addAttribute("company", company);
		String query = request.getParameter("query");
		if("details".equals(query)){
			return "modules/sys/company/view";
		}
		return "modules/sys/company/edit";
	}
	/**
	 * 保存
	 *@param company
	 *@param model
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:company:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(Company company, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, company)){
			return form(company, model,request);
		}
		company.setCreationDate(new Date());
		company.setLastUpdateDate(new Date());
		if(null == company.getId() || company.getId() <1 ){
			Company company2 = new Company();
			company2.setCompanyNo(company.getCompanyNo());
			List<Company> companies = companyService.findList(company2);
			if(companies.size() > 0 ){
				addMessage(redirectAttributes, "该公司已存在'" + company.getCompanyNo() + "'");
				return "redirect:" + adminPath + "/sys/company/?repage&companyNo="+company.getCompanyNo();
			}
			
			try {
				company.setStartDate(myFmt1.parse("1990-01-01"));
				company.setEndDate(myFmt1.parse("2099-12-31"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		companyService.saveOrUpdateCompany(company);
		addMessage(redirectAttributes, "保存公司'" + company.getCompanyName() + "'成功");
		return "redirect:" + adminPath + "/sys/company/?repage&stutas="+company.getStatus();
	}
	
	/**
	 * 删除公司
	 *@param company
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:company:edit")
	@RequestMapping(value = "delete")
	public String delete(Company company, RedirectAttributes redirectAttributes) {
		companyService.deleteCompany(company);
		addMessage(redirectAttributes, "删除公司成功");
		return "redirect:" + adminPath + "/sys/company/?repage&stutas="+company.getStatus();
	}
	
	/**
	 * 公司编号
	 *@param companyNo
	 *@return
	 */
	@ResponseBody
	@RequiresPermissions("sys:company:edit")
	@RequestMapping(value = "validateCompany")
	public String validateCompany(String companyNo,Integer id){
		String[] str= companyNo.split(",");
		Integer ind = str.length-1;
		Company company = new Company();
		company.setCompanyNo(str[ind]);
		List<Company> companies = companyService.findList(company);
		if(companies.size() > 0){
			if(companies.get(0).getId().equals(id)){
				return "true";
			}
			return "false";
		}
		return "true";
	}
	
	/**
	 * 查询公司编号
	 * @author guoq
	 * @param companyNo
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryCompanyNo")
	public String queryCompanyNo(Integer id){
		Company company = new Company();
		company.setId(id);
		company = companyService.get(company);
		String jsonString = "{\"companyNo\":\""+company.getCompanyNo()+"\",\"companyName\":\""+company.getCompanyName()+"\"}";
		return jsonString;
	}
}
