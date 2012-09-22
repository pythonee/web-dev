/**
 * 
 */
package com.taobao.api.model;

/**
 * 获取产品增值服务响应封装类。
 * 
 * @author fengsheng
 * @since 1.0, Jul 14, 2009
 */
public class VasProductGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5027398791682497881L;

	private VasProduct vasProduct;

	public VasProductGetResponse() {
		super();
	}

	public VasProductGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public VasProduct getVasProduct() {
		return this.vasProduct;
	}

	public void setVasProduct(VasProduct vasProduct) {
		this.vasProduct = vasProduct;
	}
}
