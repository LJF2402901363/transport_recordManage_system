
package com.qst.dms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 陌意随影
 TODO :匹配日志记录，"登录登出对" 类型
 *2019年11月7日  下午6:50:48
 */

public class MatchedLogRec implements Serializable {
	private static final long serialVersionUID = 1L;
	private LogRec login;
	private LogRec logout;

	/**
	 * @return  返货用户名
	 */
	public String getUser() {
		return login.getUserName();
	}

	/**
	 * @return 返回登入时刻 
	 */
	public Date getLogInTime() {
		return login.getTime();
	}

	/**
	 * @return  返回登出时刻
	 */
	public Date getLogoutTime() {
		return logout.getTime();
	}

	/**
	 * @return  返回登入记录
	 */
	public LogRec getLogin() {
		return login;
	}

	/**
	 * @return 返回 登出记录
	 */
	public LogRec getLogout() {
		return logout;
	}

	@SuppressWarnings("javadoc")
	public MatchedLogRec() {
	}

	@SuppressWarnings("javadoc")
	public MatchedLogRec(LogRec login, LogRec logout) {
		if (login.getLogType() != LogRec.LOG_IN) {
			System.out.println(login.getLogType());
			throw new RuntimeException("不是登入记录!");
		}
		if (logout.getLogType() != LogRec.LOG_OUT) {
			System.out.println(logout.getLogType());
			throw new RuntimeException("不是登出记录");
		}
		if (!login.getUserName().equals(logout.getUserName())) {
			throw new RuntimeException("登录登出必须是同一个用户!");
		}
		if (!login.getIp().equals(logout.getIp())) {
			throw new RuntimeException("登录登出必须是同一个IP地址!");
		}
		this.login = login;
		this.logout = logout;
	}

	public String toString() {
		return login.toString() + " | " + logout.toString();
	}

}
