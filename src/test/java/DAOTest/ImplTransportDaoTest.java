package DAOTest;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.qst.dms.dao.TransportDao;
import com.qst.dms.dao.impl.ImplTransportDao;
import com.qst.dms.entity.Transport;

/**
 * @author 陌意随影
 TODO :测试TransportDao实现类
 *2019年11月22日  下午4:39:37
 */
public class ImplTransportDaoTest {

 /**
 * 
 */
@Test
 public void testSave(){
	TransportDao dao=new ImplTransportDao();
	Transport t=new Transport(4,new Date(),"aa",1,"bb","cc",1);
	Boolean fla = dao.save(t);
	System.out.println(fla);
 }
/**
 * 测试删除用户
 */
@Test
public void testRemove(){
	TransportDao dao=new ImplTransportDao();
	Boolean fla =	dao.remove(1);
	System.out.println(fla);
}
/**
 * 测试获取用户
 */
@Test
public void testGet(){
	TransportDao dao=new ImplTransportDao();
	Transport t =dao.get(3);
	System.out.println(t);
}
/**
 * 测试获取全部的对象
 */
@Test
public void testGetAll(){
	TransportDao dao=new ImplTransportDao();
	 List<Transport> list = dao.getAll();
	 for(Transport t:list){
		 System.out.println(t);
	 }
}
}
