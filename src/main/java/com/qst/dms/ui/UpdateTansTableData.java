package com.qst.dms.ui;
import java.util.List;

import com.qst.dms.dao.impl.ImplTransportDao;
import com.qst.dms.entity.Transport;
import com.qst.dms.factory.DaoFactory;
import com.qst.dms.tablemodel.TransportJTableModel;
/**
 * @author 陌意随影
 TODO :更新主面板中的日志记录表
 *2019年12月12日  下午6:54:35
 */
public class UpdateTansTableData implements Runnable{
	private TransportJTableModel tansJTableModel = null;
	private ImplTransportDao tansDao = null;
	private List<Transport> list = null;
	/**默认每60秒刷新一次 */
	public static final int DEFAULT_TIME = 1000*30;
	@SuppressWarnings("javadoc")
	public UpdateTansTableData(TransportJTableModel tansJTableModel) {
		this.tansJTableModel = tansJTableModel;
		this.tansDao  = (ImplTransportDao) DaoFactory.getDaoImpl("ImplTransportDao");
		
	}
	@Override
	public void run() {
		
	while(true){
		try {
			//获取全部的匹配日志信息
			this.list = this.tansDao.getAll();
			//更新数据模型中所有的日志匹配信息
			this.tansJTableModel.update(this.list);
			Thread.sleep(DEFAULT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	}

}
