/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

import com.taobao.api.convert.reader.ApiName;

/**
 * The response result of calling the taobao.traderate.add,
 * which are the tradeId and the creating time of the traderate.
 * 
 * @author biyi
 * 
 */
public class TradeRateAddResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8820452538583142199L;
	
	@ApiName("trade_rate")
	private TradeRate rate;
	
	public TradeRate getRate() {
		return rate;
	}

	public void setRate(TradeRate rate) {
		this.rate = rate;
	}

	public TradeRateAddResponse() {
		super();
	}

	public TradeRateAddResponse(TaobaoResponse rsp) {
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
	
	@Deprecated
	public String getOrderId() {
		return rate.getOrderId();
	}
	@Deprecated
	public void setOrderId(String orderId) {
		if(rate == null){
			rate = new TradeRate();
		}
		this.rate.setOrderId(orderId);
	}
	
	public String getOid() {
		return rate.getOid();
	}
	public void setOid(String oid) {
		if(rate == null){
			rate = new TradeRate();
		}
		this.rate.setOid(oid);
	}
	
}
