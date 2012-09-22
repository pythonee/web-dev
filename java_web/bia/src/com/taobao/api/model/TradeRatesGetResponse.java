/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

/**
 * The response result of calling the taobao.traderates.get,
 * which are the totalresults number and the list of the result traderates.
 * 
 * @author biyi
 * 
 */
public class TradeRatesGetResponse extends TaobaoListResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2577768947345553146L;
	private List<TradeRate> tradeRates;

	public TradeRatesGetResponse() {
		super();
	}

	public TradeRatesGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}
	
	public List<TradeRate> getTradeRates() {
		return tradeRates;
	}

	public void setTradeRates(List<TradeRate> tradeRates) {
		this.tradeRates = tradeRates;
	}
	
}
