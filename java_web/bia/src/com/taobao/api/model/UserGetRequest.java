/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 *
 */
public class UserGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -144926149258262676L;
	private String nick;
	private String fields;
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
	public UserGetRequest withNick(String nick){
		setNick(nick);
		return this;
	}
	public UserGetRequest withFields(String fileds){
		setFields(fileds);
		return this;
	}
}
