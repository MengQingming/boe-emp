package com.boe.scheduler.web;

import com.alibaba.fastjson.JSONObject;
import com.boe.common.persistence.Page;
import com.boe.scheduler.entity.SchedulerJob;
import com.boe.scheduler.scheduling.SpringTaskConfigurer;
import com.boe.common.web.BaseController;
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

/**
 * @ClassName: SchedulerJobController
 * @Description: TODO 调度任务基础信息表
 * @author WangShengDong
 * @date 2017年3月23日 下午8:32:30
 */
@Controller
@RequestMapping(value = "${adminPath}/scheduler/job")
public class SchedulerJobController extends BaseController {

	@Autowired
	private SchedulerJobService schedulerJobService;
	
	@Autowired
	private SpringTaskConfigurer springTaskConfigurer;

	@ModelAttribute
	public SchedulerJob get(@RequestParam(required = false) Integer id) {
		if (null != id && id > 0) {
			return this.schedulerJobService.get(id);
		} else {
			return new SchedulerJob();
		}
	}

	@RequiresPermissions("scheduler:job:view")
	@RequestMapping(value = "")
	public String index(Model model) {
		return "modules/scheduler/job/index";
	}

	@RequiresPermissions("scheduler:job:view")
	@RequestMapping(value = "list")
	public String list(SchedulerJob schedulerJob, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SchedulerJob> page = schedulerJobService.findPage(new Page<SchedulerJob>(request, response), schedulerJob);
		model.addAttribute("page", page);
		return "modules/scheduler/job/list";
	}

	@RequiresPermissions("scheduler:job:view")
	@RequestMapping(value = "form")
	public String form(SchedulerJob schedulerJob, Model model) {
		return "modules/scheduler/job/form";
	}

	/**
	 * @param job
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("scheduler:job:edit")
	@RequestMapping(value = "save")
	public JSONObject save(SchedulerJob job) {
		JSONObject jsonObject = new JSONObject();
		Boolean success = false;
		try {
			SchedulerJob saveJob;
			if (!job.getIsNewRecord()) {
				saveJob = schedulerJobService.get(job.getId());
				saveJob.setJobCode(job.getJobCode());
				saveJob.setJobName(job.getJobName());
				saveJob.setJobMessage(job.getJobMessage());
				saveJob.setJobPath(job.getJobPath());
				saveJob.setJobMethod(job.getJobMethod());
				saveJob.setJobIp(job.getJobIp());
				saveJob.setJobStatus(job.getJobStatus());
			} else {
				saveJob = job;
			}
			job.setJobLock("noLock");
			schedulerJobService.saveOrUpdate(saveJob);
			success = true;
		} catch (Exception e) {
			jsonObject.put("error", e.toString());
		}
		jsonObject.put("success", success);
		return jsonObject;
	}

	@ResponseBody
	@RequiresPermissions("scheduler:job:edit")
	@RequestMapping(value = "manual")
	public JSONObject manual(SchedulerJob job) {
		JSONObject jsonObject = new JSONObject();
		Boolean success = false;
		try {
			springTaskConfigurer.taskRegistrarRun(job.getJobCode(), "M");
			success = true;
		} catch (Exception e) {
			jsonObject.put("error", e.getMessage());
		}
		jsonObject.put("success", success);
		return jsonObject;
	}
	
	@ResponseBody
	@RequiresPermissions("scheduler:job:edit")
	@RequestMapping(value = "openLock")
	public JSONObject openLock(SchedulerJob job) {
		JSONObject jsonObject = new JSONObject();
		Boolean success = false;
		try {
			schedulerJobService.jobCompletion(job);
			success = true;
		} catch (Exception e) {
			jsonObject.put("error", e.getMessage());
		}
		jsonObject.put("success", success);
		return jsonObject;
	}
	
	@RequiresPermissions("scheduler:job:view")
	@RequestMapping(value = "taskResult")
	public String taskResult() {
		springTaskConfigurer.taskResult();
		return "modules/scheduler/job/index";
	}
	
}
