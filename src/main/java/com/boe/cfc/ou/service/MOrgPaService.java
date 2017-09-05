package com.boe.cfc.ou.service;

import com.boe.cfc.ou.dao.MOrgPaDao;
import com.boe.cfc.ou.entity.MOrgPa;
import com.boe.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 获利能力Service
 * @ClassName:  MOrgPaService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author zcf
 * @date 2017年2月7日 下午3:42:50      
 */  
@Service
@Transactional(readOnly=true)
public class MOrgPaService extends CrudService<MOrgPaDao, MOrgPa> {


}