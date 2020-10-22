package DAOTest;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.qst.dms.dao.LogRecDao;
import com.qst.dms.dao.impl.ImplLogRecDao;
import com.qst.dms.entity.LogRec;
import com.qst.dms.factory.DaoFactory;

/**
 * @author 陌意随影
 TODO :LogRecDao实现类的测试类
 *2019年11月22日  下午6:51:32
 */
public class ImplLogRecDaoTest {

/**
 * 测试添加方法
 */
@Test
public void testSave(){
	LogRecDao dao = (LogRecDao) DaoFactory.getDaoImpl("ImplLogRecDao");
	LogRec  logRec = new LogRec(4,new Date(),"ddd",1,"aa","dd",0);
   System.out.println(	dao.save(logRec));
}
/**
 * 测试删除用户
 */
@Test
public void testRemove(){
	LogRecDao dao = new ImplLogRecDao();
	System.out.println(dao.remove(4));
}
/**
 * 测试更新对象
 */
@Test
public void testUpdate(){
	LogRecDao dao = new ImplLogRecDao();
	LogRec  logRec = new LogRec(4,new Date(),"ddd",2,"aa","dd",0);
	System.out.println(dao.update(logRec));
}
/**
 * 测试查询单个对象
 */
@Test
public void testGet(){
	LogRecDao dao = new ImplLogRecDao();
	System.out.println(dao.get(2));
}
/**
 * 测试查询所有对象
 */
@Test
public void testGetAll(){
	LogRecDao dao = new ImplLogRecDao();
	List<LogRec>  list = dao.getAll();
	for(LogRec t:list){
		System.out.println(t);
	}
	System.out.println(dao.get(2));
}
}
