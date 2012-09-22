package com.taobao.api.model;

import com.taobao.api.ApiConstants;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;

/**
 * 代表一个获取产品统计信息的http请求返回结果
 * @author hukui
 *
 */
public class ProductStatGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = -8367751904177429215L;

	private static final String PRODUCT = "product"; //产品统计信息 对应属性标识 
	
	private ProductStat product;    // 产品统计信息 
	
	
	public ProductStat getProduct() {
		return product;
	}

	public void setProduct(ProductStat product) {
		this.product = product;
	}

	public ProductStatGetResponse() {
		super();
	}

	public ProductStatGetResponse(TaobaoResponse ur) {
		super(ur);
	}

	protected void parseError(JSONObject json) throws JSONException {

		if (json.has(ApiConstants.ERROR_RSP)) {
			JSONObject errorRsp = json.getJSONObject(ApiConstants.ERROR_RSP);

			setErrorCode(errorRsp.getString(ApiConstants.ERROR_CODE));

			setMsg(errorRsp.getString(ApiConstants.ERROR_MSG));
		}
	}

	public boolean fillResponse(JSONObject json) {
		try {
			parseError(json);
			// 对返回结果进行处理
			if (isSuccess()) {
				JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
				if (rsp.has(ProductStatGetResponse.PRODUCT)) {
					product = ProductStat.convertJsonToObject(rsp.getJSONObject(ProductStatGetResponse.PRODUCT));
				}
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}
