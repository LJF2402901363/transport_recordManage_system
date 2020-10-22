package com.qst.dms.dao;

import java.util.List;

import com.qst.dms.entity.Transport;

/**
 * @author 陌意随影
 TODO :物流日志类的Dao接口
 *2019年11月21日  下午7:57:27
 * @param <Transport>
 */
public interface TransportDao  extends DAO<Transport>  {
  /**
 * @return 获取所有的对象
 */
public List<Transport> getAll();
}
