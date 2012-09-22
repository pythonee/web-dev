package com.taobao.api.model;

/**
 * taobao.item.update.revokeShowcase
 */
@Deprecated
public class ItemUpdateRevokeShowcaseRequest extends ItemRecommendDeleteRequest {
	private static final long serialVersionUID = 5358848036115676246L;

	public ItemUpdateRevokeShowcaseRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
}
