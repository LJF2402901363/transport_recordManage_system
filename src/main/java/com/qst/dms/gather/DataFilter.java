package com.qst.dms.gather;
import java.util.ArrayList;
import com.qst.dms.entity.DataBase;

/**
 * @author 陌意随影
 TODO :数据过滤抽象类
 *2019年11月7日  下午7:03:30
 */
public abstract class DataFilter {
	/**数据集合,使用泛型集合*/
	private ArrayList<? extends DataBase> datas;

	/**
	 * @return 返回数据集合的容器
	 */
	public ArrayList<? extends DataBase> getDatas() {
		return datas;
	}

	/**
	 * @param datas  设置数据集合的容器
	 */
	public void setDatas(ArrayList<? extends DataBase> datas) {
		this.datas = datas;
	}

	@SuppressWarnings("javadoc")
	public DataFilter() {

	}

	@SuppressWarnings("javadoc")
	public DataFilter(ArrayList<? extends DataBase> datas) {
		this.datas = datas;
	}

	/**
	 *  数据过滤抽象方法
	 */
	public abstract void doFilter();
}
