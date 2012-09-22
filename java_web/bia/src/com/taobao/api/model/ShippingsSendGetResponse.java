package com.taobao.api.model;

/**
 * taobao.shippings.send.get
 */
@Deprecated
public class ShippingsSendGetResponse extends LogisticsOrdersGetResponse {

	private static final long serialVersionUID = 158467632748543099L;

	public ShippingsSendGetResponse() {
		super();
	}

	public ShippingsSendGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public ShippingsSendGetResponse(LogisticsOrdersGetResponse rsp) {
		super(rsp);
		super.setShippings(rsp.getShippings());
		super.setTotalResults(rsp.getTotalResults());
	}

}
