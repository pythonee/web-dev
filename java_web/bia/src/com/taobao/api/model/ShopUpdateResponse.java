/**
 * 
 */
package com.taobao.api.model;

/**
 * The response result of calling the taobao.shop.update,
 * which is the shop objec with update acknowledgement
 * 
 * @author biyi
 * 
 */
public class ShopUpdateResponse extends TaobaoResponse {
	private static final long serialVersionUID = -7812028613127609899L;
	
	private Shop shop;

	public ShopUpdateResponse() {
		super();
	}

	public ShopUpdateResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
