package com.taobao.api.model;

import java.util.List;

/**
 * 商品类目响应封装。
 * 
 * @author fengsheng
 * @since 1.0, Sep 24, 2009
 */
public class ItemCatsResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -7501267141400664030L;

	private List<ItemCat> itemCats;

	public ItemCatsResponse() {
		super();
	}
	public ItemCatsResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setItemCats(List<ItemCat> itemCats) {
		this.itemCats = itemCats;
	}
	public List<ItemCat> getItemCats() {
		return itemCats;
	}
}
