/**
 * 
 */
package com.taobao.api.model;

import com.taobao.api.model.Trade;
import com.taobao.api.model.TaobaoResponse;

public abstract class TradeMemoResponse extends TaobaoResponse {
	//
	private static final long serialVersionUID = 2721996233616075040L;

	private Trade trade;

	public TradeMemoResponse() {
		super();
	}

	public TradeMemoResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Trade getTrade() {
		return trade;
	}
}
