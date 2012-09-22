package com.taobao.api.model;

import java.util.List;

import com.taobao.api.convert.reader.ApiName;
import com.taobao.api.model.Item;
import com.taobao.api.model.TaobaoResponse;

/**
 * taobao.items.custom.get
 * 
 * @author jeck218@gmail.com 2009-9-24
 */
public class ItemsCustomGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = -5834440625771648278L;
	@ApiName("items")
	private List<Item> item;

	public ItemsCustomGetResponse() {
		super();
	}

	public ItemsCustomGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}
	@Deprecated
	public List<Item> getItem() {
		return item;
	}
	public List<Item> getItems() {
		return item;
	}

	@Deprecated
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public void setItems(List<Item> item) {
		this.item = item;
	}

}
