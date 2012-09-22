/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * taobao.trades.bought.get
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradesBoughtGetRequest extends TradesRequest {

	private String sellerNick;

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	
	public TradesBoughtGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public TradesBoughtGetRequest withStartCreated(Date startCreated) {
		setStartCreated(startCreated);
		return this;
	}

	public TradesBoughtGetRequest withEndCreated(Date endCreated) {
		setEndCreated(endCreated);
		return this;
	}

	public TradesBoughtGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public TradesBoughtGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	public TradesBoughtGetRequest withTitle(String title) {
		setTitle(title);
		return this;
	}

	public TradesBoughtGetRequest withStatus(String status) {
		setStatus(status);
		return this;
	}
	
	public TradesBoughtGetRequest withSellerNick(String sellerNick){
		setSellerNick(sellerNick);
		return this;
	}

	public TradesBoughtGetRequest withType(String type) {
		setType(type);
		return this;
	}

}
