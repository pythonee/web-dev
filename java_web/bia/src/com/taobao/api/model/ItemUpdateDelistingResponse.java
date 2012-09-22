/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * The response result of the calling of the taobao.item.update.delisting,
 * which are iid of the item and the modified time. 
 * 
 * @author biyi
 * 
 */
public class ItemUpdateDelistingResponse extends TaobaoResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6050615084163105244L;
	private Item item;

	public ItemUpdateDelistingResponse() {
		super();
	}

	public ItemUpdateDelistingResponse(TaobaoResponse rsp) {
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
