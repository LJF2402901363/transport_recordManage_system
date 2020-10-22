package com.qst.dms.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.dms.dao.ResultHandler;
import com.qst.dms.dao.UserDao;
import com.qst.dms.entity.User;
import com.qst.dms.util.AESUtil;
import com.qst.dms.util.ConstantsConfig;
import com.qst.dms.util.DbUtil;
/**
 * @author 陌意随影
 TODO :用户类的数据库操作类
 *2019年11月28日  下午10:35:21
 */
public class ImplUserDao implements UserDao {
	private static final String tableName=ConstantsConfig.USERDETAILS;
	@Override
	public boolean save(User t) {
		//使用配置文件中的口令，对密码进行加密
		String encryptPassword = AESUtil.Encode(t.getPassword(), ConstantsConfig.SUCURE_PASSWORD);
		String sql = "insert into "+tableName+"  (username,password,sex,hobby,address,degree) values(?,?,?,?,?,?)";
		int result = DbUtil.executeUpdate(sql,t.getUserName(),encryptPassword,t.getSex(),t.getHobby(),t.getAddress(),t.getDegree());
		return result == 1;
	}

	@Override
	public boolean update(User t) {
		String sql = "update "+tableName+"  set username=?,password=?,sex=?,hobby=?,address=?,degree=? where id=?";
		String encryptPassword = AESUtil.Encode(t.getPassword(), ConstantsConfig.SUCURE_PASSWORD);
		int result =DbUtil.executeUpdate(sql, t.getUserName(), encryptPassword,t.getSex(),t.getHobby(),t.getAddress(),t.getDegree(),t.getId());
		return result == 1;
	}

	@Override
	public boolean remove(int key) {
		String sql  = "delete from "+tableName+"  where id=?";
		int result =DbUtil.executeUpdate(sql,key);
		return result == 1;
	}
	@Override
	public User get(int key) {
		String sql = "select *from "+tableName+"  where id=?";
		List<User> list = DbUtil.executeQuery(sql, new UserResultHandler(), key);
		return list.size()==0?null:list.get(0);
	}
	@Override
	public List<User> getAll() {
		String sql = "select *from userdetails";
		List<User> list = DbUtil.executeQuery(sql, new UserResultHandler());
		return list;
	}

	@Override
	public User getUser(String username, String password) {
		String encryptPassword = AESUtil.Encode(password, ConstantsConfig.SUCURE_PASSWORD);
		String sql = "select *from "+tableName+"  where binary username=? and binary password=?";
		List<User> list = DbUtil.executeQuery(sql, new UserResultHandler(), username,encryptPassword);
		return list.size()==0?null:list.get(0);
	}
	/**
	 * @param username
	 * @return 返回所有该用户名的集合
	 */
	public List<User> getUserByUserName(String username) {
		String sql = "select *from "+tableName+"  where binary username=? ";
		List<User> list = DbUtil.executeQuery(sql, new UserResultHandler(), username);
		return list;
	}

}
class UserResultHandler implements ResultHandler<List<User>>{
	@Override
	public List<User> handleResult(ResultSet set) {
		List<User> list = new ArrayList<>();
		 //调用通用方法获取实例类的集合
		DbUtil.mappingEntity(set, list, ConstantsConfig.USERDETAILS_ENTITY);;
		return list;
	}
	
}
