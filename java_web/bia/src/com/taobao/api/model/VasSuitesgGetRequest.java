package com.taobao.api.model;

public class VasSuitesgGetRequest extends TaobaoRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8462185238939846951L;
	
	/**
	 * 服务编码
	 */
	private String serviceCode;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
}
