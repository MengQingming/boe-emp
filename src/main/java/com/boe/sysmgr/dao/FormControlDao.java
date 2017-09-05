package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.FormControl;

/**
 * @description：表单控制
 * @author：guoq
 * @created：2016-12-20 17:02:12
 * @version 1.0
 */
@com.boe.common.persistence.annotation.MyBatisDao
public interface FormControlDao extends CrudDao<FormControl> {

	FormControl getFormControlByName(FormControl form);


}
