/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

/**
 * @author sulinchong.pt
 * 
 */
public class ShopCatsListGetResponse extends TaobaoListResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371732178490010280L;
	private List<ShopCat> shopCats;

	public ShopCatsListGetResponse() {
		super();
	}

	public ShopCatsListGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<ShopCat> getShopCats() {
		return shopCats;
	}

	public void setShopCats(List<ShopCat> shopCats) {
		this.shopCats = shopCats;
	}

}
