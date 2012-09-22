/**
 * 
 */
package com.taobao.api.model;

/**
 * Call the taobao.traderate.add to add a traderate
 * 
 * @author biyi
 * 
 */
public class TradeRateAddRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 3462804452037876034L;
	private String tid;
	private String orderId;
	private String role;
	private String anony;
	private String content;
	private String result;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAnony() {
		return anony;
	}
	public void setAnony(String anony) {
		this.anony = anony;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public TradeRateAddRequest withTid(String tid) {
		setTid(tid);
		return this;
	}
	
	public TradeRateAddRequest withRole(String role) {
		setRole(role);
		return this;
	}
	
	public TradeRateAddRequest withAnony(String anony) {
		setAnony(anony);
		return this;
	}
	
	public TradeRateAddRequest withContent(String content) {
		setContent(content);
		return this;
	}
	
	public TradeRateAddRequest withResult(String result) {
		setResult(result);
		return this;
	}
	public TradeRateAddRequest withOrderId(String orderId) {
		setOrderId(orderId);
		return this;
	}
}
