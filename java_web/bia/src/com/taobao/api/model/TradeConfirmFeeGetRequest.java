package com.taobao.api.model;

/**
 * 交易确认收货费用 -- 请求类
 * 
 * @author jeck.xie 2009-3-25
 */
public class TradeConfirmFeeGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = -6299812872992024837L;

	String tid;
	String isDetail;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}

}
