package com.qst.dms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.qst.dms.util.AppendObjectOutputStream;
/**
 * @author 陌意随影
 TODO :服务的抽象类
 *2019年12月20日  下午9:01:19
 * @param <T>
 */
public abstract class Service<T> implements BaseService<T>{
	public void saveToLocal(String pathName, T... params) {
		 if(params == null || params.length == 0|| pathName == null|| pathName.length() == 0 ){
			 return ;
		 }
		 File file = new File(pathName);
		 ObjectOutputStream osm =null;
		 try {
			 osm = AppendObjectOutputStream.newInstance(file);
			for(int i = 0;i < params.length;i++){
				osm.writeObject(params[i]);
			}
			osm.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(osm != null){
				try {
					osm.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		 
	}
	@Override
	public void saveToLocal(String pathName, List<T> list) {
		 if(list == null || list.size()== 0|| pathName == null|| pathName.length() == 0 ){
			 return ;
		 }
		 File file = new File(pathName);
		 if(!file.exists()){
			 try {
				file.createNewFile();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 ObjectOutputStream osm =null;
		 try {
			 osm = AppendObjectOutputStream.newInstance(file);
			for(int i = 0;i < list.size();i++){
				osm.writeObject(list.get(i));
				System.out.println(list.get(i));
			}
			osm.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(osm != null){
				try {
					osm.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> readFromLocal(String pathName) {
		 File file = new File(pathName);
			ObjectInputStream oism =  null;
			List<T> list =new ArrayList<>();
		try {
			 oism = new ObjectInputStream(new FileInputStream(file));
			Object obj = null;
			try {
				while((obj = oism.readObject())!=null){
					list.add((T)obj);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(oism != null){
				try {
					oism.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
