package com.boe.scheduler.web;

import com.alibaba.fastjson.JSONObject;
import com.boe.common.persistence.Page;
import com.boe.common.web.BaseController;
import com.boe.scheduler.entity.SchedulerJob;
import com.boe.scheduler.entity.SchedulerTrigger;
import com.boe.scheduler.scheduling.SpringTaskConfigurer;
import com.boe.scheduler.service.SchedulerTriggerService;
import com.boe.scheduler.service.SchedulerJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SchedulerTriggerController 
 * @Description: TODO 任务调度触发器配置
 * @author WangShengDong
 * @date 2017年3月23日 下午8:34:30
 */
@Controller
@RequestMapping(value = "${adminPath}/scheduler/trigger")
public class SchedulerTriggerController extends BaseController {
	
	private static Map<String, String> triggerTypes = new HashMap<String, String>();
	
	static {
		triggerTypes.put("forCron", "Cron表达式");
		triggerTypes.put("forSleep", "轮询调度");
		triggerTypes.put("forDate", "指定日期");
	}
	
	@Autowired
	private SchedulerTriggerService schedulerTriggerService;
	
	@Autowired
	private SchedulerJobService jobService;
	
	@Autowired
	private SpringTaskConfigurer springTaskConfigurer;

	@ModelAttribute
	public SchedulerTrigger get(@RequestParam(required=false) Integer id, String jobCode) {
		if (null != id && id>0){
			return this.schedulerTriggerService.get(id);
		}else{
			SchedulerTrigger schedulerTrigger = new SchedulerTrigger();
			schedulerTrigger.setJobCode(jobCode);
			return schedulerTrigger;
		}
	}
	
	@RequiresPermissions("scheduler:trigger:view")
	@RequestMapping(value = "")
	public String index(Model model){
		model.addAttribute("triggerTypes", triggerTypes);
		return "modules/scheduler/trigger/index";
	}
	
	@RequiresPermissions("scheduler:trigger:view")
	@RequestMapping(value = "list")
	public String list(SchedulerTrigger schedulerTrigger, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SchedulerTrigger> page = this.schedulerTriggerService.findPage(new Page<SchedulerTrigger>(request, response), schedulerTrigger);
        model.addAttribute("page", page);
		return "modules/scheduler/trigger/list";
	}
	
	@RequiresPermissions("scheduler:trigger:view")
	@RequestMapping(value = "form")
	public String form(SchedulerTrigger schedulerTrigger, Model model) {
		List<SchedulerJob> list = jobService.findList(new SchedulerJob());
		model.addAttribute("jobs", list);
		model.addAttribute("triggerTypes", triggerTypes);
		return "modules/scheduler/trigger/form";
	}
	
	@ResponseBody
	@RequiresPermissions("scheduler:trigger:edit")
	@RequestMapping(value = "save")
	public JSONObject save(SchedulerTrigger trigger){
		JSONObject jsonObject = new JSONObject();
		Boolean success = false;
		try {
			SchedulerTrigger saveTrigger = new SchedulerTrigger();
			if (!trigger.getIsNewRecord()) {
				saveTrigger = schedulerTriggerService.get(trigger.getId());
				saveTrigger.setJobId(trigger.getJobId());
				saveTrigger.setTriggerType(trigger.getTriggerType());
				saveTrigger.setTriggerCron(trigger.getTriggerCron());
				saveTrigger.setTriggerSleep(trigger.getTriggerSleep());
				saveTrigger.setTriggerDate(trigger.getTriggerDate());
				saveTrigger.setTriggerStatus(trigger.getTriggerStatus());
			} else {
				saveTrigger = trigger;
			}
			SchedulerJob job = jobService.get(saveTrigger.getJobId());
			saveTrigger.setJobCode(job.getJobCode());
			saveTrigger.setJobName(job.getJobName());
			
			saveTrigger.setTriggerTypeName(triggerTypes.get(saveTrigger.getTriggerType()));
			
			schedulerTriggerService.saveOrUpdate(saveTrigger);
			success = true;
		} catch (Exception e) {
			jsonObject.put("error", e.getMessage());
		}
		jsonObject.put("success", success);
		return jsonObject;
	}
	
}
