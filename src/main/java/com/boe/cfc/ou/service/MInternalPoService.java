package com.boe.cfc.ou.service;

import com.boe.common.service.CrudService;
import com.boe.cfc.ou.dao.MInternalPoDao;
import com.boe.cfc.ou.entity.MInternalPo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MInternalPoService extends CrudService<MInternalPoDao, MInternalPo> {

}
