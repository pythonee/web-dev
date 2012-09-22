package com.taobao.api.model;

import com.taobao.api.model.TaobaoRequest;

public class UserDetailGetRequest extends TaobaoRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8066390851359109035L;
	
	private String fields;
	private String nick;
	private String alipayNo;
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getAlipayNo() {
		return alipayNo;
	}
	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}
	
}
