package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Suite;
import com.taobao.api.util.DateUtil;

/**
 * 
 * @author Taylor xuwei840916@hotmail.com
 * @version 2009-2-18 下午07:49:12
 */
public class TaobaoSuiteJSONConvert {
	/**
	 * 将返回的JsonArray转换为List<code><</code>Combo>
	 * 
	 * @param jsonArray Json数组
	 * @return List<code><</code>Combo>
	 * @throws TaobaoApiException
	 */
	public static List<Suite> convertJsonArrayToSuiteList(JSONArray jsonArray) throws TaobaoApiException {
		List<Suite> suiteList = new ArrayList<Suite>();
		Suite suite = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				suite = convertJsonToSuite(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			suiteList.add(suite);
		}
		return suiteList;
	}

	/**
	 * 将返回的json转换为Combo对象
	 * 
	 * @param json
	 * @return Combo
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static Suite convertJsonToSuite(JSONObject json) throws JSONException, TaobaoApiException {
		Suite suite = new Suite();
		if(json.has(ApiConstants.ID)) {
			suite.setId(json.getString(ApiConstants.ID));
		}
		if (json.has(ApiConstants.NICK)) {
			suite.setNick(json.getString(ApiConstants.NICK));
		}

		if (json.has(ApiConstants.SUITE_NAME)) {
			suite.setSuiteName(json.getString(ApiConstants.SUITE_NAME));
		}

		if (json.has(ApiConstants.START_DATE)) {
			try {
				suite.setStartDate(DateUtil.strToDate(json.getString(ApiConstants.START_DATE)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.END_DATE)) {
			try {
				suite.setEndDate(DateUtil.strToDate(json.getString(ApiConstants.END_DATE)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}

		return suite;
	}
}
