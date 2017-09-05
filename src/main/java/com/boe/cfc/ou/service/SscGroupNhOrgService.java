package com.boe.cfc.ou.service;

import com.boe.common.service.CrudService;
import com.boe.cfc.ou.dao.SscGroupNhOrgDao;
import com.boe.cfc.ou.entity.SscGroupNhOrg;
import com.boe.cfc.ou.entity.SscGroupNhOu;
import com.boe.sysmgr.entity.Group;
import com.boe.sysmgr.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  NewHope项目 OU Service
 * <p>Description: SscGroupNhOrgService </p>
 * <p>Company:T-ark </p>
 * @author:  xt
 * @created: 2017/1/12
 * @version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class SscGroupNhOrgService extends CrudService<SscGroupNhOrgDao, SscGroupNhOrg> {

	@Autowired
	private SscGroupNhOrgDao nhOrgDao;

	@Autowired
	private GroupService groupService;
	/**
	 *
	 * 通过 组织机构ID 获取 OU信息,
	 * 如 传递的机构 无OU 信息, 向该机构的所有父节点 查找,
	 * 直到查询到 OU 信息跳出
	 * 无 OU 情况返回 NULL
	 *
	 * @param groupId 组织机构 ID
	 * @return
	 */

	public SscGroupNhOrg findSscGroupNhOrgByGroupId(Integer groupId){
		Group group = groupService.get(groupId);

		String[] groupPaths = group.getGroupPath().split("-");
		//倒叙 查询父节点
		for (int i=groupPaths.length-1 ; i>-1 ; i--){
			SscGroupNhOrg nhOrg =  nhOrgDao.findOrgByGroupId(Integer.valueOf(groupPaths[i]));

			if (nhOrg!=null) {
				return nhOrg;
			}
		}
		return null;
	}

	/**
	 * 根据查询条件 查询 ou 列表
	 * @param nhOu
	 * @return
	 */
	public List<SscGroupNhOu> findOuList(SscGroupNhOu nhOu){
		return nhOrgDao.findOuList(nhOu);
	}

	/**
	 * 根据 id 更新 关联字段ouId
	 * @param nhOrg
	 */
	public void updateOrgAndOu(SscGroupNhOrg nhOrg){
		nhOrgDao.updateOrgAndOu(nhOrg);
	}

	/**
	 * 根据机构id 查询是否有OU关联信息
	 * @param groupId
	 * @return
	 */
	public SscGroupNhOrg findOrgByGroupId(Integer groupId){
		return nhOrgDao.findOrgByGroupId(groupId);
	}


}
