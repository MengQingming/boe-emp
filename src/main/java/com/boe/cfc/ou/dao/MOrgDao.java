package com.boe.cfc.ou.dao;

import java.util.List;

import com.boe.cfc.ou.entity.MOrg;
import com.boe.common.persistence.CrudDao;


/**   
 * OU Dao
 * @ClassName:  MOrgDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author baipan 
 * @date 2017年2月7日 下午3:37:52      
 */  
@com.boe.common.persistence.annotation.MyBatisDao
public interface MOrgDao extends CrudDao<MOrg> {

	/**   
	 * 根据OrgCode获取OU
	 * @Title: getByOrgCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param orgCode
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月9日 下午5:33:24
	 * @return: MOrg      
	 * @throws   
	 */  
	MOrg getByOrgCode(String orgCode);
	
	/**
	 * 查询指定组织机构下面的所有OU
	 * @param groupPath 组织机构路径
	 * @return
	 */
	public List<MOrg> findListByGroupPath(String groupPath);
	
	/**
	 * 获取事业部下的所有OU
	 * @param businessLineCode
	 * @return
	 */
	public List<MOrg> findListByBusinessLineCode(String businessLineCode);
	
	/**
	 * 根据纳税人识别码查询OU
	 * @param taxpayerCode
	 * @return
	 */
	public MOrg findByTaxpayerCode(String taxpayerCode);

	/**
	 * 根据OrgCode获取OU
	 * @Title: getByCompanyAndOrgCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param orgCode
	 * @param: @return
	 * @author
	 * @date 2017年2月9日 下午5:33:24
	 * @return: MOrg
	 * @throws
	 */
	MOrg getByCompanyAndOrgCode(Integer companyId, String orgCode);
}