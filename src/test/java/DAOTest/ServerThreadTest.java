package DAOTest;

import java.util.Date;
import java.util.List;


import com.qst.dms.dao.impl.ImplMatchedLogRecDao;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.net.ClientThread;
import com.qst.dms.net.ServerThread;
import com.qst.dms.util.ConstantsConfig;

/**
 * @author 陌意随影
 TODO :
 *2019年12月19日  下午7:32:19
 */
public class ServerThreadTest {
/**
 * @param args
 */
public static void main(String[] args) {
	 new ServerThread(ConstantsConfig.PORT).start();
	 ClientThread  logRecClientThread = new ClientThread(ConstantsConfig.HOST, ConstantsConfig.PORT);
	 ImplMatchedLogRecDao dao = new ImplMatchedLogRecDao();
	 logRecClientThread.start();
//	 int id, Date time, String address, int type,String user,String ip,int logType
	 LogRec logRec1 = new LogRec(31,new Date(),"1",2,"A","A",1);
	 LogRec logRec2 = new LogRec(32,new Date(),"1",2,"A","A",0);
		logRecClientThread.sendMatchedLogRec(new MatchedLogRec(logRec1,logRec2));
}

}
