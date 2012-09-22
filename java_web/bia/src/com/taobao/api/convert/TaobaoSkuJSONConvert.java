package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Sku;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoSkuJSONConvert {
	
	/**
	 * 将单个json object转换为sku
	 * 
	 * @param json
	 * @return Sku
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static Sku convertJsonToSku(JSONObject json) throws JSONException,TaobaoApiException {
		Sku sku = new Sku();

		if (json.has(ApiConstants.SKU_ID)) {
			sku.setSkuId(json.getString(ApiConstants.SKU_ID));
		}
		if(json.has(ApiConstants.EXTRA_ID)){
			sku.setExtraId(json.getString(ApiConstants.EXTRA_ID));
		}
		if (json.has(ApiConstants.IID)) {
			sku.setIid(json.getString(ApiConstants.IID));
		}
		if (json.has(ApiConstants.PROPERTIES)) {
			sku.setProperties(json.getString(ApiConstants.PROPERTIES));
		}
		if (json.has(ApiConstants.QUANTITY)) {
			sku.setQuantity(json.getInt(ApiConstants.QUANTITY));
		}
		if (json.has(ApiConstants.PRICE)) {
			sku.setPrice(json.getString(ApiConstants.PRICE));
		}
		if (json.has(ApiConstants.OUTER_ID)) {
			sku.setOuterId(json.getString(ApiConstants.OUTER_ID));
		}
		if(json.has(ApiConstants.STATUS)){
			sku.setStatus(json.getString(ApiConstants.STATUS));
		}
		/* --- delete by jeck 2009-04-15 

		if (json.has(ApiConstants.MEMO)) {
			sku.setMemo(json.getString(ApiConstants.MEMO));
		}
	 	
		--- */
		if (json.has(ApiConstants.CREATED)) {
			try {
				sku.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.MODIFIED)) {
			try {
				sku.setModified(DateUtil.strToDate(json.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		return sku;
	}
	
	/**
	 * 将返回的JsonArray转换为List<Sku>
	 * 
	 * @param jsonArray
	 * @return List<Sku>
	 * @throws TaobaoApiException
	 */
	public static List<Sku> convertJsonArrayToSkuList(JSONArray jsonArray) throws TaobaoApiException {
		List<Sku> skus = new ArrayList<Sku>();
		Sku sku = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				sku = convertJsonToSku(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			skus.add(sku);
		}
		return skus;
	}
}
