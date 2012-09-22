package com.taobao.api.model;

/**
 * 修改SKU信息 返回的Response
 * @author gaoweibin.tw
 *
 */
public class SkuUpdateResponse extends TaobaoResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1759449505727589097L;
	public SkuUpdateResponse(){
		super();
	}
	public SkuUpdateResponse(TaobaoResponse response){
		super(response);
	}
	
	private Sku sku;
	
	public Sku getSku() {
		return sku;
	}
	public void setSku(Sku sku) {
		this.sku = sku;
	}
	
}
