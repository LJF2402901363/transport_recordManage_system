package com.qst.dms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.dms.dao.MatchedTransportDao;
import com.qst.dms.dao.ResultHandler;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;
import com.qst.dms.util.ConstantsConfig;
import com.qst.dms.util.DbUtil;

/**
 * @author 陌意随影
 TODO :
 *2019年11月22日  下午7:18:35
 */
public class ImplMatchedTransportDao implements MatchedTransportDao {
	private static final String tableName=ConstantsConfig.MATCHED_TRANSPORT;
	@Override
	public List<MatchedTransport> getAll() {
		// 获取全部对象的MySQL语句
		String sql = "select *from "+tableName;
		// 对结果集进行处理的操作
		MatchedTransportHandler matchedTransportHandler = new MatchedTransportHandler();
		// 执行MySQL语句并返回结果集
		return DbUtil.executeQuery(sql, matchedTransportHandler);
	}

	@Override
	public boolean save(MatchedTransport matchedTransport) {
		// 新增对象的MySQL语句
		String sql = "insert into "+tableName+"(sendid,transid,receiveid) values(?,?,?);";
		int sendid = matchedTransport.getSend().getId();
		int transid = matchedTransport.getTrans().getId();
		int receiveid = matchedTransport.getReceive().getId();
		// 执行MySQL语句并返回结果
		int result = DbUtil.executeUpdate(sql, sendid, transid, receiveid);
		return result == 1;
	}

	@Override
	public MatchedTransport get(int sendid, int transid, int receiveid) {
		// 查询某个对象的MySQL语句
		String sql = "select *from "+tableName+" where sendid=? and transid=? and receiveid=?";
		// 对结果集进行处理操作
		MatchedTransportHandler matchedTransportHandler = new MatchedTransportHandler();
		// 执行MySQL语句并返回一个集合对象
		List<MatchedTransport> list = DbUtil.executeQuery(sql, matchedTransportHandler, sendid, transid, receiveid);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean remove(int sendid, int transid, int receiveid) {
		// 删除指定对象的MySQL语句
		String sql = "delete from "+tableName+" where sendid=? and transid=? and receiveid=?";
		// 指向MySQL语句并返回执行结果
		int result = DbUtil.executeUpdate(sql, sendid, transid, receiveid);
		return result == 1;
	}

	@Override
	public boolean update(MatchedTransport t) {
		throw new Error("暂未支持更新");
	}
/**
 * 该匹配日志中不存在关键词id，所以不能通过id删除
 */
	@Override
	public boolean remove(int key) {
		throw new Error("不支持通过id删除");
	}

	@Override
	public MatchedTransport get(int key) {
		throw new Error("不支持通过id获得匹配的日志");
	}
 /**
  * 通过sendid,transid 和 receiverid中的一个id来获取匹配的物流日志sendid,transid 和 receiverid的数组
 * @param id
 * @return 返回一个sendid,transid 和 receiverid的数组的数组
 */
public Integer[] getMatchedTransIds(int id){
		String sql = " select * from "+tableName+" where  sendid=? or transid=? or receiveid=?  ";
		Integer[] ids = DbUtil.executeQuery(sql, new ResultHandler<Integer[]>() {
			@Override
			public Integer[] handleResult(ResultSet set) {
				try {
					Integer[] ids = new Integer[3];
					if (set.next()) {
						int sendid = set.getInt(1);
						int transid = set.getInt(2);
						int receiveid = set.getInt(3);
						ids[0] = sendid;
						ids[1] = transid;
						ids[2] = receiveid;
						return ids;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		}, id, id, id);
		return ids;
	 
 }
	@Override
	public boolean isExits(int sendid, int transid, int receveid) {
	 String sql = "select *from "+tableName+" where sendid=? and transid=? and receiveid=?";
	 ResultHandler<Integer> handler = new ResultHandler<Integer>() {
		@Override
		public Integer handleResult(ResultSet set) {
			try {
				if(set.next()) {
					
					return 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	};
	 int result  = DbUtil.executeQuery(sql,handler, sendid, transid, receveid);
		return  result == 1;
	}

	@Override
	public boolean save(int sendid, int transid, int receiveid) {
		// 新增对象的MySQL语句
		String sql = "insert into "+tableName+"(sendid,transid,receiveid) values(?,?,?);";
		// 执行MySQL语句并返回结果
		int result = DbUtil.executeUpdate(sql,sendid, transid, receiveid);
		return result == 1;
	}
}
/**
 * 
 * @author 陌意随影
 TODO :对ResultSet进行处理
 *2019年11月22日  下午11:35:26
 */
class  MatchedTransportHandler implements ResultHandler<List<MatchedTransport>>{
	private ImplTransportDao dao = null;
	private List<MatchedTransport> list = null;
	public MatchedTransportHandler() {
		dao = new ImplTransportDao();
		list = new ArrayList<MatchedTransport>();
	}

	@Override
	public List<MatchedTransport> handleResult(ResultSet set) {
		try {
			while (set.next()) {
				int sendid = set.getInt("sendid");
				int transid = set.getInt("transid");
				int receiveid = set.getInt("receiveid");
				Transport send = dao.get(sendid);
				Transport trans = dao.get(transid);
				Transport receive = dao.get(receiveid);
				MatchedTransport matchedTransport = new MatchedTransport(send, trans, receive);
				this.list.add(matchedTransport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.list;
	}
	
}
