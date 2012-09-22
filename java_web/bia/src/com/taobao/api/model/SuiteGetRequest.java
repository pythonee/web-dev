package com.taobao.api.model;

/**
 * @author Taylor xuwei840916@hotmail.com
 * @version 2009-2-18 下午07:24:45
 */
public class SuiteGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 7398797135582602796L;
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
