package DAOTest;


import java.util.List;

import org.junit.jupiter.api.Test;

import com.qst.dms.dao.LogRecDao;
import com.qst.dms.dao.impl.ImplMatchedLogRecDao;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.factory.DaoFactory;

public class ImplMatchedLogRecDaoTest {

	/**
	 * 测试添加新的对象
	 */
	@Test
	public  void testSave(){
		ImplMatchedLogRecDao dao =  (ImplMatchedLogRecDao) DaoFactory.getDaoImpl("ImplMatchedLogRecDao");
		System.out.println(dao.save(1, 1));
	}
	/**
	 * 测试 删除对象
	 */
	@Test
	public void testRemove(){
		ImplMatchedLogRecDao dao = new ImplMatchedLogRecDao();
//		System.out.println(dao.remove(2, 2));
	}
	/**
	 * 测试获取单个对象
	 */
	@Test
	public void testGet(){
		ImplMatchedLogRecDao dao = new ImplMatchedLogRecDao();
		MatchedLogRec matchedLogRec =dao.get(3, 3);
		System.out.println(matchedLogRec);
	}
	/**
	 * 测试获取多个对象
	 */
	@Test
	public void testGetAll(){
		ImplMatchedLogRecDao dao = new ImplMatchedLogRecDao();
		List<MatchedLogRec> list =dao.getAll();
		for(MatchedLogRec t :list){
			System.out.println(t);
		}
	}
}
