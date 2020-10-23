package com.qst.dms.tablemodel;

import java.io.Serializable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.qst.dms.entity.Transport;


/**
 * @author 陌意随影
 TODO :
 *2019年12月1日  下午3:44:53
 */
public class TransportJTableModel extends AbstractTableModel implements Serializable{
	private static final long serialVersionUID = 1L;
    private static final String[] ColumnNames = {"物流ID","采集时间","采集地址","状态","经手人","收货人","物流类型"};
   

	private List<Transport>  list = null;
    @SuppressWarnings("javadoc")
	public TransportJTableModel(){
    }
    @SuppressWarnings("javadoc")
	public TransportJTableModel(List<Transport>  list){
    	this.list = list;
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
		Transport transport= this.list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return transport.getId();
		case 1:
			return transport.getTime().toLocaleString();
		case 2:
			return transport.getAddress();
		case 3:

			return transport.getType();
		case 4:
			return transport.getHandler();
		case 5:
			return transport.getReciver();
		case 6:
			return transport.getTransportType();
		}
		return "";
	}
	@Override
	public String getColumnName(int column) {
		
		return ColumnNames[column];
	}
	
	 /**
	 * @param list
	 */
	public void setList(List<Transport> list) {
			this.list = list;
		}
	/**
	 * 更新数据模型
	 */
	public void update(List<Transport> list) {
		this.list =list;
		this.fireTableRowsInserted(0, this.list.size()-1);
	}
}
