package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author gaoweibin.tw
 *
 */
public class PropImg extends TaobaoModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9180857371850463266L;
	
	@ApiName("id")
	private String propimgId;   //属性图片的id
	private String url;			//图片链接地址
	private String properties;  //图片所对应的属性组合的字符串
	private long position;      //图片放在第几张
	private String created;     //商品属性图片上传时间,图片上传和更新时会返回此字段
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
	@Deprecated
	public String getPropimgId() {
		return propimgId;
	}
	public String getId() {
		return propimgId;
	}
	@Deprecated
	public void setPropimgId(String propimgId) {
		this.propimgId = propimgId;
	}
	public void setId(String propimgId) {
		this.propimgId = propimgId;
	}
	public String getCreated() {
		return created; 
	}
	public void setCreated(String created) {
		this.created = created;
	}
}
