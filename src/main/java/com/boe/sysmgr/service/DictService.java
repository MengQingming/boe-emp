package com.boe.sysmgr.service;

import java.util.List;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.entity.Dict;
import com.boe.sysmgr.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.sysmgr.dao.DictDao;

/**
* <p>Description:DictService 数据字典组信息</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:25:28
* @version: 1.0
*/
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	@Autowired
	private CacheComponent cacheComponent;
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}
	/**
	 * 保存或者修改数据字典组
	 *@param dict
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateDict(Dict dict) {
		super.save(dict);
		cacheComponent.clearSysCache(DictUtils.CACHE_DICT_MAP);
	}
	/**
	 * 删除数据字典组
	 *@param dict
	 */
	@Transactional(readOnly = false)
	public void deleteDict(Dict dict) {
		super.delete(dict);
		dao.deleteDictItemByDictId(dict.getId());
		cacheComponent.clearSysCache(DictUtils.CACHE_DICT_MAP);
		cacheComponent.clearSysCache(DictUtils.CACHE_DICTITEM_MAP);
	}

}
