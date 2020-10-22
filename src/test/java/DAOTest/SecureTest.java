package DAOTest;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;

/**
 * @author 陌意随影
 TODO :测试加密算法
 *2019年12月5日  下午7:33:32
 */
public class SecureTest {
/**
 * @param args
 */
public static void main(String[] args) {
	saveDesKey();
}
/**
 * 
 */
public static void saveDesKey(){     
    try {
        SecureRandom sr = new SecureRandom();
        //为我们选择的DES算法生成一个KeyGenerator对象
        KeyGenerator kg = KeyGenerator.getInstance ("DES" );
        kg.init (sr);
        FileOutputStream fos = new FileOutputStream("DesKey.xml");
       ObjectOutputStream oos = new ObjectOutputStream(fos);
        //生成密钥
        Key key = kg.generateKey();
      oos.writeObject(key);
      oos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
}

}
