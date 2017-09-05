package com.boe.cfc.ou.service;

import java.util.ArrayList;
import java.util.List;

import com.boe.cfc.ou.entity.MOrg;
import com.boe.cfc.ou.dao.MOrgDao;
import com.boe.common.service.CrudService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**   
 * OU Service
 * @ClassName:  MOrgService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author baipan 
 * @date 2017年2月7日 下午3:42:18      
 */  
@Service
@Transactional(readOnly=true)
public class MOrgService extends CrudService<MOrgDao, MOrg> {

	/**   
	 * 根据OrgCode获取OU
	 * @Title: getByOrgCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param orgCode
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月9日 下午5:32:30
	 * @return: MOrg      
	 * @throws   
	 */  
	public MOrg getByOrgCode(String orgCode) {
		return dao.getByOrgCode(orgCode);
	}

	/**
     * 查询指定组织机构下面的所有OU
     * @param groupPath 组织机构路径
     * @return
     */
    public List<MOrg> findListByGroupPath(String groupPath)
    {
        return dao.findListByGroupPath(groupPath);
    }
    
    /**
     * 获取事业部下的所有OU
     * @param businessLineCode 事业部编码
     * @return
     */
    public List<MOrg> findListByBusinessLineCode(String businessLineCode)
    {
        if (StringUtils.isBlank(businessLineCode))
            return new ArrayList<MOrg>();
        
        return dao.findListByBusinessLineCode(businessLineCode);
    }
	
}