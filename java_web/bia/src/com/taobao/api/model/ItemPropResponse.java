/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 *
 */
public class ItemPropResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5844599973174507638L;
	private ItemProp itemProp;

	public ItemPropResponse() {
		super();
	}

	public ItemPropResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public ItemProp getItemProp() {
		return itemProp;
	}

	public void setItemProp(ItemProp itemProp) {
		this.itemProp = itemProp;
	}
	
}
