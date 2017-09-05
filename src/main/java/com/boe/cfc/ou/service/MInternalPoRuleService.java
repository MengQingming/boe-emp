package com.boe.cfc.ou.service;

import com.boe.cfc.ou.dao.MInternalPoRuleDao;
import com.boe.cfc.ou.entity.MInternalPoRule;
import com.boe.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MInternalPoRuleService extends CrudService<MInternalPoRuleDao, MInternalPoRule> {

}
