package com.taobao.api.convert;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;
import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Spu;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoSpuJSONConvert {
	
	/**
	 * 将返回的Json转换为Spu
	 * 
	 * @param tempSpu
	 *            JSONObject
	 * @return Spu
	 * @throws TaobaoApiException
	 */
	public static Spu convertJsonToSpu(JSONObject tempSpu) throws JSONException {
		Spu getSpu = new Spu();
		if (tempSpu.has(ApiConstants.CID)) {
			getSpu.setCid(tempSpu.getString(ApiConstants.CID));
		}
		if (tempSpu.has(ApiConstants.PROPS)) {
			getSpu.setProps(tempSpu.getString(ApiConstants.PROPS));
		}
		if (tempSpu.has(ApiConstants.NAME)) {
			getSpu.setName(tempSpu.getString(ApiConstants.NAME));
		}
		if (tempSpu.has(ApiConstants.PIC_PATH)) {
			getSpu.setPicPath(tempSpu.getString(ApiConstants.PIC_PATH));
		}
		if (tempSpu.has(ApiConstants.BINDS)) {
			getSpu.setBinds(tempSpu.getString(ApiConstants.BINDS));
		}
		return getSpu;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>Spu>
	 * 
	 * @param spus
	 *            Json数组
	 * @return List<code><</code>Spu>
	 * @throws TaobaoApiException
	 */
	public static List<Spu> convertJsonArrayToSpuList(JSONArray spus) throws TaobaoApiException {
		List<Spu> spuList = new ArrayList<Spu>();
		for (int i = 0; i < spus.length(); i++) {
			JSONObject tempSpu = null;
			Spu getSpu = null;
			try {
				tempSpu = spus.getJSONObject(i);
				getSpu = convertJsonToSpu(tempSpu);
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			spuList.add(getSpu);
		}
		return spuList;
	}
}
