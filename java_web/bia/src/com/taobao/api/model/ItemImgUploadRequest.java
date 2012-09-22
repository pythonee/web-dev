package com.taobao.api.model;

import java.io.File;

/**
 * 添加商品图片
 * @author gaoweibin.tw
 * 
 */
public class ItemImgUploadRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 58140399063697026L;
	private String itemImgId;   //商品图片id 
	private String iid;			//商品id
	private String position;	//商品图片位置
	private File image;			//商品图片内容
	private boolean isMajor;	//是否将该图片设为主图
	
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public boolean isMajor() {
		return isMajor;
	}
	public void setMajor(boolean isMajor) {
		this.isMajor = isMajor;
	}
	
}
