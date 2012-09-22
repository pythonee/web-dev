package com.taobao.api.model;

/**
 * taobao.items.custom.get
 * 
 * @author jeck218@gmail.com 2009-9-24
 * @see ItemsCustomGetResponse
 */
@Deprecated
public class FullitemsGetResponse extends ItemsCustomGetResponse {

	//
	private static final long serialVersionUID = 8864896676133098081L;

	public FullitemsGetResponse() {
		super();
	}

	public FullitemsGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}
	
	public FullitemsGetResponse(ItemsCustomGetResponse rsp) {
		super(rsp);
		super.setItems(rsp.getItem());
	}
}
