/**
 * 
 */
package com.taobao.api.model;

/**
 * The response result of the calling of the taobao.shop.get,
 * which is a object of the shop. 
 * 
 * @author biyi
 * 
 */
public class ShopGetResponse extends TaobaoResponse {
	private static final long serialVersionUID = -3427303264645186849L;
	
	private Shop shop;
	
	public ShopGetResponse() {
		super();
	}

	public ShopGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Shop getShop() {
		return shop;
	}
}
