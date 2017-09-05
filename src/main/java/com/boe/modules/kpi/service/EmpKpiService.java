/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.kpi.service;

import java.util.List;

import com.boe.modules.kpi.dao.EmpKpiDao;
import com.boe.common.persistence.Page;
import com.boe.common.service.CrudService;
import com.boe.modules.kpi.entity.EmpKpi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * kpiService
 * @author YYY
 * @version 2017-08-14
 */
@Service
@Transactional(readOnly = true)
public class EmpKpiService extends CrudService<EmpKpiDao, EmpKpi> {

	public EmpKpi get(Integer id) {
		return super.get(id);
	}
	
	public List<EmpKpi> findList(EmpKpi empKpi) {
		return super.findList(empKpi);
	}
	
	public Page<EmpKpi> findPage(Page<EmpKpi> page, EmpKpi empKpi) {
		return super.findPage(page, empKpi);
	}
	
	@Transactional(readOnly = false)
	public void save(EmpKpi empKpi) {
		super.save(empKpi);
	}
	
	@Transactional(readOnly = false)
	public void delete(EmpKpi empKpi) {
		super.delete(empKpi);
	}
	
}