package com.taobao.api.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 代表一个获取产品统计信息的http请求
 * @author hukui
 *
 */
public class ProductStatGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = 7976534782212535635L;

	public static final String PRODUCT_ID= "product_id";
	
	private String productId; 	 //产品id。

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获得请求的参数map列表
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put(ProductStatGetRequest.PRODUCT_ID, productId);

		return params;
	}
	
}
