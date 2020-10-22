package com.qst.dms.data;

import java.util.ArrayList;
import com.qst.dms.entity.Transport;
/**
 * @author 陌意随影
 TODO :匹配登录日志的仓库
 *2019年11月21日  下午6:29:22
 */
public class TransportStore {
	
	// 日志数据匹配集合
	private  static ArrayList<Transport>  trans=new ArrayList<>();
	/**
	 * @return 获取老的匹配数据集合
	 */
	public static ArrayList<Transport> getTrans() {
		return  trans;
	}
}
