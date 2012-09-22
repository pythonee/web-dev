package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class SkuGetResponse extends TaobaoResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1604894259035212259L;
	public SkuGetResponse(){
		super();
	}
	public SkuGetResponse(TaobaoResponse response){
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
