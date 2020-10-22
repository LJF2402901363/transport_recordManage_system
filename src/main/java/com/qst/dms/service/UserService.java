package com.qst.dms.service;

import java.util.List;

import com.qst.dms.dao.impl.ImplUserDao;
import com.qst.dms.entity.User;

/**
 * @author 陌意随影
 TODO :这里封装了对用户的各种业务逻辑
 *2019年11月28日  下午10:22:12
 */
public class UserService extends Service<User>{
     private ImplUserDao userDao = null;

	@SuppressWarnings("javadoc")
	public UserService() {
		this.userDao = new ImplUserDao();
	}
    /**
     * 保存用户信息到数据库
     * @param user
     * @return  返回保存是否成功
     */ 
    public boolean saveUser(User user){
    	User u  = this.userDao.getUser(user.getUserName(), user.getPassword());
    	if(u != null){
    		return false;
    	}
		return this.userDao.save(user);
    }
    
    /**
     * 通过Id更新指定的用户，该用户的id是不变的，所以可以通过id来更新用户
     * @param newUser
     * @return  返回是否更新成功，更新成功返回true,反之返回false
     */
    public boolean updateUser(User newUser){
		return this.userDao.update(newUser);
    	
    }
    
    
   /**
    * 如果对应名字和密码的用户存在则返回完整信息的用户，否则返回null
 * @param username
 * @param password
 * @return  返回获取的用户
 */
public User LoginUser(String username,String password){
	User  user = this.userDao.getUser(username, password);
	return user;
   }
/**
* @param username
* @return  返回所有该用户名的用户集合
*/
public List<User> getUserByUserName(String username){
	List<User>  list = this.userDao.getUserByUserName(username);
	return list;
 }
@Override
public void saveToDataBase(List<User> list) {
	for(User user: list){
		User u  = this.userDao.getUser(user.getUserName(), user.getPassword());
		if(u == null){
			this.userDao.save(user);
		}
	}
}
@Override
public void saveToDataBase(User... params) {
	for(User user:params){
	   //首先获取该用户，如果该用户已经存在则不用保存
		User u  = this.userDao.getUser(user.getUserName(), user.getPassword());
		if(u == null){
			this.userDao.save(user);
		}
	}
	
}
@Override
public void saveToLocal(String pathName, User... params) {
	super.saveToLocal(pathName, params);
}
@Override
public void saveToLocal(String pathName, List<User> list) {
	super.saveToLocal(pathName, list);
}
@Override
public List<User> readFromLocal(String pathName) {
	return super.readFromLocal(pathName);
}    
}
