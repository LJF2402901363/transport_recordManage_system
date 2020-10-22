package com.qst.dms.dao;

import java.util.List;

import com.qst.dms.entity.User;

/**
 *@author 陌意随影
 TODO :用户类的Dao接口
 *2019年11月28日  下午10:24:36
 */
public interface UserDao extends DAO<User>{
 /**
 * @return 返回全部的用户
 */
public List<User> getAll();
/**
 * @param username 用户名
 * @param password 用户密码
 * @return 返回匹配该用户的对象
 */
public User getUser(String username,String password);
}
