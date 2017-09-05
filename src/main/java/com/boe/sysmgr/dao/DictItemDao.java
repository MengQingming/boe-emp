package com.boe.sysmgr.dao;

import java.util.List;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.Dict;
import com.boe.sysmgr.entity.DictItem;

/**
* <p>Description:DictItemDao 数据字典项dao</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:13:12
* @version: 1.0
*/
@MyBatisDao
public interface DictItemDao extends CrudDao<DictItem> {

	public List<String> findTypeList(DictItem dictItem);
	
	/**
	 * 根据字典组code查询字典项
	 *@param dictItem
	 *@return
	 */
	public List<DictItem> findListByDictCode(DictItem dictItem);
	
	/**
	 * 根据字典组删除字典项
	 *@param dict
	 *@throws Exception
	 */
	public void deleteByDict(Dict dict) throws Exception;
	
	/**
	 * 根据字典组id修改字典项的 dictCode与AppNo
	 *@param dict
	 *@throws Exception
	 */
	public void updateByDict(Dict dict) throws Exception;
}
