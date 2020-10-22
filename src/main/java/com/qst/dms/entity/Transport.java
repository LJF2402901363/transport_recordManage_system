package com.qst.dms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 陌意随影
 TODO :货运物流信息
 *2019年11月7日  下午6:55:59
 */
public class Transport extends DataBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 经手人
	 */
	private String handler;
	/**
	 * 收货人
	 */
	private String reciver;
	/**
	 * 物流状态
	 */
	private int transportType;
	/**
	 * 物流状态常量:发货中
	 */
	public static final int SENDDING = 1;// 发货中
	/**
	 * 物流状态常量: 送货中
	 */
	public static final int TRANSPORTING = 2;// 送货中
	/**
	 * 物流状态常量: 已签收
	 */
	public static final int RECIEVED = 3;// 已签收

	/**
	 * @return  获取经手人
	 */
	public String getHandler() {
		return handler;
	}

	/**
	 * @param handler 设置经手人
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}

	/**
	 * @return 获取接收者
	 */
	public String getReciver() {
		return reciver;
	}

	/**
	 * @param reciver  设置接收者
	 */
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	/**
	 * @return  获取货物运输状态
	 */
	public int getTransportType() {
		return transportType;
	}

	/**
	 * @param transportType  设置货物运输状态
	 */
	public void setTransportType(int transportType) {
		this.transportType = transportType;
	}

	@SuppressWarnings("javadoc")
	public Transport() {

	}

	@SuppressWarnings("javadoc")
	public Transport(int id, Date time, String address, int type,
			String handler, String reciver, int transportType) {
		super(id, time, address, type);
		this.handler = handler;
		this.reciver = reciver;
		this.transportType = transportType;
	}
	@SuppressWarnings("javadoc")
	public Transport( Date time, String address, int type,
			String handler, String reciver, int transportType) {
		super( time, address, type);
		this.handler = handler;
		this.reciver = reciver;
		this.transportType = transportType;
	}
	public String toString() {
		return this.getId() + "," + this.getTime() + "," + this.getAddress()
				+ "," + this.getType() + "," + handler + "," + transportType;
	}

}
