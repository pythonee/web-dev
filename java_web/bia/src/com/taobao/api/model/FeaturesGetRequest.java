package com.taobao.api.model;

/**
 * 查询类目的Feature属性
 * 
 * @author jeck218@gmail.com 2009-9-5
 */
public class FeaturesGetRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = 4922485450800472005L;

	private String cid;
	private String attrKeys;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAttrKeys() {
		return attrKeys;
	}

	public void setAttrKeys(String attrKeys) {
		this.attrKeys = attrKeys;
	}

}
