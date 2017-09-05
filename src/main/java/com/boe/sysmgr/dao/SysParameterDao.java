/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.sysmgr.dao;

import java.util.List;

import com.boe.common.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.SysParameter;


/**
 * 系统参数DAO接口
 * @author zhou
 * @version 2016-12-12
 */
@MyBatisDao
public interface SysParameterDao extends CrudDao<SysParameter> {
	public List<SysParameter> checkParamName(String paramName,String companyNo,String paramGroup,String appNo);
	public List<SysParameter> findByGroupStatusName(SysParameter sysParameter);
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
	public List<SysParameter> queryByGroupNames(String paramGroup, @Param("paramNames")List<String> paramNames);
}