package com.qst.dms.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 陌意随影
 TODO :封装了配置文件的获取
 *2019年12月19日  下午8:50:04
 */
public class PropertiesConfigUtil {
  private static Properties[] properties = new Properties[10];
  static {
	  
	 for(int i = 0;i < properties.length;i++){
		 properties[i] = new Properties();
	 }
	 try {
		properties[0].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/druid.properties"));
		properties[1].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/mysql.properties"));
		properties[2].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/server.properties"));
		properties[3].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/dao.properties"));
		properties[4].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/service.properties"));
		properties[5].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/DataAnalyse.properties"));
		properties[6].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/regex.properties"));
		properties[7].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/secure.properties"));
		properties[8].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/entity.properties"));
		properties[9].load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/databaseTable.properties"));
		
	 } catch (IOException e) {
		e.printStackTrace();
	}
  }
  /**
   * 通过配置文件名获取配置文件的properties
 * @param name
 * @return 返回对应的properties,如果为空则返回null
 */
public static  Properties PropertiesFactory(String name){
	switch(name){
	case "druid":
		return properties[0];
	case "mysql":
	return	properties[1];
	case "server":
		return properties[2];
	case "dao":
		return properties[3];
	case "service":
		return properties[4];
	case "DataAnalyse":
		return properties[5];
	case "regex":
		return properties[6];
	case "secure":
		return properties[7];
	case "entity":
		return properties[8];
	case "databaseTable":
		return properties[9];
	}
	return null;
	  
  }
}
