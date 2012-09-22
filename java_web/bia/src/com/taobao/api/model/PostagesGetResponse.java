package com.taobao.api.model;

import java.util.List;


/**
 * 调用taobao.postages.get获取卖家的运费模板 返回的是Response
 * @author gaoweibin.tw
 *
 */
public class PostagesGetResponse extends TaobaoListResponse{
	
	private static final long serialVersionUID = 5916980089714903742L;
	
	private List<Postage> postages;
	
	public PostagesGetResponse(){
		super();
	}
	
	public PostagesGetResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public List<Postage> getPostages() {
		return postages;
	}

	public void setPostages(List<Postage> postages) {
		this.postages = postages;
	}
	
}
