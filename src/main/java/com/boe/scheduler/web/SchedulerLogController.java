package com.boe.scheduler.web;

import com.boe.common.persistence.Page;
import com.boe.common.web.BaseController;
import com.boe.scheduler.service.SchedulerLogService;
import com.boe.scheduler.entity.SchedulerLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: SchedulerLogController 
 * @Description: TODO 任务调度历史记录表
 * @author WangShengDong
 * @date 2017年3月23日 下午8:33:29
 */
@Controller
@RequestMapping(value = "${adminPath}/scheduler/log")
public class SchedulerLogController extends BaseController {

	@Autowired
	private SchedulerLogService schedulerLogService;
	
	@ModelAttribute
	public SchedulerLog get() {
		return new SchedulerLog();
	}
	
	@RequiresPermissions("scheduler:log:view")
	@RequestMapping(value = "")
	public String index(Model model){
		return "modules/scheduler/log/index";
	}
	
	@RequiresPermissions("scheduler:log:view")
	@RequestMapping(value = "list")
	public String list(SchedulerLog schedulerLog, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SchedulerLog> page = this.schedulerLogService.findPage(new Page<SchedulerLog>(request, response), schedulerLog);
        model.addAttribute("page", page);
		return "modules/scheduler/log/list";
	}

}
