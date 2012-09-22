package com.taobao.api.model;

import java.io.File;

/**
 * 添加属性图片
 * @author gaoweibin.tw
 *
 */
public class PropimgUploadRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 2406677673911786715L;
	private String propImgId;     //属性图片id
	private String iid;			  //商品id
	private String properties;    //属性图片属串
	private String position;	  //属性图片位置
	private File image;
	@Deprecated
	public String getPropImgId() {
		return propImgId;
	}
	public String getId() {
		return propImgId;
	}
	
	@Deprecated
	public void setPropImgId(String propImgId) {
		this.propImgId = propImgId;
	}
	public void setId(String id) {
		this.propImgId = id;
	}
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
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
	
	
}
