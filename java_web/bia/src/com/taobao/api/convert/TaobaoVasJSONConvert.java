package com.taobao.api.convert;

import java.util.Date;

import com.taobao.api.ApiConstants;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.VasProduct;

/**
 * 淘宝增值服务数据转换类。
 * 
 * @author fengsheng
 * @since 1.0, Jul 14, 2009
 */
public abstract class TaobaoVasJSONConvert {

	/**
	 * 把JSON字符串转换为增值服务产品类。
	 * 
	 * @param json JSON字符串
	 * @return 增值服务产品类
	 * @throws JSONException
	 */
	public static VasProduct convertJsonToVasProduct(JSONObject json) throws JSONException {
		VasProduct vasProduct = new VasProduct();

		if (json.has(ApiConstants.PROD_ID)) {
			vasProduct.setProdId(json.getLong(ApiConstants.PROD_ID));
		}

		if (json.has(ApiConstants.PROD_NAME)) {
			vasProduct.setProdName(json.getString(ApiConstants.PROD_NAME));
		}

		if (json.has(ApiConstants.SERV_NAME)) {
			vasProduct.setServName(json.getString(ApiConstants.SERV_NAME));
		}

		if (json.has(ApiConstants.SERV_CODE)) {
			vasProduct.setServCode(json.getString(ApiConstants.SERV_CODE));
		}

		if (json.has(ApiConstants.PLAN_ID)) {
			vasProduct.setPlanId(json.getLong(ApiConstants.PLAN_ID));
		}

		if (json.has(ApiConstants.PLAN_NAME)) {
			vasProduct.setPlanName(json.getString(ApiConstants.PLAN_NAME));
		}

		if (json.has(ApiConstants.START_DATE)) {
			JSONObject jsonStart = json.getJSONObject(ApiConstants.START_DATE);
			if (jsonStart.has(ApiConstants.TIME)) {
				Long startDate = jsonStart.getLong(ApiConstants.TIME);
				vasProduct.setStartDate(new Date(startDate));
			}
		}

		if (json.has(ApiConstants.END_DATE)) {
			JSONObject jsonEnd = json.getJSONObject(ApiConstants.END_DATE);
			if (jsonEnd.has(ApiConstants.TIME)) {
				Long endDate = jsonEnd.getLong(ApiConstants.TIME);
				vasProduct.setEndDate(new Date(endDate));
			}
		}

		if (json.has(ApiConstants.STATUS)) {
			vasProduct.setStatus(json.getLong(ApiConstants.STATUS));
		}

		return vasProduct;
	}

}
