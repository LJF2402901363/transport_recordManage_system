package com.qst.dms.dao;

import java.sql.ResultSet;

/**
 * @author 陌意随影
 TODO : 结果集的处理器
 *2019年11月21日  下午7:58:30
 */
public interface ResultHandler <T> {
  /**
   * 在这个方法中对结果集进行操作
 * @param resultset 要操作的结果集
 * @return 返回一个处理过后的结果集
 */
public T  handleResult(ResultSet resultset);
}
