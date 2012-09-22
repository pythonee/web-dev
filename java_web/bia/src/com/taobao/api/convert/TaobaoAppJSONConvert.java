package com.taobao.api.convert;

import com.taobao.api.ApiConstants;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.Tadget;

/**
 * 淘宝应用数据转换类。
 * 
 * @author fengsheng
 * @since 1.0, Jul 24, 2009
 */
public abstract class TaobaoAppJSONConvert {

	public static Tadget convertJsonToTadget(JSONObject json) throws JSONException {
		Tadget tadget = new Tadget();

		if (json.has(ApiConstants.APP_KEY)) {
			tadget.setAppKey(json.getString(ApiConstants.APP_KEY));
		}

		if (json.has(ApiConstants.APP_SECRET)) {
			tadget.setAppSecret(json.getString(ApiConstants.APP_SECRET));
		}

		return tadget;
	}

}
