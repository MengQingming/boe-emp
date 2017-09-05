package com.boe.cfc.ou.dao;

import java.util.List;

import com.boe.common.persistence.CrudDao;
import com.boe.cfc.ou.entity.MOrgCc;

/**   
 * 成本中心dao
 * @ClassName:  MOrgCcDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author baipan 
 * @date 2017年2月7日 下午3:38:53      
 */  
@com.boe.common.persistence.annotation.MyBatisDao
public interface MOrgCcDao extends CrudDao<MOrgCc> {

	/**   
	 * 根据cccode获取成本中心
	 * @Title: getByCcCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param cccode
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月9日 下午3:05:17
	 * @return: MOrgCc      
	 * @throws   
	 */  
	MOrgCc getByCcCode(String cccode);

	/**   
	 * 根据成本组获取列表
	 * @Title: queryByCcGroup   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param ccGroup
	 * @param: @return  
	 * @author baipan     
	 * @date 2017年2月10日 上午10:25:05
	 * @return: List<MOrgCc>      
	 * @throws   
	 */  
	List<MOrgCc> queryByCcGroup(String ccGroup);

	List<MOrgCc> getByOrgCode(String orgCode);

	List<MOrgCc> queryByCcGroup(String orgCode,String ccGroup);

}