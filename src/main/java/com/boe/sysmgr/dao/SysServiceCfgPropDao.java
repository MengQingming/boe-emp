package com.boe.sysmgr.dao;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.annotation.MyBatisDao;
import com.boe.sysmgr.entity.SysServiceCfg;
import com.boe.sysmgr.entity.SysServiceCfgProp;

import java.util.List;

/**
 * @ClassName: SysServiceCfgPropDao 
 * @Description: TODO {@link SysServiceCfgProp}
 * @author WangShengDong
 * @date 2017年3月27日 下午4:45:08
 */
@MyBatisDao
public interface SysServiceCfgPropDao extends CrudDao<SysServiceCfgProp> {
	
	/**
	 * 通过接口ID获取所有的；
	 * @param cfgId
	 * @return
	 */
	List<SysServiceCfgProp> getByCfgName(SysServiceCfg sysServiceCfg);
	
	/**
	 * 通过CODE获取
	 * @param code
	 * @param ip
	 * @param serviceName
	 * @return
	 */
	SysServiceCfgProp getByCode(String code, String ip, String serviceName);

	/**
	 * 当前接口全部（状态不管）
	 * @param cfgId
	 * @return
	 */
	List<SysServiceCfgProp> getByCfgIdAll(Integer cfgId);
	
	void updateProp(SysServiceCfgProp prop);

}
