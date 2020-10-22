package com.qst.dms.dao;

/**
 * @author 陌意随影
 TODO :数据库增删查改的顶级接口
 *2019年11月21日  下午7:46:40
 */
public interface DAO <T>{
 /**
  * 保存新的对象并返回是否添加成功
 * @param t
 * @return 添加成功返回true，反之则返回false
 */
public boolean save(T t);
/**
 * 更新指定的对象
 * @param t 需要更新的对象
 * @return 返回是否更新成功
 */
public boolean update(T t);
/**
 * 根据指定的主键来删除对象
 * @param key 需要被删除的主键
 * @return 删除成功返回true，否则返回false
 */
public boolean remove(int key);
/**
 * 根据指定的关键字获取对象
 * @param key  需要获取对象的主键
 * @return 返回一个对象
 */
public T get(int key);

}
