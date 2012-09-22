package com.taobao.api.model;

import java.util.List;

public class LogisticsOrdersDetailGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -6819984128302029725L;

	private List<Shipping> shippings;

	public LogisticsOrdersDetailGetResponse() {
		super();
	}

	public LogisticsOrdersDetailGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setShippings(List<Shipping> shippings) {
		this.shippings = shippings;
	}

	public List<Shipping> getShippings() {
		return shippings;
	}
}
