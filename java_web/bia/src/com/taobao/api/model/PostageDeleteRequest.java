package com.taobao.api.model;

public class PostageDeleteRequest extends TaobaoRequest {
	
	private static final long serialVersionUID = -6709216574715465721L;
	private String postageId;

	public String getPostageId() {
		return postageId;
	}

	public void setPostageId(String postageId) {
		this.postageId = postageId;
	}
	
}
