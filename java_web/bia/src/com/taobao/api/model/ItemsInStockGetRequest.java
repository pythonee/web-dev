/**
 * 
 */
package com.taobao.api.model;

/**
 * taobao.items.inventory.get
 * 
 * @author jeck218@gmail.com 2009-9-24
 * @see ItemsInventoryGetRequest
 */
@Deprecated
public class ItemsInStockGetRequest extends ItemsInventoryGetRequest {
	//
	private static final long serialVersionUID = 1555219194579950737L;
	
	public ItemsInStockGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public ItemsInStockGetRequest withQ(String q) {
		setQ(q);
		return this;
	}

	public ItemsInStockGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public ItemsInStockGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}
	
	public ItemsInStockGetRequest withBanner(String banner) {
		setBanner(banner);
		return this;
	}
	
	public ItemsInStockGetRequest withOrderBY(String orderBy) {
		setOrderBy(orderBy);
		return this;
	}
	public ItemsInStockGetRequest withItemType(String itemType) {
		setItemType(itemType);
		return this;
	}
	
}
