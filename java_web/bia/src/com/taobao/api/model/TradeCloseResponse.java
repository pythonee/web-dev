/**
 * 
 */
package com.taobao.api.model;

/**
 * @author wuying
 * 
 */
public class TradeCloseResponse extends TaobaoResponse {

	private static final long serialVersionUID = -5879907034260030588L;

	private Trade trade;

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public TradeCloseResponse() {
		super();
	}

	public TradeCloseResponse(TaobaoResponse rsp) {
		super(rsp);
	}

}
