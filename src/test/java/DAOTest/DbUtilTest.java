package DAOTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.Transport;
import com.qst.dms.util.ConstantsConfig;
import com.qst.dms.util.DbUtil;

/**
 * @author 陌意随影
 TODO :
 *2019年12月21日  下午6:20:03
 */
public class DbUtilTest {
    public static void main(String[] args) {
		Connection con = DbUtil.getDruidConnection();
		
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(" select* from  gather_transport");
			ResultSet rs = ps.executeQuery();
//			 List<User> list = new ArrayList<>();
//			con.commit();
//			DbUtil.mappingEntity(rs,list,ConstantsConfig.USERDETAILS);
//			for(User u:list){
//				System.out.println(u);
//			}
			
			
//			 List<LogRec> list = new ArrayList<>();
//				DbUtil.mappingEntity(rs,list,ConstantsConfig.GATHER_LOGREC);
//				for(LogRec u:list){
//					System.out.println(u);
//				}
			con.commit();
			 List<Transport> list = new ArrayList<>();
				DbUtil.mappingEntity(rs,list,ConstantsConfig.GATHER_TRANSPORT_ENTITY);
				for(Transport u:list){
					System.out.println(u);
				}
				
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
