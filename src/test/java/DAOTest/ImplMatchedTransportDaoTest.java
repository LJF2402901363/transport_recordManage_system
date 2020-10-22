package DAOTest;



import java.util.List;

import org.junit.jupiter.api.Test;

import com.qst.dms.dao.impl.ImplMatchedTransportDao;
import com.qst.dms.entity.MatchedTransport;

public class ImplMatchedTransportDaoTest {

/**
 * 测试添加新的对象
 */
@Test
public  void testSave(){
	ImplMatchedTransportDao dao = new ImplMatchedTransportDao();
//	dao.save(1,3,7);
}
/**
 * 测试 删除对象
 */
@Test
public void testRemove(){
	ImplMatchedTransportDao dao = new ImplMatchedTransportDao();
	System.out.println(dao.remove(1, 3,7));
}
/**
 * 测试获取单个对象
 */
@Test
public void testGet(){
	ImplMatchedTransportDao dao = new ImplMatchedTransportDao();
	MatchedTransport t = dao.get(1,2,3);
	System.out.println(t);
}
/**
 * 测试获取多个对象
 */
@Test
public void testGetAll(){
	ImplMatchedTransportDao dao = new ImplMatchedTransportDao();
	List<MatchedTransport> list =dao.getAll();
	for(MatchedTransport t :list){
		System.out.println(t);
	}
}

}
