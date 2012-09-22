package com.taobao.api.model;

/**
 * 获取SKU
 * 
 * @author gaoweibin.tw
 * 
 */
public class SkuGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -3308054836552367169L;
	private String fields;
	private String skuId;
	private String nick;

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

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

}
