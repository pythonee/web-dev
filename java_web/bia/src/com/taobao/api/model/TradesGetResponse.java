/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

public class TradesGetResponse extends TaobaoListResponse {
	
	private static final long serialVersionUID = -3545399279298906737L;
	
	private List<Trade> trades;

	public TradesGetResponse() {
		super();
	}

	public TradesGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public List<Trade> getTrades() {
		return trades;
	}
}
