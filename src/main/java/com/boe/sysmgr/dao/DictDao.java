package com.boe.sysmgr.dao;

import java.util.List;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.Dict;
import com.boe.common.persistence.annotation.MyBatisDao;

/**
* <p>Description:DictDao 数据字典dao</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:12:52
* @version: 1.0
*/
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	public void deleteDictItemByDictId(Integer id);
}
