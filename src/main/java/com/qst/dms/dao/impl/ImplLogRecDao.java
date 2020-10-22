package com.qst.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qst.dms.dao.LogRecDao;
import com.qst.dms.dao.ResultHandler;
import com.qst.dms.entity.LogRec;
import com.qst.dms.util.ConstantsConfig;
import com.qst.dms.util.DbUtil;

/**
 * @author 陌意随影
 TODO :登录日志的dao实现类
 *2019年11月21日  下午8:05:49
 */
public class ImplLogRecDao implements LogRecDao {
	 @SuppressWarnings("javadoc")
	public ImplLogRecDao() {
	}
	@Override
	public boolean save(LogRec t) {
		//添加新的对象的MySQL语句
		String sql = "insert into "+ConstantsConfig.GATHER_LOGREC+" (time,address,type,username,ip,logtype) values(?,?,?,?,?,?)";
		@SuppressWarnings("deprecation")
		//执行MySQL语句并返回执行结果
		int result = DbUtil.executeUpdate(sql,
											   t.getTime().toLocaleString(),
				                               t.getAddress(),t.getType(),
				                               t.getUserName(),t.getIp(),
				                               t.getLogType());
		
		return result == 1;
		
	}
	@Override
	public boolean update(LogRec t) {
		//更新对象的MySQL语句
		String sql = "update "+ConstantsConfig.GATHER_LOGREC+" set time=?,address=?,type=?,username=?,ip=?,logtype=? where id=?";
		@SuppressWarnings("deprecation")
		//执行更新预计并返回更新结果
		int result = DbUtil.executeUpdate(sql, 
				t.getTime().toLocaleString(),t.getAddress(),
				t.getType(),t.getUserName(),
				t.getIp(),t.getLogType(),
				t.getId());
		return  result == 1;
	}
/**
 * 通过对象本身登录的id来删除元素
 */
	@Override
	public boolean remove(LogRec t) {
		//删除指定用户的MySQL语句
		String sql = "delete from "+ConstantsConfig.GATHER_LOGREC+" where id=?";
		//执行MySQL语句并返回执行结果
		int result = DbUtil.executeUpdate(sql,t.getId());
		return result == 1;
	}
/**
 * 通过数据库中的主键id来获取数据
 */
	@Override
	public LogRec get(int key) {
		//获取指定关键字的MySQL语句
		String sql = "select *from  "+ConstantsConfig.GATHER_LOGREC+" where id=?" ;
		//单个对象的处理集
		LogRecResultHandler   resultHandler = new LogRecResultHandler();
		List<LogRec> list =DbUtil.executeQuery(sql, resultHandler, key);
		//执行查询语句并返回经过处理后的结果集
		return 	list.size()==0 ? null:list.get(0);
	}

	@Override
	public List<LogRec> getAll() {
		//查询所有对象的MySQL语句
		String sql = "select *from "+ConstantsConfig.GATHER_LOGREC;
		//处理获取所有对象的结果集
		LogRecResultHandler  resultHandler = new  LogRecResultHandler ();
		 //执行查询语句并返回执行结果
		 return DbUtil.executeQuery(sql, resultHandler);
	}
	/**
	 * 通过数据库中的id来删除元素
	 */
	@Override
	public boolean remove(int key) {
		//删除指定用户的MySQL语句
				String sql = "delete from "+ConstantsConfig.GATHER_LOGREC+" where id=?";
				//执行MySQL语句并返回执行结果
				int result = DbUtil.executeUpdate(sql,key);
				return result == 1;
	}

}
/**
 * 
 * @author 陌意随影
 TODO :获取多个 LogRec对象的处理集
 *2019年11月22日  下午6:28:08
 */
class LogRecResultHandler implements ResultHandler<List<LogRec>>{

	@Override
	public List<LogRec> handleResult(ResultSet set) {
		List<LogRec> list = new ArrayList<LogRec>();
		String tableName=  ConstantsConfig.GATHER_LOGREC_ENTITY;
		//调用工具类的通用方法获得实体对象集合
		DbUtil.mappingEntity(set,list,tableName);
		return list;
		}
}