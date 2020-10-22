package com.qst.dms.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;

/**
 * @author 陌意随影
 TODO :匹配日志客户端 
 *2019年12月19日  下午6:38:41
 */
public class ClientThread  extends Thread{
	  //客户端套节字
      private Socket client = null;
      //对象输出流
      private ObjectOutputStream osm = null;
      //端口
      private int port ;
      //IP
      private String ip ;
      @SuppressWarnings("javadoc")
	public ClientThread(String ip ,int port){
    	  try {
    		  this.port = port;
    		  this.ip =ip;
			this.client = new Socket(this.ip,this.port);
			this.osm = new ObjectOutputStream(this.client.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
      }

    /**
     * 将指定的日志集合全都发送到服务器
    * @param logRecList
    */
   public void sendMatchedLogRec(List<MatchedLogRec> logRecList){
   	 for(MatchedLogRec matchedlogRec:logRecList){
   		 try {
   			  //将每个matchedlogRec发送到服务器
				this.osm.writeObject(matchedlogRec);
				//刷新立马发送
				this.osm.flush();
			} catch (IOException e) {
				try {
					if(this.osm!=null){
						this.osm.close();
					}
					if(this.client!=null){
						this.client.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
   	 }
    }
   /**
    * 将指定的日志集合全都发送到服务器
    * @param logRecList
    */
   public void sendMatchedLogRec(MatchedLogRec... logRecList){
   	if(logRecList == null){
   		return;
   	}
  	 for(MatchedLogRec matchedlogRec:logRecList){
  		 try {
  			//将每个matchedlogRec发送到服务器
				this.osm.writeObject(matchedlogRec);
				//刷新立马发送
				this.osm.flush();
			} catch (IOException e) {
				try {
					if(this.osm!=null){
						this.osm.close();
					}
					if(this.client!=null){
						this.client.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
  	 }
   }
   /**
    * 将指定的物流集合全都发送到服务器
   * @param transList
   */
  public void sendMatchedTransport(List<MatchedTransport> transList){
	 	 for(MatchedTransport matchedTransport:transList){
	 		 try {
	 			//将每个matchedTransport发送到服务器
					this.osm.writeObject(matchedTransport);
					//刷新立马发送
					this.osm.flush();
				} catch (IOException e) {
					try {
						if(this.osm!=null){
							this.osm.close();
						}
						if(this.client!=null){
							this.client.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	 	 }
   }
  /**
   * 将指定的物流集合全都发送到服务器
   * @param transList
   */
  public void sendMatchedTransport(MatchedTransport... transList){
  	if(transList== null){
  		return;
  	}
 	 for(MatchedTransport matchedTransport:transList){
 		 try {
 			//将每个matchedTransport发送到服务器
				this.osm.writeObject(matchedTransport);
				//刷新立马发送
				this.osm.flush();
			} catch (IOException e) {
				try {
					if(this.osm!=null){
						this.osm.close();
					}
					if(this.client!=null){
						this.client.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
 	 }
  }
      @Override
    public void run() {
    	  
    }
}
