/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;


/**
 * taobao.trades.sold.get
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradesSoldGetRequest extends TradesRequest {
	
	private String buyerNick;

	/**
	 * 买家未评：RATE_UNBUYER 
	 * 卖家未评：RATE_UNSELLER 
	 * 买家已评，卖家未评：RATE_BUYER_UNSELLER
	 * 买家未评，卖家已评：RATE_UNBUYER_SELLER
	 */
	private String rateStatus;

	public String getRateStatus() {
		return rateStatus;
	}

	public void setRateStatus(String rateStatus) {
		this.rateStatus = rateStatus;
	}

	/**
	 * @return the buyerNick
	 */
	public String getBuyerNick() {
		return buyerNick;
	}

	/**
	 * @param buyerNick
	 *            the buyerNick to set
	 */
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	
	public TradesSoldGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public TradesSoldGetRequest withStartCreated(Date startCreated) {
		setStartCreated(startCreated);
		return this;
	}

	public TradesSoldGetRequest withEndCreated(Date endCreated) {
		setStartCreated(endCreated);
		return this;
	}

	public TradesSoldGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public TradesSoldGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	public TradesSoldGetRequest withTitle(String title) {
		setTitle(title);
		return this;
	}

	public TradesSoldGetRequest withStatus(String status) {
		setStatus(status);
		return this;
	}
	
	public TradesSoldGetRequest withBuyerNick(String sellerNick){
		setBuyerNick(sellerNick);
		return this;
	}
	
	public TradesSoldGetRequest withType(String type) {
		setType(type);
		return this;
	}

}
