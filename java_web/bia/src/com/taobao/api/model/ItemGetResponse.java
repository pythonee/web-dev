/**
 * 
 */
package com.taobao.api.model;

/**
 * 调用 taobao.item.get 得到单个商品时 返回的response
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemGetResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3041556864579952236L;
	private Item item;

	public ItemGetResponse() {
		super();
	}

	public ItemGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
}
