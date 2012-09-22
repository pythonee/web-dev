package com.taobao.api.model;

/**
 * 调用taobao.item.sku.add 返回的Response
 * @author gaoweibin.tw
 *
 */
public class SkuAddResponse extends TaobaoResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8046394146899867573L;
	private Sku sku;
	public SkuAddResponse(){
		super();
	}
	public SkuAddResponse(TaobaoResponse response){
		super(response);
	}
	public Sku getSku() {
		return sku;
	}
	public void setSku(Sku sku) {
		this.sku = sku;
	}
	
}
