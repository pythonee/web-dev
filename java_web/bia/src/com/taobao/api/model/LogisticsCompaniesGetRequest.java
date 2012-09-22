package com.taobao.api.model;

public class LogisticsCompaniesGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = 8165107990737396404L;
	@Deprecated
	private String field;
	private String fields;
	private Boolean isRecommended;
	private String orderMode; // 下单方式
	@Deprecated
	public String getField() {
		return fields;
	}
	@Deprecated
	public void setField(String field) {
		this.fields = field;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public Boolean getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
	public String getOrderMode() {
		return orderMode;
	}
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
}
