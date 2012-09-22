package com.taobao.api.model;

/**
 * 类目的Feature属性
 * 
 * @author jeck218@gmail.com 2009-9-3
 */
public class Feature extends TaobaoModel {
	//
	private static final long serialVersionUID = 150689413611627084L;
	
	private String attrKey;
	private String attrValue;

	public String getAttrKey() {
		return attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

}
