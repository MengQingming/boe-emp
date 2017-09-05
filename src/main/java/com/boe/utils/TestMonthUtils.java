package com.boe.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhangxiyu
 * @version 2017-05-19
 * @description 获取从开始时间到结束时间所有年月的月份列的测试代码
 */
public class TestMonthUtils {
	
	
 public void MonthTest(){
	Date startDate=new Date();//开始时间
	Date endDate = new Date();//截止时间 
	Date periodDate = startDate;//开始和截止中间的时间（初值是开始时间）
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//设置日期的格式
	List<String> list=new ArrayList<String>();
	Calendar cPeriod=Calendar.getInstance();
	Calendar cEnd=Calendar.getInstance();
	cEnd.set(Calendar.YEAR, 2017);
	cEnd.set(Calendar.MONTH, 9-1);
	cEnd.set(Calendar.DATE, 10); //设置截止的时间CrudDao
	cPeriod.setTime(periodDate);//设置中间时间初值
	endDate = cEnd.getTime();//获取截止时间
	int startDay=cPeriod.get(Calendar.DATE);
	int endDay=cEnd.get(Calendar.DATE);
	//打印截止时间
	System.out.println("######打印截止时间######");
	System.out.println(endDate);
	//打印日期
	System.out.println("######打印日期######");
	System.out.println(startDay);
	System.out.println(endDay);
	//循环打印年月
    if(startDay<=endDay){
		cEnd.add(Calendar.MONTH, -1);
		endDate=cEnd.getTime();
		//打印年月
		System.out.println("######打印结束日期######");
		System.out.println(sdf.format(endDate));
		System.out.println("######打印之间的月份######");
		for(;periodDate.getTime()<=endDate.getTime();cPeriod.add(Calendar.MONTH, 1)){
			periodDate=cPeriod.getTime();
			list.add(sdf.format(periodDate));
		}
	
    }else{
		//打印年月
		System.out.println("######打印结束日期######");
		System.out.println(sdf.format(endDate));
		System.out.println("######打印之间的月份######");
		for(;periodDate.getTime()<=endDate.getTime();cPeriod.add(Calendar.MONTH, 1)){
			periodDate=cPeriod.getTime();
			list.add(sdf.format(periodDate));
		}
    }
	for(int i=0;i<list.size();i++){
		System.out.println(list.get(i));
	}
}
}

