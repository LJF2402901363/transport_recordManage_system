package DAOTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author 陌意随影
 TODO :
 *2019年12月19日  下午11:24:25
 */
public class test {
 /**
 * @param args
 */
public static void main(String[] args) {
      File file = new File("config");
      boolean fla = file.isDirectory();
      System.out.println(fla);
      System.out.println(file.exists());
}
}
