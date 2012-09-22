package com.taobao.api.model;


/**
 * 调用taobao.postage.update更新商品时 返回的Response
 * @author gaoweibin.tw
 *
 */
public class PostageUpdateResponse extends TaobaoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5211601136188756071L;
	private Postage postage;
	
	public PostageUpdateResponse(){
		super();
	}
	
	public PostageUpdateResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public Postage getPostage() {
		return postage;
	}

	public void setPostage(Postage postage) {
		this.postage = postage;
	}
	
}
