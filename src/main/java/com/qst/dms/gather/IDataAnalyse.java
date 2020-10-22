package com.qst.dms.gather;

import java.util.ArrayList;

/**
 * @author 陌意随影
 TODO :数据分析接口
 *2019年11月7日  下午7:05:13
 */
public interface IDataAnalyse {
	/**
	 * @return  进行数据匹配,返回泛型ArrayList集合
	 */
	ArrayList<?> matchData();
}
