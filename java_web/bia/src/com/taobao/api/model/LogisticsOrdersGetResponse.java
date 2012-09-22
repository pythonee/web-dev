package com.taobao.api.model;

import java.util.List;

import com.taobao.api.convert.reader.ApiName;

public class LogisticsOrdersGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -5256151873316397401L;

	private List<Shipping> shippings;

	public LogisticsOrdersGetResponse() {
		super();
	}

	public LogisticsOrdersGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setShippings(List<Shipping> shippings) {
		this.shippings = shippings;
	}

	public List<Shipping> getShippings() {
		return shippings;
	}
}
