package com.taobao.api.model;



/**
 * 调用taobao.postages.get获取卖家的运费模板 返回的是Response
 * @author 
 *
 */
public class PostageGetResponse extends TaobaoListResponse{
	
	private static final long serialVersionUID = 5916980089714903742L;
	
	private Postage postage;
	
	public PostageGetResponse(){
		super();
	}
	
	public PostageGetResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public Postage getPostage() {
		return postage;
	}

	public void setPostage(Postage postage) {
		this.postage = postage;
	}

	
	
}
