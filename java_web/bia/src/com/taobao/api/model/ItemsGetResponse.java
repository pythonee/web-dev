/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

/**
 * 调用taobao.items.get搜索商品是 返回的Response
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemsGetResponse extends TaobaoListResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7861764260397045802L;
	private List<Item> items;
	public ItemsGetResponse() {
		super();
	}

	public ItemsGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}
}
