package com.taobao.api.model;

/**
 * 
 * @author moling
 * @since 1.0, 2009-8-4
 */
public class ItemVideoDeleteRequest extends TaobaoRequest {

	private static final long serialVersionUID = -6058984053300096450L;

	private String id;			//视频对应商品的id
	private String iid;			//商品id
	private String lang;		//商品的文字类型
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIid() {
		return this.iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getLang() {
		return this.lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
}
