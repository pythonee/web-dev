/**
 * 
 */
package com.taobao.api.model;

/**
 * 调用 taobao.item.get 得到单个商品时 需要传入的参数
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -4178601883176995077L;
	private String fields;
	private String nick;
	private String iid;
    private String numIid;
	public String getNumIid() {
		return numIid;
	}

	public void setNumIid(String numIid) {
		this.numIid = numIid;
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

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public ItemGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public ItemGetRequest withNick(String nick) {
		setNick(nick);
		return this;
	}

	public ItemGetRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
	public ItemGetRequest withNumIid(String numIid) {
		setNumIid(numIid);
		return this;
	}
}
