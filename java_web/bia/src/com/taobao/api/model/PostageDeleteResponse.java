package com.taobao.api.model;

public class PostageDeleteResponse extends TaobaoListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1206698833576299401L;
	
	private Postage postage;
	
	public PostageDeleteResponse() {
		super();
	}
	
	public PostageDeleteResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public Postage getPostage() {
		return postage;
	}

	public void setPostage(Postage postage) {
		this.postage = postage;
	}
	
}
