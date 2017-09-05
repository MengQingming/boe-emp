package com.boe.sysmgr.exception.web;

import com.boe.common.persistence.Page;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.exception.entity.SysException;
import com.boe.sysmgr.exception.service.ExceptionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * 异常处理 Controller
 *
 * @author xt
 * @date 2017年1月4日 10:52:28
 * @version 1.0
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/exc")
public class ExceptionController extends BaseController {

	@Autowired
	private ExceptionService exceptionService;

	@ModelAttribute
	public SysException get(@RequestParam(required=false) Integer id) {
		if (null!=id && id>0){
			return exceptionService.get(id);
		}else{
			return new SysException();
		}
	}

	@RequiresPermissions("sys:exc:view")
	@RequestMapping(value = "")
	public String index(Model model){
		return "modules/sys/exception/index";
	}

	/**
	 * 异常消息
	 * @param sysException
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:exc:view")
	@RequestMapping(value = "findExceptionMessage")
	public String findExceptionMessage(SysException sysException,Model model ) {
		model.addAttribute("sysException", sysException);
		return "modules/sys/exception/exceptionMessage";
	}

	/**
	 * 列表
	 * @param sysException
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:exc:view")
	@RequestMapping(value = {"list"})
	public String list(SysException sysException, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<SysException> page = exceptionService.findPage(new Page<SysException>(request, response), sysException);
		model.addAttribute("page", page);
		return "modules/sys/exception/list";
	}
	/**
	 * 更新 异常状态
	 *@param sysException
	 *@param redirectAttributes
	 *@return
	 */
	@RequiresPermissions("sys:exc:view")
	@RequestMapping(value = "updateExcStatus")
	public String updateExcStatus(SysException sysException, RedirectAttributes redirectAttributes) {
		if (sysException.getStatus().equals("未处理")){
			sysException.setStatus("已处理");
		}
		exceptionService.updateExcStatus(sysException);
		addMessage(redirectAttributes, "异常状态更改成功");
		return "redirect:" + adminPath + "/sys/exc/?repage";
	}

}
