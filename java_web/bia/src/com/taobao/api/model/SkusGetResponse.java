package com.taobao.api.model;

import java.util.List;

/**
 * 批量获取SKU信息,返回的response
 * @author gaoweibin.tw
 *
 */
public class SkusGetResponse extends TaobaoResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7390473712260771731L;
	
	public SkusGetResponse(){
		super();
	}
	public SkusGetResponse(TaobaoResponse response){
		super(response);
	}
	
	private List<Sku> skus;

	public List<Sku> getSkus() {
		return skus;
	}
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}
}
