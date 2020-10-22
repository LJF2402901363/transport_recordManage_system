package com.qst.dms.entity;

import java.io.Serializable;

/**
 * @author 陌意随影
 TODO :匹配发送
 *2019年11月7日  下午6:54:53
 */
public class MatchedTransport implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Transport send;
	private Transport trans;
	private Transport receive;

	/**
	 * @return  返回发送者
	 */
	public Transport getSend() {
		return send;
	}

	/**
	 * @param send 设置发送的货物
	 */
	public void setSend(Transport send) {
		this.send = send;
	}

	/**
	 * @return  返回送的货
	 */
	public Transport getTrans() {
		return trans;
	}

	/**
	 * @param trans 设置送货
	 */
	public void setTrans(Transport trans) {
		this.trans = trans;
	}

	/**
	 * @return  获取接受的货物
	 */
	public Transport getReceive() {
		return receive;
	}

	/**
	 * @param receive 设置接受的货物
	 */
	public void setReceive(Transport receive) {
		this.receive = receive;
	}

	@SuppressWarnings("javadoc")
	public MatchedTransport() {

	}

	@SuppressWarnings("javadoc")
	public MatchedTransport(Transport send, Transport trans, Transport receive) {
		if (send.getTransportType() != Transport.SENDDING) {
			throw new RuntimeException("不是发货记录!");
		}
		if (trans.getTransportType() != Transport.TRANSPORTING) {
			throw new RuntimeException("不是送货记录!");
		}
		if (receive.getTransportType() != Transport.RECIEVED) {
			throw new RuntimeException("不是签收记录!");
		}
		
		this.send = send;
		this.trans = trans;
		this.receive = receive;
	}

	public String toString() {
		return send.toString() + "|" + trans.toString() + "|" + receive;
	}

}
