package com.qst.dms.dao;

import java.util.List;

import com.qst.dms.entity.MatchedTransport;

/**
 * @author 陌意随影
 TODO :
 *2019年11月22日  下午7:14:21
 */
public interface MatchedTransportDao  extends DAO<MatchedTransport> {
	/**
	 * @return 返回查询的所有对象
	 */
	public List<MatchedTransport> getAll();
	/**
	 * 通过 物流的id获取对应的对象
	 * @param sendid
	 * @param transid
	 * @param receveid
	 * @return 返回一个匹配的物流日志对象
	 */
	public MatchedTransport get(int sendid,int transid,int receveid) ;
	/**
	 * 保存这三个ID匹配的对象
	 * @param sendid
	 * @param transid
	 * @param receveid
	 * @return  保存成功返回true，否则返回false
	 */
	public boolean save(int sendid,int transid,int receiveid);
	/**
	 * 删除这三个ID匹配的对象
	 * @param sendid
	 * @param transid
	 * @param receveid
	 * @return  删除成功返回true，否则返回false
	 */
	public boolean remove(int sendid,int transid,int receveid);
	/**
	 * 返回满足sendid和transid和 receveid 同时存在的数据是否存在
	 * @param sendid
	 * @param transid
	 * @param receveid
	 * @return存在返回true，否则返回false
	 */
	public boolean isExits(int sendid,int transid,int receveid);

}
