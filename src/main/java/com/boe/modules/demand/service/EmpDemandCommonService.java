/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.demand.service;

import java.util.List;

import com.boe.common.persistence.Page;
import com.boe.common.service.CrudService;
import com.boe.modules.demand.dao.EmpDemandCommonDao;
import com.boe.modules.demand.entity.EmpDemandCommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * demandService
 * @author YYY
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class EmpDemandCommonService extends CrudService<EmpDemandCommonDao, EmpDemandCommon> {
	@Autowired
	private EmpDemandCommonDao empDemandCommonDao;

	public EmpDemandCommon get(Integer id) {
		return super.get(id);
	}
	
	public List<EmpDemandCommon> findList(EmpDemandCommon empDemandCommon) {
		return super.findList(empDemandCommon);
	}
	
	public Page<EmpDemandCommon> findPage(Page<EmpDemandCommon> page, EmpDemandCommon empDemandCommon) {
		return super.findPage(page, empDemandCommon);
	}
	
	@Transactional(readOnly = false)
	public void save(EmpDemandCommon empDemandCommon) {
		super.save(empDemandCommon);
	}
	
	@Transactional(readOnly = false)
	public void delete(EmpDemandCommon empDemandCommon) {
		super.delete(empDemandCommon);
	}
	@Transactional(readOnly = false)
	public void deleteBatch(String [] ids) {
		empDemandCommonDao.deleteBatch(ids);
	}
	
	
	
}