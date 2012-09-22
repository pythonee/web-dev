package com.taobao.api.model;

/**
 * 
 * @author moling
 * @since 1.0, 2009-9-23
 */
public class ItemRecommendAddRequest extends TaobaoRequest {

	private static final long serialVersionUID = 6795434397357012321L;

	private String iid;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public ItemRecommendAddRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
}
