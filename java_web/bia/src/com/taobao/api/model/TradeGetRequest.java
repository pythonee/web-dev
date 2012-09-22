package com.taobao.api.model;

/**
 * taobao.trade.get
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradeGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = -7013523243242894493L;

	private String fields;
	private String tid;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public TradeGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public TradeGetRequest withTid(String tid) {
		setTid(tid);
		return this;
	}

}
