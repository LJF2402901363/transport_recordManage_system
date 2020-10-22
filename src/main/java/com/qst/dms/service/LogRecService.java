package com.qst.dms.service;
import java.util.List;

import com.qst.dms.dao.impl.ImplLogRecDao;
import com.qst.dms.dao.impl.ImplMatchedLogRecDao;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.factory.DaoFactory;

/**
 * @author 陌意随影
 TODO :日志业务类
 *2019年11月7日  下午7:26:58
 */
public class LogRecService  extends Service<MatchedLogRec> {
	/** 数据库链接 */
	private ImplMatchedLogRecDao matchedLogRecDao = null;
	private ImplLogRecDao logRecDao = null;
	@SuppressWarnings("javadoc")
	public LogRecService() {
		this.matchedLogRecDao = (ImplMatchedLogRecDao) DaoFactory.getDaoImpl("ImplMatchedLogRecDao");
		this.logRecDao = (ImplLogRecDao) DaoFactory.getDaoImpl("ImplLogRecDao");;
	}
/**
 * 将匹配的登录日志匹配到文件中去
 */
public void saveToDataBase(List<MatchedLogRec> matchLogs ){
		if (matchLogs == null) {
			return;
		}
		for (int i = 0; i < matchLogs.size(); i++) {
			MatchedLogRec matchLog = matchLogs.get(i);
			int loginid = matchLog.getLogin().getId();
			int logoutid = matchLog.getLogout().getId();
			boolean fla = this.matchedLogRecDao.isExits(loginid, logoutid);
			if (!fla) {
				System.out.println(matchLog);
				boolean fla1 = this.logRecDao.save(matchLog.getLogin());
				boolean fla2 = this.logRecDao.save(matchLog.getLogout());
				if (!fla1 || !fla2) {
					System.out.println("存储到数据库时失败");
				}
			}
		}
		List<LogRec> list = this.logRecDao.getAll();
		for (int i = 0; i < list.size(); i = i + 2) {
			LogRec login = list.get(i);
			LogRec logout = list.get(i + 1);
			boolean fla = this.matchedLogRecDao.isExits(login.getId(), logout.getId());
			if (!fla) {
				this.matchedLogRecDao.save(login.getId(), logout.getId());
			}
		}
   }
/**
 * 通过loginid 或 logoutid中的一个id来删除对应的两条记录
 * @param id
 * @return 返回是否移除成功
 */
public boolean remove(int id){
	Integer[] ids = this.matchedLogRecDao.getMatchedLogId(id);
	if(ids == null || ids.length != 2){
		return false;
	}
	int loginid = ids[0];
	int logoutid = ids[1];
	boolean fla1 =this.logRecDao.remove(loginid);
	boolean fla2 =this.logRecDao.remove(logoutid);
	boolean fla3 = this.matchedLogRecDao.remove(loginid, logoutid);
	if(fla1&&fla2&&fla3){
		return true;
	}else{
		return false;
	}
	
}


/**
 * 将匹配的登录日志匹配到文件中去
 */
public void saveToDataBase(MatchedLogRec... matchLogs ){
	if(matchLogs == null){
		return ;
	}
	   for(int i = 0;i < matchLogs.length;i++){
		 MatchedLogRec matchLog = matchLogs[i];
		 
		 int loginid = matchLog.getLogin().getId();
		 int logoutid = matchLog.getLogout().getId();
		 boolean fla = this.matchedLogRecDao.isExits(loginid, logoutid);
		 if(!fla) {
			 System.out.println(matchLog);
			 boolean fla1 = this.logRecDao.save(matchLog.getLogin());
			 boolean fla2 = this.logRecDao.save(matchLog.getLogout());
			 if(!fla1||!fla2) {
				 System.out.println("存储到数据库时失败");
			 }
		 }
	   }
	   List<LogRec> list = this.logRecDao.getAll();
	   for(int i = 0; i < list.size();i= i+2){
		   LogRec  login = list.get(i);
		   LogRec  logout = list.get(i+1);
		   System.out.println(login);
		   System.out.println(logout);
		   boolean fla = this.matchedLogRecDao.isExits(login.getId(), logout.getId());
		   if(!fla){
			   this.matchedLogRecDao.save(login.getId(),logout.getId());
		   }
	   }
   }
/**
 * 读匹配日志信息保存，参数是集合
 */
public List<LogRec>  readLogfromDataBase() {
	//获取全部的匹配登录日志
	List<LogRec> list = this.logRecDao.getAll();
	return list;
}
@Override
public void saveToLocal(String pathName, MatchedLogRec... params) {
	super.saveToLocal(pathName, params);
}
@Override
public void saveToLocal(String pathName, List<MatchedLogRec> list) {
	super.saveToLocal(pathName, list);
	
}
@Override
public List<MatchedLogRec> readFromLocal(String pathName) {
	return super.readFromLocal(pathName);
}
}
