package com.taobao.api.model;

import com.taobao.api.ApiConstants;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;

/**
 * 代表一个productpricestat请求的http返回结果
 * 
 * @author hukui
 */

public class ProductPriceStatGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 3775054599809550437L;

	private static final String PRICE_STAT = "price_stat";   //产品价格统计信息 
	
	private ProductPriceStat priceStat;   //产品价格统计信息 

	public ProductPriceStat getPriceStat() {
		return priceStat;
	}

	public void setPriceStat(ProductPriceStat priceStat) {
		this.priceStat = priceStat;
	}
	
	public ProductPriceStatGetResponse() {
		super();
	}

	public ProductPriceStatGetResponse(TaobaoResponse ur) {
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
				if (rsp.has(ProductPriceStatGetResponse.PRICE_STAT)) {
					priceStat = ProductPriceStat.convertJsonToObject(rsp.getJSONObject(ProductPriceStatGetResponse.PRICE_STAT));
				}
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}
