/**
 * 
 */
package com.taobao.api.model;

/**
 * Call the taobao.traderates.get to get the sellers' list of traderates
 * 
 * @author biyi
 * 
 */
public class TradeRatesGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -6291948599953278669L;
	private String fields;
	private String role;
	private String rateType;
	private String result;
	private Integer pageNo;
	private Integer pageSize;
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public TradeRatesGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	
	public TradeRatesGetRequest withRole(String role) {
		setRole(role);
		return this;
	}
	
	public TradeRatesGetRequest withRateType(String rateType) {
		setRateType(rateType);
		return this;
	}

	
	public TradeRatesGetRequest withResult(String result) {
		setResult(result);
		return this;
	}
	
	public TradeRatesGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}
	
	public TradeRatesGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}
}
