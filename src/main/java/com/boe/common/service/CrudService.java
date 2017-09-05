package com.boe.common.service;

import com.boe.common.persistence.CrudDao;
import com.boe.common.persistence.DataEntity;
import com.boe.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Integer id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {

		logger.info("*****保存数据（插入或更新）*****CrudService*****entity.getIsNewRecord() = "+entity.getIsNewRecord());

		logger.info("*****保存数据（插入或更新）*****CrudService*****entity.getDelFlag() = "+entity.getDelFlag());
		
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
			logger.info("*****保存数据（插入或更新）*****CrudService***dao.insert(entity)**");
		}else{
			entity.preUpdate();
			dao.update(entity);
			logger.info("*****保存数据（插入或更新）*****CrudService***dao.update(entity)**");
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

}
