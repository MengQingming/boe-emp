package com.boe.i18n.service;

import java.util.List;

import com.boe.common.service.CrudService;
import com.boe.i18n.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.i18n.dao.ResourceDao;

@Service
@Transactional(readOnly = true)
public class ResourceService extends CrudService<ResourceDao,Resource> {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Transactional(readOnly = false)
	@Cacheable(value = "sysCache", key = "'formSource'")
	public List<Resource> findAll(){
		return resourceDao.findAllList(new Resource());
	}
}
