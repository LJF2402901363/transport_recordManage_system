package com.qst.dms.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.service.LogRecService;
import com.qst.dms.service.TransportService;

/**
 * @author 陌意随影
 TODO :
 *2019年12月19日  下午7:08:03
 */
public class ServerThread  extends Thread{
  
   private ServerSocket serverSocket = null;
   private ObjectInputStream oism = null;
   private boolean isRunning = true;
   private LogRecService logRecService = null;
   private TransportService transportService = null;
   private int port ;
	@SuppressWarnings("javadoc")
	public ServerThread(int port){
		try {
			this.port = port;
			//启动服务器等待客户端连接
			this.serverSocket = new ServerSocket(this.port);
			System.out.println("服务器已经启动，等待客户端连接。。。。。。");
		   this.logRecService = new LogRecService();
			this.transportService= new TransportService();
		} catch (IOException e) {
			if(this.serverSocket!= null){
					try {
						this.serverSocket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			e.printStackTrace();
		}
	}
 @Override
	public void run() {
	  while(isRunning){
		  try {
				System.out.println("等待客户端连接。。。。。。");
				//线程阻塞，等待客户端连接
			   Socket cilent = this.serverSocket.accept();
			System.out.println("连接客户端成功！");
			//获取客户端发送的数据
			this.oism = new ObjectInputStream(cilent.getInputStream());
			Object obj = null;
			try {
				while((obj =this.oism.readObject())!=null){
					if(obj instanceof MatchedLogRec){
						//保存数据到数据库
						this.logRecService.saveToDataBase((MatchedLogRec)obj);
						System.out.println((MatchedLogRec)obj);
					}else if(obj instanceof MatchedTransport){
						//保存数据到数据库
						this.transportService.saveToDataBase((MatchedTransport)obj);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			if(this.oism!= null){
				try {
					this.oism.close();
				} catch (IOException e1) {
					e.printStackTrace();
				}
			}
			if(this.serverSocket!= null){
				
						try {
							this.serverSocket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				} 
			e.printStackTrace();
			}
		}
	}
}
 
