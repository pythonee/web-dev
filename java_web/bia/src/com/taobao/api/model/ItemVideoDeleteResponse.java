package com.taobao.api.model;

import java.util.Date;

/**
 * 
 * @author moling
 * @since 1.0, 2009-8-4
 */
public class ItemVideoDeleteResponse extends TaobaoResponse {
	private static final long serialVersionUID = -777580283968473924L;

	private Video video;
	
	public ItemVideoDeleteResponse() {
		super();
	}

	public ItemVideoDeleteResponse(TaobaoResponse rsp) {
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
}
