package com.boe.sysmgr.dao;

import java.util.List;
import java.util.Map;

import com.boe.common.persistence.TreeDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.Group;

/**
* <p>Description:GroupDao 组织机构dao</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:13:33
* @version: 1.0
*/
@MyBatisDao
public interface GroupDao extends TreeDao<Group> {
	/**
	 * 针对于封装的treeSelect控件
	 *@return
	 *@throws Exception
	 */
	public List<Group> findListByTreeBox(Group group) throws Exception;
	
	/**
	 * 查询父节点下直属子节点
	 *@param group
	 *@return
	 *@throws Exception
	 */
	public List<Group> findListByPrentId(Group group) throws Exception;
	
	/**
	 * 根据条件查询组织结构信息
	 *@param group
	 *@return
	 *@throws Exception
	 */
	public List<Group> findListByGroup(Group group) throws Exception;
	
	/**
	 * 根据父节点查询出其下面所有的子节点
	 * @Title: findChildRenByPrentGroup
	 * @Description: TODO
	 * @param group
	 * @return
	 * @throws Exception
	 * @return: List<Group>
	 */
	public List<Group> findChildRenByPrentGroup(Group group) throws Exception;
	
	/**
	 * 查询当前group的子级组织集合
	 * @author Prosper
	 * @param id 父级的id 
	 * @return
	 */
	public List<Group> findChildrenById(Integer id);
	
	/**
	 * 通过path like的方式查询组织机构，可以根据group_type进行筛选
	 * @author Prosper
	 * @param group
	 * @return
	 */
	public List<Group> findByPathLike(Group group);
	
	/**
	 * 通过开始时间和结束时间区域查询最后同步时间组织机构集合
	 * @author Prosper
	 * @param group
	 * @return
	 */
	public List<Group> findListByPeriod(Group group);
	
	/**
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public Group getByUserIdGroupId(Integer userId,Integer groupId);

	public List<Group> queryChilds(Integer groupId);
	
	/**
	 * 
	 * @param groupNo
	 * @return
	 */
	public Group getByGroupNo(String groupNo);

	/**
	 * 调用存储过程解析同步来的数据
	 * @author Prosper
	 * @datetime 2017年5月11日 下午9:41:11
	 * @param map
	 */
	public void callToSyncGroup(Map<String, Object> map);
	
}