/**
 * 
 */
package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;


/**
 * 调用taobao.items.get搜索商品是 返回的Response
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemsSearchResponse extends TaobaoListResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7861764260397045802L;
	@ApiName("item_search")
	private ItemSearch  search;
	public ItemsSearchResponse() {
		super();
	}

	public ItemsSearchResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	@Deprecated
	public ItemSearch getSearch() {
		return search;
	}
	public ItemSearch getItemSearch() {
		return search;
	}

	@Deprecated
	public void setSearch(ItemSearch search) {
		this.search = search;
	}
	public void setItemSearch(ItemSearch search) {
		this.search = search;
	}
	
}
