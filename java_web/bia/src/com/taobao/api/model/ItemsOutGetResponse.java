package com.taobao.api.model;

/**
 * taobao.items.custom.get
 * 
 * @author jeck218@gmail.com 2009-9-24
 * @see ItemsCustomGetResponse
 */
@Deprecated
public class ItemsOutGetResponse extends ItemsCustomGetResponse {

	private static final long serialVersionUID = -758997500409541418L;

	public ItemsOutGetResponse() {
		super();
	}

	public ItemsOutGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public ItemsOutGetResponse(ItemsCustomGetResponse rsp) {
		super(rsp);
		super.setItems(rsp.getItem());
	}

}
