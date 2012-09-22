package com.taobao.api.model;

import java.util.Date;

/**
 * 
 * @author moling
 * @since 1.0, 2009-9-23
 */
public class ItemRecommendAddResponse extends TaobaoResponse {

	private static final long serialVersionUID = -3748931883789059977L;

	private Item item;

	public ItemRecommendAddResponse() {
		super();
	}

	public ItemRecommendAddResponse(TaobaoResponse rsp) {
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
