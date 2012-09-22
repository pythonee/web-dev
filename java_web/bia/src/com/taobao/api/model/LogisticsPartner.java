package com.taobao.api.model;


public class LogisticsPartner extends TaobaoModel{
	
	/**
	 * 物流公司信息
	 */
	private static final long serialVersionUID = 3484825484391103055L;
	private String companyId;
	private String companyCode;
	private String companyName;
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}