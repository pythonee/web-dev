package com.taobao.api.model;

/**
 * 调用taobao.postage.add 添加邮费模板时 返回的Resonse
 * @author gaoweibin.tw
 *
 */
public class PostageAddResponse extends TaobaoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1089401933918384496L;
	
	private Postage postage;
	
	public PostageAddResponse(){
		super();
	}
	
	public PostageAddResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public Postage getPostage() {
		return postage;
	}

	public void setPostage(Postage postage) {
		this.postage = postage;
	}
	
}
