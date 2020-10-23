package com.qst.dms.service;
import java.util.List;

import com.qst.dms.dao.impl.ImplMatchedTransportDao;
import com.qst.dms.dao.impl.ImplTransportDao;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;
import com.qst.dms.factory.DaoFactory;

/**
 * @author 陌意随影
 TODO :
 *2019年11月7日  下午7:29:39
 */
public class TransportService extends Service<MatchedTransport>{
	private ImplMatchedTransportDao implMatchedTransportDao = null;
	private ImplTransportDao implTransportDao = null;
	
	@SuppressWarnings("javadoc")
	public TransportService() {
		this.implMatchedTransportDao = (ImplMatchedTransportDao) DaoFactory.getDaoImpl("ImplMatchedTransportDao");;
		this.implTransportDao = (ImplTransportDao)DaoFactory.getDaoImpl("ImplTransportDao");;
	}
	/**
	 * 通过匹配的物流sendid，transid和receiveid中的其中一个ID删除另外匹配的这两条数据
	 * @return 返回是否删除成功
	 */
	public boolean removeMatchedTransBySendid(int  id){
		Integer[] ids = this.implMatchedTransportDao.getMatchedTransIds(id);
		if(ids == null || ids.length != 3){
			return false;
		}
		int sendid = ids[0];
		int transid = ids[1];
		int receiveid = ids[2];
		boolean fla1 = this.implTransportDao.remove(sendid);
		boolean fla2 = this.implTransportDao.remove(transid );
		boolean fla3 = this.implTransportDao.remove(receiveid);
		boolean fla4 = this.implMatchedTransportDao.remove(sendid, transid, receiveid);
		if(fla1 && fla2&& fla3&&fla4){
			return true;
		}
		return false;
		
	}
 /**
 * 将匹配的物流日志存储到数据库中
 */
public void saveToDataBase(List<MatchedTransport> matchedTransports){
		if (matchedTransports == null) {
			return;
		}
		for (int i = 0; i < matchedTransports.size(); i++) {
			MatchedTransport matchedTransport = matchedTransports.get(i);
			int sendid = matchedTransport.getSend().getId();
			int transid = matchedTransport.getTrans().getId();
			int receveid = matchedTransport.getReceive().getId();

			boolean fla = this.implMatchedTransportDao.isExits(sendid, transid, receveid);
			if (!fla) {
				boolean fla1 = this.implTransportDao.save(matchedTransport.getSend());
				boolean fla2 = this.implTransportDao.save(matchedTransport.getTrans());
				boolean fla3 = this.implTransportDao.save(matchedTransport.getReceive());
				if (!fla1 || !fla2 || !fla3) {
					System.out.println("存储到数据库失败！");
				}
			}
		}
		List<Transport> list = this.implTransportDao.getAll();
		for (int i = 0; i < list.size(); i = i + 3) {
			Transport send = list.get(i);
			Transport trans = list.get(i + 1);
			Transport receve = list.get(i + 2);
			boolean fla = this.implMatchedTransportDao.isExits(send.getId(), trans.getId(), receve.getId());
			if (!fla) {
				this.implMatchedTransportDao.save(send.getId(), trans.getId(), receve.getId());
			}
		}

	}
/**
* 将匹配的物流日志存储到数据库中
*/
public void saveToDataBase(MatchedTransport...  matchedTransports){
		for (int i = 0; i < matchedTransports.length; i++) {
			MatchedTransport matchedTransport = matchedTransports[i];
			int sendid = matchedTransport.getSend().getId();
			int transid = matchedTransport.getTrans().getId();
			int receveid = matchedTransport.getReceive().getId();
			boolean fla = this.implMatchedTransportDao.isExits(sendid, transid, receveid);
			if (!fla) {
				boolean fla1 = this.implTransportDao.save(matchedTransport.getSend());
				boolean fla2 = this.implTransportDao.save(matchedTransport.getTrans());
				boolean fla3 = this.implTransportDao.save(matchedTransport.getReceive());
				if (!fla1 || !fla2 || !fla3) {
					System.out.println("存储到数据库失败！");
				}
			}
		}
		List<Transport> list = this.implTransportDao.getAll();
		for (int i = 0; i < list.size(); i = i + 3) {
			Transport send = list.get(i);
			Transport trans = list.get(i + 1);
			Transport receve = list.get(i + 2);
			boolean fla = this.implMatchedTransportDao.isExits(send.getId(), trans.getId(), receve.getId());
			if (!fla) {
				this.implMatchedTransportDao.save(send.getId(), trans.getId(), receve.getId());
			}
		}
}
/**
 * 从数据库中读取数据
 */
public  List<MatchedTransport> readMatchTransportFromDataBase(){
	//从数据库中获取全部的匹配物流日志
	List<MatchedTransport> list = this.implMatchedTransportDao.getAll();
	//将数据库获得的匹配全部添加到匹配的日志集合中
	return list;
}
/**
 * 从数据库中读取文件
 */
public List<Transport>  readTransportFromDataBase(){
	//从数据库中获取全部的匹配物流日志
	List<Transport> list = this.implTransportDao.getAll();
	//将数据库获得的匹配全部添加到匹配的日志集合中
	return list;
}
public void saveToLocal(String pathName, MatchedTransport... params) {
	super.saveToLocal(pathName, params);
}
@Override
public void saveToLocal(String pathName, List<MatchedTransport> list) {
	super.saveToLocal(pathName, list);
}
@Override
public List<MatchedTransport> readFromLocal(String pathName) {
	return super.readFromLocal(pathName);
}
}
	

