package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class ItemPropsVerticalGetRequest extends TaobaoRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6664135208537005519L;
	
	private String fields;
	private String cid;
	private String type;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
}
