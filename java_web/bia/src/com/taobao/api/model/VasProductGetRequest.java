/**
 * 
 */
package com.taobao.api.model;

/**
 * 获取产品增值服务请求封装。
 * 
 * @author yunshu
 * @since 1.0, Jul 13, 2009
 */
public class VasProductGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = -1209095962914649976L;

	private Long subProdId; // 产品订购ID

	public Long getSubProdId() {
		return subProdId;
	}

	public void setSubProdId(Long subProdId) {
		this.subProdId = subProdId;
	}

}
