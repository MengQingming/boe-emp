/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.sysmgr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.common.persistence.Page;
import com.boe.common.service.CrudService;
import com.boe.sysmgr.dao.SysFileDao;
import com.boe.sysmgr.entity.SysFile;


/**
 * 附件上传Service
 * @author zhou
 * @version 2016-12-12
 */
@Service
@Transactional(readOnly = true)
public class SysFileService extends CrudService<SysFileDao, SysFile> {
	@Autowired
	private SysFileDao sysFileDao;
	public SysFile get(Integer id) {
		return super.get(id);
	}
	
	public List<SysFile> findList(SysFile sysFile) {
		return super.findList(sysFile);
	}
	
	
	public Page<SysFile> findPage(Page<SysFile> page, SysFile sysFile) {
		return super.findPage(page, sysFile);
	}
	
	@Transactional(readOnly = false)
	public void save(SysFile sysFile) {
		super.save(sysFile);
	}
	/**
	 * 获取附件列表不分页
	 * @param companyNo
	 * @param dataSourceKey
	 * @param appNo
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<SysFile> queryByAppNoAndDataSourceKey(String companyNo,String dataSourceKey,String appNo){
		return sysFileDao.queryByAppNoAndDataSourceKey(companyNo, dataSourceKey, appNo);
	}
	@Transactional(readOnly = false)
	public List<SysFile> queryByAppNoAndDataSource(String dataSource,String dataSourceKey,String appNo){
		return sysFileDao.queryByAppNoAndDataSource(dataSource, dataSourceKey, appNo);
	}
	@Transactional(readOnly = false)
	public void delete(SysFile sysFile) {
		super.delete(sysFile);
	}
	@Transactional(readOnly = false)
	public List<SysFile> checkFileIsExist(String serverPath ,String dataSourceKey){
		return sysFileDao.checkFileIsExist(serverPath, dataSourceKey);
	}
	@Transactional(readOnly = false)
	public SysFile getCollectionFile(SysFile sysFile){
		return sysFileDao.getCollectionFile(sysFile);
	}
	
}