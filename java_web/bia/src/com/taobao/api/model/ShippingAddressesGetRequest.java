package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class ShippingAddressesGetRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = 3145276598218847824L;
	private String fields;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	
	
}
