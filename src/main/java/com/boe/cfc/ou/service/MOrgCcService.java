package com.boe.cfc.ou.service;

import java.util.List;

import com.boe.cfc.ou.entity.MGroupOrg;
import com.boe.cfc.ou.entity.MOrgCc;
import com.boe.common.service.CrudService;
import com.boe.sysmgr.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.boe.cfc.ou.dao.MOrgCcDao;
import com.boe.sysmgr.entity.Group;

/**   
 * 成本中心Service
 * @ClassName:  MOrgCcService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author baipan 
 * @date 2017年2月7日 下午3:42:50      
 */  
@Service
@Transactional(readOnly=true)
public class MOrgCcService extends CrudService<MOrgCcDao, MOrgCc> {

	@Autowired
	private GroupDao groupDao;


	@Autowired
	private MGroupOrgService mGroupOrgService;

	/**   
	 * 根据cccode获取成本中心
	 * @Title: getByCcCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param cccode
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月9日 下午3:04:03
	 * @return: MOrgCc      
	 * @throws   
	 */  
	public MOrgCc getByCcCode(String cccode) {
		return dao.getByCcCode(cccode);
	}

	/**
	 * 获取ou下边的所以成本中心
	 * @param orgCode
	 * @return
	 */
	public List<MOrgCc> queryOrgccs(String orgCode) {
		
		return dao.getByOrgCode(orgCode);
	}

	/**
	 * 根据Group获取成本中心
	 * @Title: queryOrgccs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param group
	 * @param: @return
	 * @author
	 * @date 2017年2月10日 上午9:44:59
	 * @return: List<MOrgCc>
	 * @throws
	 */
	public List<MOrgCc> queryOrgccs(Group group,String orgCode) {
		//先通过group获取成本组《当前group如果没有成本组，就往上找，找到为止》
		//再通过成本组获取成本中心集合
		List<MOrgCc> ccs = null;
		group = groupDao.get(group.getId());
		if (group==null){
			List<MGroupOrg> tempList = mGroupOrgService.findMGroupOrgByOrgCode(orgCode);
			group = (tempList==null||tempList.size()<1)?new Group():new Group(tempList.get(0).getGroupId());
		}
		String ccGroup = !StringUtils.isEmpty(group.getCcGroup()) ? group.getCcGroup() : null;
		/*if (StringUtils.isEmpty(ccGroup)) {
			for(;;){
				Group parentGroup = groupDao.get(group.getParentId());
				ccGroup = !StringUtils.isEmpty(parentGroup.getCcGroup()) ? parentGroup.getCcGroup() : null;
				if ((!StringUtils.isEmpty(ccGroup)) || parentGroup == null) {
					break;
				}else{
					group = parentGroup;
				}
			}
		}*/
		if (!StringUtils.isEmpty(ccGroup)) {
			ccs = dao.queryByCcGroup(orgCode, ccGroup);
		}else{
			ccs = dao.getByOrgCode(orgCode);
		}
		return ccs;
	}

	public List<MOrgCc> queryOrgccs(Group group,String orgCode,Integer item2Id) {
		List<MOrgCc> ccs = null;
			group = groupDao.get(group.getId());
			if (group==null){
				List<MGroupOrg> tempList = mGroupOrgService.findMGroupOrgByOrgCode(orgCode);
				group = (tempList==null||tempList.size()<1)?new Group():new Group(tempList.get(0).getGroupId());
			}
			String ccGroup = !StringUtils.isEmpty(group.getCcGroup()) ? group.getCcGroup() : null;
			if (!StringUtils.isEmpty(ccGroup)) {
				ccs = dao.queryByCcGroup(orgCode, ccGroup);
			}else{
				ccs = dao.getByOrgCode(orgCode);
			}
		return ccs;
	}

	/**   
	 * 根据Group获取成本中心
	 * @Title: queryOrgccs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param group
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月10日 上午9:44:59
	 * @return: List<MOrgCc>      
	 * @throws   
	 */  
	public List<MOrgCc> queryOrgccs(Group group) {
		//先通过group获取成本组《当前group如果没有成本组，就往上找，找到为止》
		//再通过成本组获取成本中心集合
		List<MOrgCc> ccs = null;
		group = groupDao.get(group.getId());
		String ccGroup = !StringUtils.isEmpty(group.getCcGroup()) ? group.getCcGroup() : null;
		if (StringUtils.isEmpty(ccGroup)) {
			for(;;){
				Group parentGroup = groupDao.get(group.getParentId());
				ccGroup = !StringUtils.isEmpty(parentGroup.getCcGroup()) ? parentGroup.getCcGroup() : null;
				if ((!StringUtils.isEmpty(ccGroup)) || parentGroup == null) {
					break;
				}else{
					group = parentGroup;
				}
			}
		}
		if (!StringUtils.isEmpty(ccGroup)) {
			ccs = dao.queryByCcGroup(ccGroup);
		}
		return ccs;
	}


}