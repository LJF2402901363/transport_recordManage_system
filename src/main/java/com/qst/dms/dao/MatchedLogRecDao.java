package com.qst.dms.dao;

import java.util.List;

import com.qst.dms.entity.MatchedLogRec;
/**
 * @author 陌意随影
 TODO :匹配登录日志的DAO接口
 *2019年11月22日  下午7:12:05
 */
public interface MatchedLogRecDao extends DAO<MatchedLogRec>{
/**
 * @return 获取全部的匹配日志
 */
public List<MatchedLogRec> getAll();
/**
 * 移除指定的对象
 * @param loginid 登入的ID
 * @param logoutid 登出的ID
 * @return  移除成功返回true，否则返回false
 */
public boolean remove(int loginid,int logoutid);
/**
 * 保存数据
  * @param loginid 登入的ID
 * @param logoutid 登出的ID
 * @return  保存成功返回true，否则返回false
 */
public boolean save(int loginid,int logoutid);
/**
 * 返回满足loginid 和 logoutid 同时的数据是否存在
  * @param loginid 登入的ID
 * @param logoutid 登出的ID
 * @return存在返回true，否则返回false
 */
public boolean isExits(int loginid,int logoutid);


}
