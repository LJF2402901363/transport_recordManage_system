package com.qst.dms.data;

import java.util.ArrayList;

import com.qst.dms.entity.MatchedLogRec;

/**
 * @author 陌意随影
 TODO :匹配登录日志的仓库
 *2019年11月21日  下午6:29:22
 */
public class MatchedLogRecStore {
	// 日志数据匹配集合
	private  static ArrayList<MatchedLogRec> oldmatchedLogs=new ArrayList<MatchedLogRec>();
	/**
	 * 修改匹配的日志集合
	 * @param oldmatchedLogs
	 */
	public static void setOldmatchedLogs(ArrayList<MatchedLogRec> oldmatchedLogs) {
		MatchedLogRecStore.oldmatchedLogs = oldmatchedLogs;
	}
	/**
	 * @return 获取老的匹配数据集合
	 */
	public static ArrayList<MatchedLogRec> getOldmatchedLogs() {
		return oldmatchedLogs;
	}
	
}
