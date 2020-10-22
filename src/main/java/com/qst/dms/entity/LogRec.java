
package com.qst.dms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 陌意随影
 TODO :登录日志
 *2019年11月7日  下午6:48:37
 */
public class LogRec extends DataBase implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *  登录用户名
	 */
	private String userName;
	/**
	 * 登录用户主机IP地址
	 */
	private String ip;
	/**
	 * 登录状态：登录、登出
	 */
	private int logType;
	/**
	 * 登录常量LOG_IN、登出常量常量LOG_OUT
	 */
    public static final int LOG_IN=1;
    public static final int LOG_OUT=0;
    
	/**
	 * @return  获取用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param user 设置用户名
	 */
	public void setUserName(String user) {
		this.userName = user;
	}


	/** 
	 * @return 获取用户的ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置用户的ip
	 * @param ip 要设置的ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return  获取登录的类型
	 */
	public int getLogType() {
		return logType;
	}

	
	/**
	 * @param logType  设置登录的类型
	 */
	public void setLogType(int logType) {
		this.logType = logType;
	}

	@SuppressWarnings("javadoc")
	public LogRec() {
	}

	@SuppressWarnings("javadoc")
	public LogRec(int id, Date time, String address, int type,String user,String ip,int logType) {
		super(id,time,address,type);
		this.userName=user;
		this.ip=ip;
		this.logType=logType;
	}
	@SuppressWarnings("javadoc")
	public LogRec( Date time, String address, int type,String user,String ip,int logType) {
		super(time,address,type);
		this.userName=user;
		this.ip=ip;
		this.logType=logType;
	}
	
	public String toString() {
		return this.getId() + "," +this.getTime() + "," +this.getAddress() + "," + this.getType() + ","+userName+","+ip+","+logType;
	}
}
