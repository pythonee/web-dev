package com.taobao.api.model;

/**
 * 
 * @author moling
 * @since 1.0, 2009-8-4
 */
public class ItemVideoUploadRequest extends TaobaoRequest {
	private static final long serialVersionUID = 5275960926684603474L;

	private String id;			//视频对应商品的id
	private String videoId;  	//视频数字id
	private String iid;			//商品id
	private String lang;		//商品的文字类型
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVideoId() {
		return this.videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getIid() {
		return this.iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getLang() {
		return this.lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
}
