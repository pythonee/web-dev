/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 * 
 */
public class SellerCatsListGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 532384633229880975L;
	private String nick;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	public SellerCatsListGetRequest withNick(String nick){
		setNick(nick);
		return this;
	}
}
