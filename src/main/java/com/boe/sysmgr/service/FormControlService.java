package com.boe.sysmgr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.dao.FormControlDao;
import com.boe.sysmgr.entity.FormControl;

/**
 * @description：表单控制
 * @author：guoq
 * @created：2016-12-20 17:02:12
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class FormControlService extends CrudService<FormControlDao,FormControl>{

	public FormControl getFormControlByName(FormControl form) {
		return dao.getFormControlByName(form);
	}

}
