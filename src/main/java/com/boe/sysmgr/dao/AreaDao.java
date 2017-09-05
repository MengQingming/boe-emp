package com.boe.sysmgr.dao;

import java.util.List;

import com.boe.sysmgr.entity.Area;
import com.boe.common.persistence.TreeDao;
import com.boe.common.persistence.annotation.MyBatisDao;


/**
* <p>Description:AreaDao 区域dao</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:12:13
* @version: 1.0
*/
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

	List<Area> findListByPrentId(Area area2);

	List<Area> findListByTreeBox(Area area);

	Area findByAreaCode(String code, Integer companyId);

    List<Area> getAreaByName(String areaName);

    Area getTopArea(Integer companyId);
}
