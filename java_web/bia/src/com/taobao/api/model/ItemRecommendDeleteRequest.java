package com.taobao.api.model;

/**
 * 
 * @author moling
 * @since 1.0, 2009-9-23
 */
public class ItemRecommendDeleteRequest extends TaobaoRequest {

	private static final long serialVersionUID = 5600963862320087934L;

	private String iid;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public ItemRecommendDeleteRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
}
