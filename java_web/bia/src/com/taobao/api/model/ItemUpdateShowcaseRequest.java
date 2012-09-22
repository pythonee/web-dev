package com.taobao.api.model;

/**
 * taobao.item.update.showcase
 */
@Deprecated
public class ItemUpdateShowcaseRequest extends ItemRecommendAddRequest {

	private static final long serialVersionUID = 5601343864165424980L;

	public ItemUpdateShowcaseRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
}
