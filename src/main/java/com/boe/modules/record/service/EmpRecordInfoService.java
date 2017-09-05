/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.modules.record.service;

import com.boe.common.persistence.Page;
import com.boe.common.service.CrudService;
import com.boe.modules.record.dao.EmpRecordInfoDao;
import com.boe.modules.record.entity.EmpRecordInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * recordService
 * @author YYY
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class EmpRecordInfoService extends CrudService<EmpRecordInfoDao, EmpRecordInfo> {

	public EmpRecordInfo get(Integer id) {
		return super.get(id);
	}
	
	public List<EmpRecordInfo> findList(EmpRecordInfo empRecordInfo) {
		return super.findList(empRecordInfo);
	}
	
	public Page<EmpRecordInfo> findPage(Page<EmpRecordInfo> page, EmpRecordInfo empRecordInfo) {
		return super.findPage(page, empRecordInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(EmpRecordInfo empRecordInfo) {
		super.save(empRecordInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(EmpRecordInfo empRecordInfo) {
		super.delete(empRecordInfo);
	}
	
}