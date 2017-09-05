package com.boe.sysmgr.service;

import java.util.List;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.entity.Dict;
import com.boe.sysmgr.entity.DictItem;
import com.boe.sysmgr.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.sysmgr.dao.DictItemDao;

/**
* <p>Description:DictItemService 数据字典项Service</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:22:23
* @version: 1.0
*/
@Service
@Transactional(readOnly = true)
public class DictItemService extends CrudService<DictItemDao, DictItem> {
	
	@Autowired
	private CacheComponent cacheComponent;
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new DictItem());
	}
	/**
	 * 保存或者修改数据字典项信息
	 *@param dictItem
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateDictItem(DictItem dictItem) {
		super.save(dictItem);
		cacheComponent.clearSysCache(DictUtils.CACHE_DICTITEM_MAP);
	}
	/**
	 * 删除数据字典项
	 *@param dictItem
	 */
	@Transactional(readOnly = false)
	public void deleteDictItem(DictItem dictItem) {
		super.delete(dictItem);
		cacheComponent.clearSysCache(DictUtils.CACHE_DICTITEM_MAP);
	}
	/**
	 * 根据数据字典组code查询其对于的数据字典项
	 *@param dictItem
	 *@return
	 */
	public List<DictItem> findListByDictCode(DictItem dictItem){
		return dao.findListByDictCode(dictItem);
	}
	/**
	 * 根据数据字典组删除字典项
	 *@param dict
	 *@throws Exception
	 */
	public void deleteByDict(Dict dict) throws Exception{
		dao.deleteByDict(dict);
	}
	
	/**
	 * 根据数据字典组修改dictCode和appNo
	 *@param dict
	 *@throws Exception
	 */
	public void updateByDict(Dict dict) throws Exception{
		dao.updateByDict(dict);
	}
}
