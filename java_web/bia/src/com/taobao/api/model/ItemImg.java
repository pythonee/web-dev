package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author gaoweibin.tw
 *
 */
public class ItemImg extends TaobaoModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7251642765531379576L;
	
	@ApiName("id")
	private String itemimgId;      //商品图片id
	private String url;				//图片链接地址
	private long position;			//图片放在第几张
	private String created;			//商品图片上传时间,图片上传和更新时会返回此字段
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
	@Deprecated
	public String getItemimgId() {
		return itemimgId;
	}
	public String getId() {
		return itemimgId;
	}
	@Deprecated
	public void setItemimgId(String itemimgId) {
		this.itemimgId = itemimgId;
	}
	public void setId(String itemimgId) {
		this.itemimgId = itemimgId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	
}
