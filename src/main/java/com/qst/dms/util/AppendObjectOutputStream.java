package com.qst.dms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author 陌意随影
 TODO :重写ObjectOutputStream的对象输出流流，解决读取文件不正确的问题
 *2019年11月14日  下午6:51:04
 */
public class AppendObjectOutputStream extends ObjectOutputStream {
	/**在外部实例化的时候必须赋值*/
	private static File file=null;
	private AppendObjectOutputStream(File file) throws IOException, SecurityException {
		super(new FileOutputStream(file,true));
	}
	/**
	 * @param f
	 * @return  返回一个AppendObjectOutputStream对象
	 */
	public static AppendObjectOutputStream newInstance(File f){
		file =f;
		try {
			return new AppendObjectOutputStream(f);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
 @Override
protected void writeStreamHeader() throws IOException {
	 
	 if(file!=null){
		 //如果文件之前为空，则添加一个输入的 字节头，四个字节
		 if(file.length()==0){
			 super.writeStreamHeader();
		 }else{
			 this.reset();
		 }
	 }else{
		 super.writeStreamHeader();
	 }
	 
}
}
