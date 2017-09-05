package com.boe.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boe.common.utils.SpringContextHolder;
import com.google.common.collect.Lists;


import com.boe.sysmgr.dao.UserDao;
import com.boe.sysmgr.entity.DictItem;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.utils.DictItemUtils;


public class TarkUtils {
	//private static SysRateDao rateDao = SpringContextHolder.getBean(SysRateDao.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	public static final String CACHE_RATE_MAP = "rateMap";


	public static List<User> getUserList(){
		List<User> list = userDao.findListOrderByUserNum(new User());
		List<User> userLst = Lists.newArrayList();
		for (User user : list) {
			user.setUserNum(user.getFullName()+"("+user.getUserNum()+")");
			userLst.add(user);
		}
		return userLst;
	}
	

	public static List<DictItem> getLeaveTypeCode(){
		//获取对应的列表
		List<DictItem> list = DictItemUtils.getDictItemListL("leave_type", "");
		List<DictItem> li=new ArrayList<DictItem>();
		Iterator<DictItem> it = list.iterator();
		while(it.hasNext()){
			DictItem l=(DictItem)it.next();
			if(l.getItemCode().equals("medical")||l.getItemCode().equals("affair")||l.getItemCode().equals("other")){
				li.add(l);
			}
		}
		list.removeAll(li);
		return list;
	}
}
