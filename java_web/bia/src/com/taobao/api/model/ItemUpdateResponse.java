/**
 * 
 */
package com.taobao.api.model;


/**
 * 调用taobao.item.update更新商品时 返回的response
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemUpdateResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7121222812176033427L;
	private Item item;

	public ItemUpdateResponse() {
		super();
	}

	public ItemUpdateResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
