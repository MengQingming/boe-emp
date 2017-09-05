package com.boe.sysmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boe.common.persistence.Page;
import com.boe.common.service.CrudService;
import com.boe.sysmgr.dao.SysMessageDao;
import com.boe.sysmgr.dao.SysMessageHisDao;
import com.boe.sysmgr.entity.SysMessageHis;

@Service
public class SysMessageHisService extends CrudService<SysMessageHisDao,SysMessageHis>{
	
	@Autowired
	private SysMessageHisDao sysMessageHisDao;
	@Autowired
	private SysMessageDao sysMessageDao;
	/**
	 * 互获取消息分页列表数据
	 * @param page
	 * @param sysMessage
	 * @return
	 */
	public Page<SysMessageHis> findList(Page<SysMessageHis> page,SysMessageHis sysMessageHis){
		sysMessageHis.getSqlMap().put("dsf", dataScopeFilter(sysMessageHis.getCurrentUser(), "o", "a"));
		return super.findPage(page,sysMessageHis);
	}

}
