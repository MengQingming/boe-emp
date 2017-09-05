package com.boe.sysmgr.utils;

import java.util.List;

import com.boe.common.utils.SpringContextHolder;
import com.boe.sysmgr.dao.DictItemDao;
import com.boe.sysmgr.entity.Dict;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;

import com.google.common.collect.Lists;
import com.boe.common.mapper.JsonMapper;
import com.boe.sysmgr.entity.DictItem;
import com.boe.sysmgr.entity.User;

/**
 * 字典工具类
 * @author lxx
 * @version 2016-11-24
 */
public class DictItemUtils {
	
	private static DictItemDao dictItemDao = SpringContextHolder.getBean(DictItemDao.class);

	/**
	 * 返回字典列表（JSON）
	 * @param appNo
	 * @return
	 */
	public static String getDictItemListJson(String dictCode,String isGeneral){
		return JsonMapper.toJsonString(getDictItemListL(dictCode,isGeneral));
	}
	
	
	
	
	/**
	 * 字典项编码 + 字典组编码 + 字典数值  获取字典中文值
	 * @param itemCode  	
	 * 						--字典数值
	 * @param isGeneral
	 * 						--是否通用 T 表示通用、F表示不通用
	 * @param dictCode
	 * 						--字典组编码
	 * @param defaultValue  --默认值
	 * @return
	 */
	public static String getDictItemValueL(String itemCode,String dictCode, String isGeneral,String defaultValue){
		if (StringUtils.isNotBlank(itemCode) && StringUtils.isNotBlank(dictCode)){
			for (DictItem dictItem : getDictItemListL(dictCode,isGeneral)){
				if (itemCode.equals(dictItem.getItemCode()) && dictCode.equals(dictItem.getDict().getDictCode())){
					return dictItem.getItemValue();
				}
			}
		}
		return defaultValue;
	}
	
	/**
	 * 根据字典组编码+是否通用+字典中文值  获取字典数值
	 *@param itemValue
	 *				    --- 字典中文值
	 *@param dictCode
	 *					--- 字典组编码
	 *@param isGeneral
	 *					--- 是否通用  T 表示通用、F表示不通用
	 *@param defaultValue
	 *@return
	 */
	public static String getDictItemCodeL(String itemValue,String dictCode,String isGeneral, String defaultValue){
		if (StringUtils.isNotBlank(itemValue) && StringUtils.isNotBlank(dictCode)){
			for (DictItem dictItem : getDictItemListL(dictCode,isGeneral)){
				if (itemValue.equals(dictItem.getItemValue()) && dictCode.equals(dictItem.getDict().getDictCode())){
					return dictItem.getItemCode();
				}
			}
		}
		return defaultValue;
	}
	
	/**
	 * 根据字典组编码 +是否通用 字典项List
	 *@param dictCode 
	 *					--字典组编码
	 *@param isGeneral
	 *					--T 表示通用、F表示不通用、不传表示默认不通用
	 *@return
	 */
	public static List<DictItem> getDictItemListL(String dictCode,String isGeneral){
		DictItem dd = new DictItem();
		dd.setStatus("1");
		Dict dict = new Dict();
		dd.setDictCodeQuery(dictCode);
		dict.setDictCode(dictCode);
		dd.setDict(dict);
		
		User user = new User();
		if(!user.getCurrentUser().isAdmin()){
			dd.setCompanyId(UserUtils.getUser().getCompany().getId());
		}
		dd.setLanguage(user.getLang());
		if("F".equals(isGeneral)){
			dd.setAppNo(getAppNo());
		}else{
			dd.setIsGeneral(isGeneral);
		}
		List<DictItem> dictItemList = dictItemDao.findList(dd);
		if (dictItemList == null){
			dictItemList = Lists.newArrayList();
		}
		//获取当前用户的语言类别
		Session session = UserUtils.getSession();
		Object obj = session.getAttribute("language");
		if(obj!=null){
			String language = obj.toString();
			for(DictItem di : dictItemList){
				if(di.getItemCode().equals(language)){
					di.setSelected(true);
				}else{
					di.setSelected(false);
				}
			}
		}
		return dictItemList;
	}
	
	private static String getAppNo(){
		String appNo = "";
		Object obj = UserUtils.getSession().getAttribute("appNo");
		if(null != obj){
			appNo = obj.toString();
		}
		return appNo;
	}
}
