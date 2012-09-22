/**
 * 
 */
package com.taobao.api.model;

/**
 * Call the taobao.item.update.bulklisting to listing a bulk item
 * 
 * @author biyi
 * 
 */
public class ItemUpdateBulklistingRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -2141766061187182404L;
	private String iid;
	private Integer num;
	private Integer bulkBaseNum;
	
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
	public Integer getBulkBaseNum() {
		return bulkBaseNum;
	}
	public void setBulkBaseNum(Integer bulkBaseNum) {
		this.bulkBaseNum = bulkBaseNum;
	}
	
	public ItemUpdateBulklistingRequest withIid(String iid) {
		setIid(iid);
		return this;
	}
	
	public ItemUpdateBulklistingRequest withNum(Integer num) {
		setNum(num);
		return this;
	}
	
	public ItemUpdateBulklistingRequest withBulkBaseNum(Integer bulkBaseNumum) {
		setBulkBaseNum(bulkBaseNumum);
		return this;
	}
}
