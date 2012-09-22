/**
 * 
 */
package com.taobao.api.model;

/**
 * Call the taobao.item.update.listing to listing an item
 * 
 * @author biyi
 * 
 */
public class ItemUpdateListingRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -673053994588756375L;
	private String iid;
	private Integer num;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public ItemUpdateListingRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
	
	public ItemUpdateListingRequest withNum(Integer num) {
		setNum(num);
		return this;
	}
	
}
