package com.boe.sysmgr.utils;

import java.util.List;
import java.util.Map;

import com.boe.common.utils.SpringContextHolder;
import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.dao.DictDao;
import com.boe.sysmgr.entity.Dict;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.boe.common.mapper.JsonMapper;

/**
 * 字典工具类
 * @author lxx
 * @version 2016-11-24
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);
	private static CacheComponent cacheComponent = SpringContextHolder.getBean(CacheComponent.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	public static final String CACHE_DICTITEM_MAP = "dictItemMap";
	
	public static String getDictLabel(String code, String appNo, String defaultValue){
		if (StringUtils.isNotBlank(appNo) && StringUtils.isNotBlank(code)){
			for (Dict dict : getDictList(appNo)){
				if (appNo.equals(dict.getAppNo()) && code.equals(dict.getDictCode())){
					return dict.getDictName();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String appNo, String defaultValue){
		if (StringUtils.isNotBlank(appNo) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, appNo, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String name, String appNo, String defaultLabel){
		if (StringUtils.isNotBlank(appNo) && StringUtils.isNotBlank(name)){
			for (Dict dict : getDictList(appNo)){
				if (appNo.equals(dict.getAppNo()) && name.equals(dict.getDbName())){
					return dict.getDictCode();
				}
			}
		}
		return defaultLabel;
	}
	
	
	public static List<Dict> getDictList(String appNo){
		Map<String, List<Dict>> dictMap = cacheComponent.findDictList(appNo);
		List<Dict> dictList = dictMap.get(appNo);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}

	public static String getDictName(String code,String appNo){
		if (StringUtils.isNotBlank(appNo) && StringUtils.isNotBlank(code)){
			for (Dict dict : getDictList(appNo)){
				if (appNo.equals(dict.getAppNo()) && code.equals(dict.getDictCode())){
					return dict.getDictName();
				}
			}
		}
		return "";
	}
	
	public static List<Dict> getAllDict(){
		List<Dict> dicts= dictDao.findAllList(new Dict());
		return dicts;
	}
	
	public static String findDictNameById(Integer id){
		Dict dict= dictDao.get(id);
		return dict.getDictName();
	}
	/**
	 * 返回字典列表（JSON）
	 * @param appNo
	 * @return
	 */
	public static String getDictListJson(String appNo){
		return JsonMapper.toJsonString(getDictList(appNo));
	}
	
}
