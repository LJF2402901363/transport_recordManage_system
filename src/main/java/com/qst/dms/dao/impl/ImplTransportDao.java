package com.qst.dms.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qst.dms.dao.ResultHandler;
import com.qst.dms.dao.TransportDao;
import com.qst.dms.entity.Transport;
import com.qst.dms.util.ConstantsConfig;
import com.qst.dms.util.DbUtil;
/**
 * @author 陌意随影
 TODO :物流日志的实现类
 *2019年11月22日  下午6:32:27
 */
public class ImplTransportDao implements TransportDao{
	  private static final String tableName=ConstantsConfig.GATHER_TRANSPORT;
	@Override
	public boolean save(Transport t) {
		//添加新的对象的MySQL语句
		String sql ="insert into "+tableName+" (time,address,type,handler,reciver,transporttype) values(?,?,?,?,?,?)";
		@SuppressWarnings("deprecation")
		//执行语句并返回执行结果
		int result =DbUtil.executeUpdate(sql, t.getTime().toLocaleString(),t.getAddress(),t.getType(),t.getHandler(),t.getReciver(),t.getTransportType());
		return  result == 1;
	}

	@Override
	public boolean update(Transport t) {
		//更新指定对象的MySQL语句
		String sql ="update "+tableName+" set time=?,address=?,type=?,handler=?,reciver=?,transporttype=? where id=?";
		@SuppressWarnings("deprecation")
		//执行MySQL语句并返回执行结果
		int result =DbUtil.executeUpdate(sql, t.getTime().toLocaleString(),t.getAddress(),t.getType(),t.getReciver(),t.getTransportType(),t.getId());
		return  result == 1;
	}
	/**
	 * 通过数据库中的主键id来删除数据
	 */
@Override
	public boolean remove(int key) {
		//通过指定的关键字来删除对象
		String sql = "delete from "+tableName+" where id=?";
		//执行删除对象的MySQL语句并返回执行结果
		int result = DbUtil.executeUpdate(sql, key);
		return  result == 1;
	}

/**
 * 通过数据库中的主键来获取数据
 */
	@Override
	public Transport get(int key) {
		//查询指定关键字的MySQL语句
		String sql = "select *from "+tableName+" where id=?";
		//实现获得一个对象的结果集，对结果集进行操作
		TransportResultHandler manyResultHandler = new TransportResultHandler();
		//执行查询语句并返回结果集
		List<Transport> list =DbUtil.executeQuery(sql, manyResultHandler,key);
		if(list == null || list.size() == 0){
			return null;
		}
		return   list.get(0);
	}
	@Override
	public List<Transport> getAll() {
		//获得所有对象的MySQL语句
		String sql = "select *from "+tableName;
		//实现多个对象的结果集操作
		TransportResultHandler manyResultHandler = new TransportResultHandler();
		//执行查询语句并返回执行结果
		return  DbUtil.executeQuery(sql, manyResultHandler);
		
	}
/**
 * 
 * @author 陌意随影
 TODO :对ResultSet 这个结果集进行处理
 *2019年11月22日  下午6:28:08
 */
class TransportResultHandler implements ResultHandler<List<Transport>>{

	@Override
	public List<Transport> handleResult(ResultSet set) {
		 List<Transport> list = new ArrayList<>();
		 //调用通用方法获取实例类的集合
		DbUtil.mappingEntity(set,list,ConstantsConfig.GATHER_TRANSPORT_ENTITY);
		return list;
	}
}
}