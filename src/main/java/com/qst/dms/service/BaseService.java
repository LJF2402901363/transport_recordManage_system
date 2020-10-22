package com.qst.dms.service;

import java.util.List;



/**
 * @author 陌意随影
 TODO :基本的业务逻辑
 *2019年12月20日  上午12:19:45
 * @param <T>
 */
public interface BaseService<T> {
	/**
	 * 将指定集合的数据保存到数据库
	 * @param list
	 */
	public void saveToDataBase(List<T> list );
	/**
	 * 将指定集合的数据保存到数据库
	 * @param params 
	 */
	public void saveToDataBase(T...  params );
	/**
	 * 将指定的对象保存到本地本间路径
	 * @param pathName
	 * @param params
	 */
	public void saveToLocal(String pathName,T...  params );
	/**
	 * 将指定的对象保存到本地本间路径
	 * @param pathName
	 * @param list
	 */
	public void saveToLocal(String pathName,List<T> list);
	/**
	 * 从指定的文件路径读取文件
	 * @param pathName 
	 */
	public List<T> readFromLocal(String pathName);
}
