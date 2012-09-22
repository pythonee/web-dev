package com.taobao.api.model;

/**
 *
 * @author moling
 * @date 2008-11-20
 */
public class ShopShowcaseRemainCountResponse extends TaobaoResponse {
	private static final long serialVersionUID = -2502163494893857484L;
	
	private Integer remainShowcase;
	
	public ShopShowcaseRemainCountResponse() {
		super();
	}

	public ShopShowcaseRemainCountResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Integer getRemainShowcase() {
		return remainShowcase;
	}

	public void setRemainShowcase(Integer remainShowcase) {
		this.remainShowcase = remainShowcase;
	}

}
