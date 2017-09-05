package com.boe.cfc.ou.util;

import com.boe.cfc.ou.dao.MInternalPoDao;
import com.boe.cfc.ou.entity.MInternalPo;
import com.boe.common.utils.SpringContextHolder;

import java.util.List;


/**   
 * 业务小类工具类
 * @ClassName:  Item2Utils   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author baipan 
 * @date 2017年1月24日 上午10:54:33      
 */  
public class InternalProUtils {

	private static MInternalPoDao dao = SpringContextHolder.getBean(MInternalPoDao.class);


	/**
	 * @Title: queryMInternalPos
	 * @Description: 查询内部订单
	 * @param: @param mInternalPo
	 * @param: @return
	 * @author zcf
	 * @date 2017年2月13日 下午7:47:11
	 * @return: List<MInternalPo>
	 * @throws
	 */
	public static List<MInternalPo> queryMInternalPos(String orgCode){
		MInternalPo mInternalPo = new MInternalPo();
		mInternalPo.setOrgCode(orgCode);
		List<MInternalPo> list = dao.findList(mInternalPo);
		return list;
	}
}
