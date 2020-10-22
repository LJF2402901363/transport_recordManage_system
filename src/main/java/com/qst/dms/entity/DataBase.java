
package com.qst.dms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 陌意随影
 TODO :基础数据类
 *2019年11月7日  下午7:25:55
 */

public class DataBase implements Serializable{
	private static final long serialVersionUID = 1L;
	/**ID标识*/
	private int id;
	/**时间*/
	private Date time;
	/**地点*/
	private String address;
	/**状态*/
	private int type;
	/**"采集"*/
	public static final int GATHER=1;
	/**"匹配"*/
	public static final int MATHCH=2;
	/**"记录"*/
	public static final int RECORD=3;
	/**发送*/
	public static final int SEND=4;
	/**"接收"*/
	public static final int RECIVE=5;
	/**"归档"*/
	public static final int WRITE=6;
	/**"保存"*/
	public static final int SAVE=7;

	/**
	 * @return 获取id
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * @param id 设置id
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return  获取时间
	 */
	public Date getTime() {
		return time;
	}

	
	/**
	 * @param time  设置时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}


	/**
	 * @return  获取地址
	 */
	public String getAddress() {
		return address;
	}

	
	/**
	 * @param address  设置地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return  获取
	 */
	public int getType() {
		return type;
	}


	/**
	 * @param type 设置状态
	 */
	public void setType(int type) {
		this.type = type;
	}

	@SuppressWarnings("javadoc")
	public DataBase() {
	}

	@SuppressWarnings("javadoc")
	public DataBase(int id, Date time, String address, int type) {
		this.id = id;
		this.time = time;
		this.address = address;
		this.type = type;
	}
	@SuppressWarnings("javadoc")
	public DataBase(Date time, String address, int type) {
		this.time = time;
		this.address = address;
		this.type = type;
	}

	public String toString() {
		return id + "," + time + "," + address + "," + type;
	}

}
