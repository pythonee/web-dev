package com.taobao.api.model;

/**
 * taobao.trade.get
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradeGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 6426445740041758776L;

	private Trade trade;

	public TradeGetResponse() {
		super();
	}

	public TradeGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Trade getTrade() {
		return trade;
	}
}
