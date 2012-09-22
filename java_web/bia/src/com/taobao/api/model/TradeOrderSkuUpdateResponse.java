package com.taobao.api.model;

/**
 * 更改订单的销售属性响应。
 * 
 * @author fengsheng
 * @since 1.0, Oct 27, 2009
 */
public class TradeOrderSkuUpdateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5364051436198183103L;

	private Order order;

	public TradeOrderSkuUpdateResponse() {
		super();
	}

	public TradeOrderSkuUpdateResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
