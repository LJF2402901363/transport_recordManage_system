package com.qst.dms.util;

import java.util.Properties;

/**
 * @author 陌意随影
 TODO :
 *2019年12月19日  下午9:47:11
 */
public class ConstantsConfig {
	/**服务器连接的IP地址*/
	public final static String HOST= PropertiesConfigUtil.PropertiesFactory("server").getProperty("host");
	/**服务器连接的端口*/
   public final static int  PORT= Integer.parseInt(PropertiesConfigUtil.PropertiesFactory("server").getProperty("port"));
   /**数据库的链接配置*/
	public final static Properties JDBCMYSQL=PropertiesConfigUtil.PropertiesFactory("druid");
	/**ImplLogRecDao实现类的配置路径*/
	public final static String IMPLLOGRECDAO=PropertiesConfigUtil.PropertiesFactory("dao").getProperty("ImplLogRecDao");
	/**ImplMatchedLogRecDao实现类的配置路径*/
	public final static String IMPLMATCHEDLOGRECDAO=PropertiesConfigUtil.PropertiesFactory("dao").getProperty("ImplMatchedLogRecDao");
	/**ImplMatchedTransportDao实现类的配置路径*/
	public final static String IMPLMATCHEDTRANSPORTDAO=PropertiesConfigUtil.PropertiesFactory("dao").getProperty("ImplMatchedTransportDao");
	/**ImplTransportDao实现类的配置路径*/
	public final static String IMPLTRANSPORTDAO=PropertiesConfigUtil.PropertiesFactory("dao").getProperty("ImplTransportDao");
	/**ImplUserDao实现类的配置路径*/
	public final static String IMPLUSERDAO=PropertiesConfigUtil.PropertiesFactory("dao").getProperty("ImplUserDao");
	/**LogRecService实现类的配置路径*/
	public final static String LOGRECSERVICE=PropertiesConfigUtil.PropertiesFactory("service").getProperty("LogRecService");
	/**TransportService实现类的配置路径*/
	public final static String TRANSPORTSERVICE=PropertiesConfigUtil.PropertiesFactory("service").getProperty("TransportService");
	/**UserService实现类的配置路径*/
	public final static String USERSERVICE=PropertiesConfigUtil.PropertiesFactory("service").getProperty("UserService");
	/**LogRecAnalyse实现类的配置路径*/
	public final static String LOGRECANALYSE=PropertiesConfigUtil.PropertiesFactory("DataAnalyse").getProperty("LogRecAnalyse");
	/**TransportAnalyse实现类的配置路径*/
	public final static String TRANSPORTANALYSE=PropertiesConfigUtil.PropertiesFactory("DataAnalyse").getProperty("TransportAnalyse");
	//
	/**用户名正则表达式的配置路径*/
	public final static String USERNAME_REGEXFORMATA=PropertiesConfigUtil.PropertiesFactory("regex").getProperty("username");
	/**密码正则表达式的配置路径*/
	public final static String PASSWORD_REGEXFORMATA=PropertiesConfigUtil.PropertiesFactory("regex").getProperty("password");
	/**加密和解密算法的密码*/
	public final static String SUCURE_PASSWORD=PropertiesConfigUtil.PropertiesFactory("secure").getProperty("password");
	/**LogRec对应实体类的路径*/
	public final static String GATHER_LOGREC_ENTITY=PropertiesConfigUtil.PropertiesFactory("entity").getProperty("gather_logrec");
	/**MatchedLogRec对应实体类的路径*/
	public final static String GATHER_TRANSPORT_ENTITY=PropertiesConfigUtil.PropertiesFactory("entity").getProperty("gather_transport");
	/**MatchedLogRec对应实体类的路径*/
	public final static String MATCHED_LOGREC_ENTITY=PropertiesConfigUtil.PropertiesFactory("entity").getProperty("matched_logrec");
	/**MatchedTransport对应实体类的路径*/
	public final static String MATCHED_TRANSPORT_ENTITY=PropertiesConfigUtil.PropertiesFactory("entity").getProperty("matched_transport");
	/**User对应实体类的路径*/
	public final static String USERDETAILS_ENTITY=PropertiesConfigUtil.PropertiesFactory("entity").getProperty("userdetails");
	    
	/**LogRec对应实体类的路径*/
	public final static String GATHER_LOGREC=PropertiesConfigUtil.PropertiesFactory("databaseTable").getProperty("gather_logrec");
	/**MatchedLogRec对应实体类的路径*/
	public final static String GATHER_TRANSPORT=PropertiesConfigUtil.PropertiesFactory("databaseTable").getProperty("gather_transport");
	/**MatchedLogRec对应实体类的路径*/
	public final static String MATCHED_LOGREC=PropertiesConfigUtil.PropertiesFactory("databaseTable").getProperty("matched_logrec");
	/**MatchedTransport对应实体类的路径*/
	public final static String MATCHED_TRANSPORT=PropertiesConfigUtil.PropertiesFactory("databaseTable").getProperty("matched_transport");
	/**User对应实体类的路径*/
	public final static String USERDETAILS=PropertiesConfigUtil.PropertiesFactory("databaseTable").getProperty("userdetails");
	    
	
	
	
	
	
	}
