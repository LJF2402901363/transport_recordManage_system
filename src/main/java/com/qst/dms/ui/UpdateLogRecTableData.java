package com.qst.dms.ui;

import java.util.List;

import com.qst.dms.dao.impl.ImplLogRecDao;
import com.qst.dms.dao.impl.ImplMatchedLogRecDao;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.factory.DaoFactory;
import com.qst.dms.tablemodel.LogRecJTableModel;
/**
 * @author 陌意随影
 TODO :更新主面板中的日志记录表
 *2019年12月12日  下午6:54:35
 */
public class UpdateLogRecTableData implements Runnable{
	private LogRecJTableModel logRecJTableModel = null;
	private ImplLogRecDao LogRecDao = null;
	private List<LogRec> list = null;
	/**默认每60秒刷新一次 */
	public static final int DEFAULT_TIME = 1000* 30;
	@SuppressWarnings("javadoc")
	public UpdateLogRecTableData(LogRecJTableModel logRecJTableModel) {
		this.logRecJTableModel = logRecJTableModel;
		this.LogRecDao  =(ImplLogRecDao) DaoFactory.getDaoImpl("ImplLogRecDao");
		
	}
	@Override
	public void run() {
		
	while(true){
		try {
			//获取全部的匹配日志信息
			this.list = this.LogRecDao.getAll();
			//更新数据模型中所有的日志匹配信息
			this.logRecJTableModel.update(this.list);
			Thread.sleep(DEFAULT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	}

}
