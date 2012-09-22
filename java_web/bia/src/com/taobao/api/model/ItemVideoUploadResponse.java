package com.taobao.api.model;

import java.util.Date;

/**
 * 
 * @author moling
 * @since 1.0, 2009-8-4
 */
public class ItemVideoUploadResponse extends TaobaoResponse {
	private static final long serialVersionUID = -2840358944303891912L;

	private Video video;
	
	public ItemVideoUploadResponse() {
		super();
	}

	public ItemVideoUploadResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Video getVideo() {
		return video;
	}
	
	public void setVideo(Video video) {
		this.video = video;
	}
	
	public String getIid() {
		if (null == video) {
			return null;
		}
		return video.getIid();
	}

	public void setIid(String iid) {
		if (null == video) {
			video = new Video();
		}
		this.video.setIid(iid);
	}

	public Date getModified() {
		if (null == video) {
			return null;
		}
		return video.getModified();
	}

	public void setModified(Date modified) {
		if (null == video) {
			video = new Video();
		}
		this.video.setModified(modified);
	}
	
	public String getId() {
		if (null == video) {
			return null;
		}
		return video.getId();
	}

	public void setId(String id) {
		if (null == video) {
			video = new Video();
		}
		this.video.setId(id);
	}
}
