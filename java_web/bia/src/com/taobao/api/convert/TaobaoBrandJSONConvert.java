package com.taobao.api.convert;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;
import com.taobao.api.ApiConstants;
import com.taobao.api.model.Brand;

/**
 * 品牌Model和JSON的转换辅助类
 * 
 * @author jeck.xie 2009-3-25
 * @see Brand
 */
public class TaobaoBrandJSONConvert {

	public static List<Brand> convertJsonArray2BrandList(JSONArray array) throws JSONException {
		List<Brand> result = new ArrayList<Brand>();

		for (int i = 0; i < array.length(); i++) 
			result.add(convertJson2Brand(array.getJSONObject(i)));

		return result;
	}

	public static Brand convertJson2Brand(JSONObject json) throws JSONException {
		Brand brand = new Brand();
		if (json.has(ApiConstants.VID))
			brand.setVid(json.getString(ApiConstants.VID));

		if (json.has(ApiConstants.NAME))
			brand.setName(json.getString(ApiConstants.NAME));
		
		if (json.has(ApiConstants.PID))
			brand.setPid(json.getString(ApiConstants.PID));
		
		if (json.has(ApiConstants.PROP_NAME))
			brand.setPropName(json.getString(ApiConstants.PROP_NAME));

		return brand;
	}

}
