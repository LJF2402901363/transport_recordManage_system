package com.qst.dms.util;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;

import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.qst.dms.dao.ResultHandler;


/**
 * @author 陌意随影
 TODO :数据库的链接工具
 *2019年11月21日  下午8:13:05
 */
public class DbUtil {
	private final static Properties pro =ConstantsConfig.JDBCMYSQL ;
	private static Connection con = null;
    /**
     * @return 返回一个Druid连接池的DataSource
     */
    public static DataSource getDruidDataSource(){
    	DataSource ds = null;
    	try {
			ds =  DruidDataSourceFactory.createDataSource(pro);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ds;
    	
    }
    
    /**
     * @return 返回一个没有连接池的连接对象
     */
    public static Connection getDruidConnection(){
    	try {
    		if(con == null){
    			DataSource ds = DruidDataSourceFactory.createDataSource(pro);
    			return ds.getConnection();
    		}else{
    			return con;
    		}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	return null;
    }
    
    /**
     * @return 返回一个普通连接池的连接对象
     */
    public static Connection getConnection(){
    	
		try {
			return DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("username"),  pro.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * 关闭连接资源
     * @param con
     * @param st
     * @param rs
     */
    public static void close(Connection con,Statement st,ResultSet rs){
    	if(rs != null){
    		try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				
				if(st != null){
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						if(con != null){
							try {
								con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
    	}
    }
    /**
     * 查询和更新元素使用
     * @param sql
     * @param params
     * @return 返回一个结果的标识
     */
    public static int executeUpdate(String sql,Object... params){
    	if(params == null){
    		return -1;
    	}
    	Connection con =DbUtil.getDruidConnection();
    	PreparedStatement ps=null;
    	//禁止自动提交事件
    	try {
			con.setAutoCommit(false);
			 ps = con.prepareStatement(sql);
			for(int i =0 ;i < params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			int result = ps.executeUpdate();
			//提交事件
			con.commit();
			return result;
		} catch (SQLException e) {
			//回滚事件
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.close(con, ps, null);
		}
    	
		return -1;
    	
    }
   
    /**
     * 查询并返回结果集
     * @param sql
     * @param resultHandler 结果集的处理器
     * @param params
     * @return  返回查询的结果
     */
    public static <T> T executeQuery(String sql,ResultHandler<T> resultHandler,Object... params){
    	if(params == null ){
    		return null;
    	}
    	Connection con =DbUtil.getDruidConnection();
    	try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	PreparedStatement ps = null;
    	try {
			 ps=con.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			ResultSet resultSet = ps.executeQuery();
			con.commit();
			return resultHandler.handleResult(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.close(con, ps, null);
		}
    	
    	return null;
    }
  /**
   * 通过反射技术来获取实体类
 * @param resultSet  结果集
 * @param tableName 数据库表名
 */
@SuppressWarnings("unchecked")
public static <T> void   mappingEntity(ResultSet resultSet, Collection<T> collection, String tableName){
	Object	entityObj =null;
	Class<?> entityClass=null;
	try {
			entityClass = Class.forName(tableName);
			while (resultSet.next()) {
				// 实例化一个对象
				entityObj = entityClass.newInstance();
				// 获取实体类的所有方法
				Method[] methods = entityClass.getMethods();
				for (Method method : methods) {
					// 获取每个方法的名称
					String methodName = method.getName();
					if ("set".equals(methodName.substring(0, 3))) {
						// 给属性值赋值
						String filedName = methodName.substring(3).toLowerCase();
						// 获取所赋值的类型
						String paramType = method.getParameterTypes()[0].getTypeName();
						if ("int".equals(paramType) || "java.lang.Integer".equals(paramType)) {
							method.invoke(entityObj, resultSet.getInt(filedName));
						} else if ("long".equals(paramType) || "java.lang.Long".equals(paramType)) {
							method.invoke(entityObj, resultSet.getLong(filedName));
						} else if ("short".equals(paramType) || "java.lang.Short".equals(paramType)) {
							method.invoke(entityObj, resultSet.getShort(filedName));
						} else if ("double".equals(paramType) || "java.lang.Double".equals(paramType)) {
							method.invoke(entityObj, resultSet.getDouble(filedName));
						} else if ("float".equals(paramType) || "java.lang.Float".equals(paramType)) {
							method.invoke(entityObj, resultSet.getFloat(filedName));
						} else if ("boolean".equals(paramType) || "java.lang.Boolean".equals(paramType)) {
							method.invoke(entityObj, resultSet.getBoolean(filedName));
						} else if ("String".equals(paramType) || "java.lang.String".equals(paramType)) {
							if (filedName.equalsIgnoreCase("password")) {
								String password = resultSet.getString(filedName);
								// 如果该属性是密码password则需要调用解密工具对其解密
								password = AESUtil.Decode(password, ConstantsConfig.SUCURE_PASSWORD);
								method.invoke(entityObj, password);
							} else {
								method.invoke(entityObj, resultSet.getString(filedName));
							}
						} else if ("Date".equals(paramType) || "java.util.Date".equals(paramType)) {
							method.invoke(entityObj, resultSet.getDate(filedName));
						} else {
							// 其他情况碰到时再添加，如Java8中的LocalDateTime等
							method.invoke(entityObj, resultSet.getObject(filedName));
						}
					}
				}
				collection.add((T) entityObj);
				}
		} catch (Exception e) {
			e.printStackTrace();
  }finally{
	  try {
		  if(resultSet!=null){
			  resultSet.close();
		  }
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }
}
}
