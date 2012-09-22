/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * The response result of calling the taobao.traderate.add,
 * which are the tradeId and the creating time of the traderate.
 * 
 * @author biyi
 * 
 */
public class TradeRateListAddResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8820452538583142199L;
	
	private TradeRate rate;
	
	public TradeRate getRate() {
		return rate;
	}

	public void setRate(TradeRate rate) {
		this.rate = rate;
	}
	
	public TradeRateListAddResponse() {
		super();
	}

	public TradeRateListAddResponse(TaobaoResponse rsp) {
		super(rsp);
	}
	
	public String getTid() {
		return rate.getTid();
	}
	public void setTid(String tid) {
		if(rate == null){
			rate = new TradeRate();
		}
		this.rate.setTid(tid);
	}
	public Date getCreated() {
		return rate.getCreated();
	}
	public void setCreated(Date created) {
		if(rate == null){
			rate = new TradeRate();
		}
		this.rate.setCreated(created);
	}	
	
}
