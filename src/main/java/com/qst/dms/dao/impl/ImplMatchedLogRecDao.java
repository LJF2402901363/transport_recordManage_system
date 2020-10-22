package com.qst.dms.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.dms.dao.MatchedLogRecDao;
import com.qst.dms.dao.ResultHandler;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.util.ConstantsConfig;
import com.qst.dms.util.DbUtil;
/**
 * @author 陌意随影
 TODO :
 *2019年11月22日  下午7:19:17
 */
public class ImplMatchedLogRecDao implements MatchedLogRecDao {
  private static final String tableName=ConstantsConfig.MATCHED_LOGREC;
	@Override
	public boolean save(MatchedLogRec matchedLogRec) {
		//添加新的对象的MySQL语句
	     	String sql = "insert into "+tableName+" (loginid,logoutid) values(?,?);";
			int result = DbUtil.executeUpdate(sql,matchedLogRec.getLogin().getId(),matchedLogRec.getLogout().getId());
			return result==1;
	}
	/**
	 * 该匹配日志的数据库表没有主键id，所以暂时不实现
	 */
	@Override
	public boolean remove(int key) {
	  throw new Error("暂未支持通过id移除");
	}


	@Override
	public boolean remove(int loginid,int logoutid) {
		//删除指定id的MySQL语句
		String sql = "delete from "+tableName+"  where loginid=? and logoutid=?";
		//执行MySQL语句并返回执行结果
		int result = DbUtil.executeUpdate(sql, loginid,logoutid);
		return result == 1;

	}

	/**
	 * 通过
	 * @param loginid
	 * @param logoutid
	 * @return 通过登录的id和登出的id返回一个匹配日志
	 */
	public MatchedLogRec get(int loginid, int logoutid) {
		//查询单个对象的MySQL语句
		String sql = "select *from "+tableName+"  where loginid=? and logoutid=?";
		//对ResultSet结果集进行操作处理
		MatchedLogRecHandler matchedLogRecHandler = new MatchedLogRecHandler();
		//返回操作处理后的集合
		List<MatchedLogRec> list = DbUtil.executeQuery(sql, matchedLogRecHandler, loginid, logoutid);
		//返回要查询的对象
		return list.size() == 0 ? null : list.get(0);
	}
	@Override
	public List<MatchedLogRec> getAll() {
		//查询所有对象的MySQL语句
		String sql = "select *from "+tableName+" ;";
		//对结果集ResultSet进行处理的操作
		MatchedLogRecHandler matchedLogRecHandler = new MatchedLogRecHandler();
		//执行MySQL语句并返回结果集
		return DbUtil.executeQuery(sql, matchedLogRecHandler);
	}

	@Override
	public boolean update(MatchedLogRec t) {
		String sql ="update "+tableName+" where loginid=? and logoutid=?";
		int result = DbUtil.executeUpdate(sql, t.getLogin().getId(),t.getLogout().getId());
		return result == 1;
	}

	@Override
	public MatchedLogRec get(int key) {
	try {
		throw	new Exception("暂不支持通过id获得匹配的日志");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	}
	/**
	 * 通过登入的id和登出的id
	 * @param id
	 * @return 返回登入和登出的id数组
	 */
	public Integer[]  getMatchedLogId(int id) {
		String sql = "select * from "+tableName+" where loginid=? or logoutid=?;";
		Integer[] ids = DbUtil.executeQuery(sql, new ResultHandler<Integer[]>() {
			@Override
			public Integer[] handleResult(ResultSet set) {
				Integer[] ids = new Integer[2];
				try {
					if (set.next()) {
						int loginid = set.getInt(1);
						int logoutid = set.getInt(2);
						ids[0] = loginid;
						ids[1] = logoutid;
						return ids;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		}, id, id);
		return ids;
	}
	
	@Override
	public boolean isExits(int loginid, int logoutid) {
		String sql = "select *from "+tableName+" where  loginid=? and logoutid=?";

		ResultHandler<Integer> handler = new ResultHandler<Integer>() {
			@Override
			public Integer handleResult(ResultSet set) {
				try {
					if (set.next()) {
						return 1;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
		};
		int result = DbUtil.executeQuery(sql, handler, loginid, logoutid);
		return result == 1;
	}
	@Override
	public boolean save(int loginid, int logoutid) {
		//添加新的对象的MySQL语句
     	String sql = "insert into "+tableName+"(loginid,logoutid) values(?,?);";
		int result = DbUtil.executeUpdate(sql,loginid, logoutid);
		return result==1;
	}

}
/**
 * 
 * @author 陌意随影
 TODO :对ResultSet进行处理操作
 *2019年11月22日  下午11:33:20
 */
class MatchedLogRecHandler implements ResultHandler<List<MatchedLogRec>> {
	ImplLogRecDao dao = new ImplLogRecDao();

	@Override
	public List<MatchedLogRec> handleResult(ResultSet set) {
		List<MatchedLogRec> list = new ArrayList<>();
		MatchedLogRec matchedLogRec = null;
		try {
			while (set.next()) {
				int loginid = set.getInt("loginid");
				int logoutid = set.getInt("logoutid");
				LogRec login = dao.get(loginid);
				LogRec logout = dao.get(logoutid);
				if (login == null || logout == null) {
					continue;
				}
				matchedLogRec = new MatchedLogRec(login, logout);
				list.add(matchedLogRec);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

