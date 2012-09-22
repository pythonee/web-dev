package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * 交易确认收货费用 -- 返回类
 * 
 * @author jeck.xie 2009-3-25
 */
public class TradeConfirmFeeGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 2213068360412873543L;

	@ApiName("trade_confirm_fee")
	private TradeConfirmFee confirmFee;

	public TradeConfirmFee getConfirmFee() {
		return confirmFee;
	}

	public void setConfirmFee(TradeConfirmFee confirmFee) {
		this.confirmFee = confirmFee;
	}

	public TradeConfirmFeeGetResponse() {
		super();
	}

	public TradeConfirmFeeGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}
}
