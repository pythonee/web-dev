package com.taobao.api.model;

/**
 * taobao.item.update.revokeShowcase
 * 
 * @author fengsheng
 * @since 1.0, Sep 24, 2009
 */
@Deprecated
public class ItemUpdateRevokeShowcaseResponse extends ItemRecommendDeleteResponse {

	private static final long serialVersionUID = -7710973272104110101L;

	public ItemUpdateRevokeShowcaseResponse() {
		super();
	}

	public ItemUpdateRevokeShowcaseResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public ItemUpdateRevokeShowcaseResponse(ItemRecommendDeleteResponse rsp) {
		super(rsp);
		super.setIid(rsp.getIid());
		super.setModified(rsp.getModified());
	}

}
