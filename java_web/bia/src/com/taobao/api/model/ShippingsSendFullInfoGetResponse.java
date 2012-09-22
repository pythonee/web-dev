package com.taobao.api.model;

@Deprecated
public class ShippingsSendFullInfoGetResponse extends LogisticsOrdersDetailGetResponse {

	private static final long serialVersionUID = 9040264438572148543L;

	public ShippingsSendFullInfoGetResponse() {
		super();
	}

	public ShippingsSendFullInfoGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public ShippingsSendFullInfoGetResponse(LogisticsOrdersDetailGetResponse rsp) {
		super(rsp);
		super.setShippings(rsp.getShippings());
		super.setTotalResults(rsp.getTotalResults());
	}

}
