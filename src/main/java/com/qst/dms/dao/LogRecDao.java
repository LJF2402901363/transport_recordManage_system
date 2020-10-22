package com.qst.dms.dao;

import java.util.List;

import com.qst.dms.entity.LogRec;

/**
 * @author 陌意随影
 TODO :
 *2019年11月21日  下午8:04:52
 */
public interface LogRecDao extends DAO<LogRec>{
	/**
	 * 移除数据库中指定的数据
	 * @param logRec 需要删除的日志记录
	 * @return 移除陈宫返回true，否则返回false
	 */
	public boolean remove( LogRec logRec);
	 /**
	 * @return 获取所有的对象
	 */
	public List<LogRec> getAll();
}
