/**
 * 
 */
package com.taobao.api.model;

/**
 * taobao.trade.close
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradeCloseRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = -7412447939854784929L;

	private String tid;
	private String closeReason;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public TradeCloseRequest withTid(String tid) {
		setTid(tid);
		return this;
	}

	public TradeCloseRequest withCloseReason(String closeReason) {
		setCloseReason(closeReason);
		return this;
	}

}
