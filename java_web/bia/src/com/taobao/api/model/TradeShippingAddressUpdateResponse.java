package com.taobao.api.model;

/**
 * 更改交易的收货地址响应。
 * 
 * @author fengsheng
 * @since 1.0, Oct 27, 2009
 */
public class TradeShippingAddressUpdateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1116837779394829212L;

	private Trade trade;

	public TradeShippingAddressUpdateResponse() {
		super();
	}

	public TradeShippingAddressUpdateResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Trade getTrade() {
		return this.trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

}
