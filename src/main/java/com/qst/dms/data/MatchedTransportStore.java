package com.qst.dms.data;

import java.util.ArrayList;

import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;

/**
 * @author 陌意随影
 TODO :匹配登录日志的仓库
 *2019年11月21日  下午6:29:22
 */
public class MatchedTransportStore {
	
	// 日志数据匹配集合
	private  static ArrayList<MatchedTransport>  oldmatchedTrans=new ArrayList<>();
	/**
	 * @return 获取老的匹配数据集合
	 */
	public static ArrayList<MatchedTransport> getOldmatchedTrans() {
		return  oldmatchedTrans;
	}
	/**
	 * 修改日志集合
	 * @param oldmatchedTrans
	 */
	public static void setOldmatchedTrans(ArrayList<MatchedTransport> oldmatchedTrans) {
		MatchedTransportStore.oldmatchedTrans = oldmatchedTrans;
	}
}
