package com.taobao.api.model;

import java.util.Date;

/**
 * 
 * @author moling
 * @since 1.0, 2009-8-4
 */
public class Video extends TaobaoModel {
	private static final long serialVersionUID = 870177203685713401L;
	private String id;
	private String iid;
	private String videoId;
	private String url;
	private Date modified;
	private Date created;
	
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
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getModified() {
		return this.modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getCreated() {
		return this.created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getIid() {
		return this.iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
}
