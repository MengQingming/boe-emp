package com.boe.common.mybatisplus.test;

import com.boe.common.mybatisplus.annotations.IdType;
import com.boe.common.mybatisplus.generator.AutoGenerator;
import com.boe.common.mybatisplus.utils.EnvUtil;
import com.boe.common.mybatisplus.generator.ConfigDataSource;
import com.boe.common.mybatisplus.generator.ConfigGenerator;

/**
 * 
 * 自动生成映射工具类
 * 
 */
public class AutoGeneratorTools {
	/**
	 * 
	 * 测试 run 执行
	 * 
	 * <p>
	 * 配置方法查看 {@link ConfigGenerator}
	 * </p>
	 * 
	 */
	public static void main( String[] args ) {
		ConfigGenerator cg = new ConfigGenerator();
		cg.setEntityPackage("com.tark.gentemp.entity");
		cg.setMapperPackage("com.tark.gentemp.mapper");
		cg.setServicePackage("com.tark.gentemp.service");
		cg.setSuperServiceImpl("com.tark.gentemp.service.support.BaseServiceImpl");
		cg.setIdType(IdType.ID_WORKER);
		if (EnvUtil.isLinux()) {
			cg.setSaveDir("/Users/hubin/springwind/");
		} else {
			cg.setSaveDir("C:/autoGenEntity/");
		}
		
		//此处增加：设置为oracle数据库。
		cg.setConfigDataSource(ConfigDataSource.valueOf("ORACLE"));
		
		cg.setDbDriverName("oracle.jdbc.driver.OracleDriver");
		cg.setDbUser("boe");
		cg.setDbPassword("boe");
		cg.setDbUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		
		//此处增加：设置要把哪些表生成实体，不设置则为所有表
		String[] aa = {"SYS_DICT"};
		cg.setTableNames(aa);
				
		/*
		//此处增加：设置为MYSQL数据库，也可以不加，默认就是MYSQL
		cg.setConfigDataSource(ConfigDataSource.valueOf("MYSQL"));
		cg.setDbDriverName("com.mysql.jdbc.Driver");
		cg.setDbUser("root");
		cg.setDbPassword("123456");
		cg.setDbUrl("jdbc:mysql://127.0.0.1:3306/springwind?characterEncoding=utf8");
		*/
		
		cg.setDbPrefix(false);
		AutoGenerator.run(cg);
		System.out.println("-----------------");
	}
	
}
