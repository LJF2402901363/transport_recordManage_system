package com.qst.dms.tablemodel;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import com.qst.dms.entity.LogRec;
import com.qst.dms.service.LogRecService;


/**
 * @author 陌意随影
 TODO :
 *2019年12月1日  下午3:44:53
 */
public class LogRecJTableModel extends AbstractTableModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private static final String[] ColumnNames = {"日志ID","采集时间","采集地址","状态","用户名","IP地址","日志类型"};
    private List<LogRec>  list = null;
    
	@SuppressWarnings("javadoc")
	public LogRecJTableModel(List<LogRec> list){
    }
	@SuppressWarnings("javadoc")
	public LogRecJTableModel(){
    }
	@Override
	public int getRowCount() {
		return this.list.size() ;
	}

	@Override
	public int getColumnCount() {
		
		return ColumnNames.length;
	}
 
	@SuppressWarnings("deprecation")
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		LogRec logRec = this.list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return logRec.getId();
		case 1:
			return logRec.getTime().toLocaleString();
		case 2:
			return logRec.getAddress();
		case 3:
			return logRec.getType();
		case 4:
			return logRec.getUserName();
		case 5:
			return logRec.getIp();
		case 6:
			return logRec.getLogType();
		}
		return "";
	}
	@Override
	public String getColumnName(int column) {
		return ColumnNames[column];
	}
	/**
	 * 更新数据模型
	 */
	public void update( List<LogRec> list) {
		this.list = list;
		this.fireTableRowsInserted(0, this.list.size()-1);
	}
	/**
	 * @param list
	 */
	public void setList(List<LogRec> list) {
		this.list = list;
	}
}
