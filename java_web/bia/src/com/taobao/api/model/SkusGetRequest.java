package com.taobao.api.model;

/**
 * 批量获取SKU信息
 * @author gaoweibin.tw
 *
 */
public class SkusGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -7802323902436738313L;
	private String iids;
	private String fields;
	private String nick;
	
	public String getIids() {
		return iids;
	}
	public void setIids(String iids) {
		this.iids = iids;
	}
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
	
}
