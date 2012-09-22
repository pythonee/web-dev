package com.taobao.api.model;

import java.util.List;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author gaoweibin.tw
 *
 */
public class ShippingAddressesGetResponse extends TaobaoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2661882796563581980L;
	
	public ShippingAddressesGetResponse(){
		super();
	}
	
	public ShippingAddressesGetResponse(TaobaoResponse rsp){
		super(rsp);
	}
	
	@ApiName("shipping_addresss")
	List<ShippingAddress> shippingAddressList;

	public List<ShippingAddress> getShippingAddressList() {
		return shippingAddressList;
	}

	public void setShippingAddressList(List<ShippingAddress> shippingAddressList) {
		this.shippingAddressList = shippingAddressList;
	}
	
}
