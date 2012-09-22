package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class ItemImgDeleteRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = 6923800996639467039L;
	private String itemImgId;  //商品图片id
	private String iid;		   //商品id
	@Deprecated
	public String getItemImgId() {
		return itemImgId;
	}
	public String getId() {
		return itemImgId;
	}
	
	@Deprecated
	public void setItemImgId(String itemImgId) {
		this.itemImgId = itemImgId;
	}
	public void setId(String id) {
		this.itemImgId = id;
	}
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	
	
}
