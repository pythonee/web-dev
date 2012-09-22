/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * The response result of the calling of the taobao.item.update.listing,
 * which are iid of the item and the modified time. 
 * 
 * @author biyi
 * 
 */
public class ItemUpdateListingResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8221231582723657091L;
	private Item item;
	
	public ItemUpdateListingResponse() {
		super();
	}

	public ItemUpdateListingResponse(TaobaoResponse rsp) {
		super(rsp);
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public String getIid() {
		if (null == item) {
			return null;
		}
		return item.getIid();
	}

	public void setIid(String iid) {
		if (null == item) {
			item = new Item();
		}
		this.item.setIid(iid);
	}

	public Date getModified() {
		if (null == item) {
			return null;
		}
		return item.getModified();
	}

	public void setModified(Date modified) {
		if (null == item) {
			item = new Item();
		}
		this.item.setModified(modified);
	}
	
}
