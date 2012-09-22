/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 *
 */
public class UsersGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -5307813265659949791L;
	private String nicks;
	private String fields;
	public String getNicks() {
		return nicks;
	}
	public void setNicks(String nicks) {
		this.nicks = nicks;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public UsersGetRequest withNicks(String nicks){
		setNicks(nicks);
		return this;
	}
	public UsersGetRequest withFields(String fileds){
		setFields(fileds);
		return this;
	}
}