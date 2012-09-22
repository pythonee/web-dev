/**
 * 
 */
package com.taobao.api.model;


/**
 * 调用 taobao.item.add 添加商品时,返回的Response
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemAddResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 804632915389469270L;
	private Item item;
	public ItemAddResponse() {
		super();
	}

	public ItemAddResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}
