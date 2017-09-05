package com.boe.sysmgr.dao;

import java.util.List;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.Menu;


/**
 * @description: 菜单DAO接口
 * @author: 
 * @created: 2016-12-01 10:30:28
 * @version: 1.0
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

	/**
	 * @description: 根据父级节点进行模糊搜索
	 * @param  Menu
	 * @return List<Menu>
	 */
	public List<Menu> findByParentIdsLike(Menu menu);

	/**
	 * @description: 根据用户ID获取菜单信息
	 * @param  Menu
	 * @return List<Menu>
	 */
	public List<Menu> findByUserId(Menu menu);
	
	/**
	 * @description: 更新菜单父级节点关联信息
	 * @param  Menu
	 */
	public int updateParentIds(Menu menu);
	
	/**
	 * @description: 修改菜单的排列顺序
	 * @param  Menu
	 */
	public int updateSort(Menu menu);
	
}
