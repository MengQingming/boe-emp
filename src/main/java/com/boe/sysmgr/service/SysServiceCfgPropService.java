package com.boe.sysmgr.service;

import com.boe.common.service.CrudService;
import com.boe.common.utils.SpringContextHolder;
import com.boe.scheduler.scheduling.SpringTaskConfigurer;
import com.boe.sysmgr.dao.SysServiceCfgPropDao;
import com.boe.sysmgr.entity.SysServiceCfg;
import com.boe.sysmgr.entity.SysServiceCfgProp;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysServiceCfgPropService 
 * @Description: TODO {@link SysServiceCfgPropDao}
 * @author WangShengDong
 * @date 2017年3月27日 下午4:46:02
 */
@Service
public class SysServiceCfgPropService extends CrudService<SysServiceCfgPropDao,SysServiceCfgProp> {
	
	/**
	 * @param list
	 */
	public void saveOrUpdate(List<SysServiceCfgProp> list){
		for (SysServiceCfgProp sysServiceCfgProp : list) {
			save(sysServiceCfgProp);
		}
	}
	
	/**
	 * 获取CODE
	 * @param code
	 * @return
	 */
	public static String getByCode(String code) {
		if (code == null || "".equals(code)) return "";
		SysServiceCfgProp cfgProp= SpringContextHolder.getBean(SysServiceCfgPropDao.class).getByCode(code, SpringTaskConfigurer.getIp(),System.getProperty("weblogic.Name"));
		if (cfgProp != null)
			return cfgProp.getValue();
		return "";
	}
	
	/**
	 * @param serviceName
	 * @return
	 */
	public Map<String, String> getByCfgName(String serviceName){
		Map<String, String> map = new HashMap<String, String>();
		SysServiceCfg sysServiceCfg = new SysServiceCfg();
		sysServiceCfg.setServiceName(serviceName);
		sysServiceCfg.setServiceRunIp(SpringTaskConfigurer.getIp());
		sysServiceCfg.setServiceRunIp(System.getProperty("weblogic.Name"));
		for (SysServiceCfgProp prop :  dao.getByCfgName(sysServiceCfg)) {
			map.put(prop.getCode(), prop.getValue());
		}
		return map;
	}
	
	/**
	 * @param prop
	 */
	public void updateProp(SysServiceCfgProp prop){
		dao.updateProp(prop);
	}
	
	public void delete(SysServiceCfgProp prop){
		dao.delete(prop);
	}
	
}
