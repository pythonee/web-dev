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
public class TradeRateListAddRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -1570157378804027612L;
	private String tid;
	private String role;
	private String anony;
	private String content;
	private String result;
	

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
	
	public TradeRateListAddRequest withTid(String tid) {
		setTid(tid);
		return this;
	}
	
	public TradeRateListAddRequest withRole(String role) {
		setRole(role);
		return this;
	}
	
	public TradeRateListAddRequest withAnony(String anony) {
		setAnony(anony);
		return this;
	}
	
	public TradeRateListAddRequest withContent(String content) {
		setContent(content);
		return this;
	}
	
	public TradeRateListAddRequest withResult(String result) {
		setResult(result);
		return this;
	}

}
