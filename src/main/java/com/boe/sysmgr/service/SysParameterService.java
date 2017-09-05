/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.sysmgr.service;

import java.util.List;

import com.boe.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.dao.SysParameterDao;
import com.boe.sysmgr.entity.SysParameter;


/**
 * 系统参数Service
 * @author zhou
 * @version 2016-12-12
 */
@Service
@Transactional(readOnly = true)
public class SysParameterService extends CrudService<SysParameterDao, SysParameter> {
	@Autowired
	private SysParameterDao sysParameterDao;
	public SysParameter get(Integer id) {
		return super.get(id);
	}
	
	public List<SysParameter> findList(SysParameter sysParameter) {
		return super.findList(sysParameter);
	}
	
	/**   
	 * 根据参数组名称与参数名称集合查询
	 * @Title: queryByGroupNames   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param paramGroup
	 * @param: @param paramNames
	 * @param: @return  
	 * @author baipan     
	 * @date 2016年12月23日 下午1:41:47
	 * @return: List<SysParameter>      
	 * @throws   
	 */  
	public List<SysParameter> queryByGroupNames(String paramGroup, List<String> paramNames) {
		return sysParameterDao.queryByGroupNames(paramGroup, paramNames);
	}
	
	public Page<SysParameter> findPage(Page<SysParameter> page, SysParameter sysParameter) {
		return super.findPage(page, sysParameter);
	}
	
	@Transactional(readOnly = false)
	public void save(SysParameter sysParameter) {
		super.save(sysParameter);
	}
	/**
	 * 验证系统参数名称相同
	 * @param paramName
	 * @param companyNo
	 * @param paramGroup
	 * @param appNo
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<SysParameter> checkParamName(String paramName,String companyNo,String paramGroup,String appNo) {
		return sysParameterDao.checkParamName(paramName, companyNo, paramGroup, appNo);
	}
	@Transactional(readOnly = false)
	public List<SysParameter> findByGroupStatusName(String paramName,String paramGroup,String status) {
		SysParameter sysParameter=new SysParameter();
		sysParameter.setParamName(paramName);
		sysParameter.setParamGroup(paramGroup);
		sysParameter.setStatus(status);
		return sysParameterDao.findByGroupStatusName(sysParameter);
	}
	@Transactional(readOnly = false)
	public void delete(SysParameter sysParameter) {
		super.delete(sysParameter);
	}
	
}