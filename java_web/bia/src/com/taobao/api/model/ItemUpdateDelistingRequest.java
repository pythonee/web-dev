/**
 * 
 */
package com.taobao.api.model;

/**
 * Call the taobao.item.update.delisting to delisting an item
 * 
 * @author biyi
 * 
 */
public class ItemUpdateDelistingRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 8105098143934527813L;
	private String iid;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}
	
	public ItemUpdateDelistingRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
}
