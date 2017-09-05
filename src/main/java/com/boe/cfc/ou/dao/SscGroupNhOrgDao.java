package com.boe.cfc.ou.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.cfc.ou.entity.SscGroupNhOrg;
import com.boe.cfc.ou.entity.SscGroupNhOu;

import java.util.List;


/**
 * NewHope项目 OU Dao
 * <p>Description: SscGroupNhOrgDao </p>
 * <p>Company:T-ark </p>
 * @author:  xt
 * @created: 2017/1/12
 * @version: 1.0
 */
@com.boe.common.persistence.annotation.MyBatisDao
public interface SscGroupNhOrgDao extends CrudDao<SscGroupNhOrg> {

	/**
	 * 根据查询条件 查询 ou 列表
	 * @param sscGroupNhOu
	 * @return
	 */
	List<SscGroupNhOu> findOuList( SscGroupNhOu sscGroupNhOu);

	/**
	 * 根据 id 更新 关联字段ouId
	 * @param nhOrg
	 */
	void updateOrgAndOu(SscGroupNhOrg nhOrg);

	/**
	 * 根据机构id 查询是否有Org关联信息
	 * @param groupId
	 * @return
	 */
	SscGroupNhOrg findOrgByGroupId(Integer groupId);

}
