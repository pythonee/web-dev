package com.taobao.api.model;

import java.util.List;

/**
 * 卖家授权信息Model
 * 
 * @author jeck.xie 2009-3-25
 */
public class SellerAuthorize extends TaobaoModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5160104265933137495L;

	List<Brand> brands; // 品牌列表
	List<ItemCat> itemCats; // 类目列表

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<ItemCat> getItemCats() {
		return itemCats;
	}

	public void setItemCats(List<ItemCat> itemCats) {
		this.itemCats = itemCats;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
