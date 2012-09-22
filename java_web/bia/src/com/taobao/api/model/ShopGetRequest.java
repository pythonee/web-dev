package com.taobao.api.model;

/**
 * Call the taobao.shop.get to get a seller's shop
 * 
 * @author biyi
 */
public class ShopGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = 2583726692505091114L;

	private String fields;
	private String nick;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public ShopGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public ShopGetRequest withNick(String nick) {
		setNick(nick);
		return this;
	}

}
