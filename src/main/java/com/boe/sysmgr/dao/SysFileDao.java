/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.sysmgr.dao;

import java.util.List;

import com.boe.common.persistence.CrudDao;
import com.boe.sysmgr.entity.SysFile;


/**
 * 附件上传DAO接口
 * @author zhou
 * @version 2016-12-12
 */
@com.boe.common.persistence.annotation.MyBatisDao
public interface SysFileDao extends CrudDao<SysFile> {
	public List<SysFile> queryByAppNoAndDataSourceKey(String companyNo,String dataSourceKey,String appNo);
	public List<SysFile> queryByAppNoAndDataSource(String dataSource,String dataSourceKey,String appNo);
	public List<SysFile> checkFileIsExist(String serverPath ,String dataSourceKey);
	public SysFile getCollectionFile(SysFile sysFile);
}