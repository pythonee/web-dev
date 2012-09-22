package com.taobao.api.model;

/**
 * 获取卖家的运费模板时 需要传入的参数
 * @author 
 *
 */
public class PostageGetRequest extends TaobaoRequest{

	//
	private static final long serialVersionUID = -6079457274578200211L;
	private String fields;
	private String nick;
    private String postageId;
	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	
	public String getPostageId() {
		return postageId;
	}

	public void setPostageId(String postageId) {
		this.postageId = postageId;
	}

	public PostageGetRequest withFields(String fields){
		setFields(fields);
		return this;
	}
	public PostageGetRequest withPostageId(String postageId){
		setPostageId(postageId);
		return this;
	}
	public PostageGetRequest withNick(String nick){
		setNick(nick);
		return this;
	}
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
}
