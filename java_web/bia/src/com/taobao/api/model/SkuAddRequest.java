package com.taobao.api.model;

/**
 * 调用taobao.item.sku.add  添加SKU
 * @author gaoweibin.tw
 *
 */
public class SkuAddRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -4073096111490823205L;
	private String iid;
	private String properties;
	private String quantity;
	private String price;
	private String outerId; 
	private String lang;
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOuterId() {
		return outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
}
