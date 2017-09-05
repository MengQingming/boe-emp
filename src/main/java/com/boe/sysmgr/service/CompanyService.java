package com.boe.sysmgr.service;

import com.boe.common.service.CrudService;
import com.boe.sysmgr.entity.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.sysmgr.dao.CompanyDao;

/**
* <p>Description:CompanyService  公司Service</p>
* <p>Company:T-ark </p>
* @author: lxx
* @created: 2016-12-8下午3:21:22
* @version: 1.0
*/
@Service
@Transactional(readOnly = true)
public class CompanyService extends CrudService<CompanyDao, Company> {
	
	/**
	 * 保存或者修改公司信息
	 *@param company
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdateCompany(Company company) {
		super.save(company);
	}
	/**
	 * 删除公司信息
	 *@param company
	 */
	@Transactional(readOnly = false)
	public void deleteCompany(Company company) {
		super.delete(company);
	}
}
