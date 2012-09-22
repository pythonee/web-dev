package com.taobao.api.model;

/**
 * 更改订单的销售属性请求。
 * 
 * @author fengsheng
 * @since 1.0, Oct 27, 2009
 */
public class TradeOrderSkuUpdateRequest extends TaobaoRequest {

	private static final long serialVersionUID = -4415306917621001208L;

	private Long oid;
	private Long skuId;
	private String skuProps;

	public Long getOid() {
		return this.oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Long getSkuId() {
		return this.skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public String getSkuProps() {
		return this.skuProps;
	}
	public void setSkuProps(String skuProps) {
		this.skuProps = skuProps;
	}

}
