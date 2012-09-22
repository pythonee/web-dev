package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;
import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Postage;
import com.taobao.api.model.PostageMode;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoPostageJSONConvert {
	
	/**
	 * 将返回的Json转换为Postage
	 * 
	 * @param json
	 * @return Postage
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static Postage convertJsonToPostage(JSONObject json) throws JSONException,TaobaoApiException {
		Postage postage = new Postage();
		if (json.has(ApiConstants.POSTAGE_ID)) {
			postage.setPostageId(json.getString(ApiConstants.POSTAGE_ID));
		}
		if (json.has(ApiConstants.NAME)) {
			postage.setName(json.getString(ApiConstants.NAME));
		}
		if (json.has(ApiConstants.MEMO)) {
			postage.setMemo(json.getString(ApiConstants.MEMO));
		}
		try {
			if (json.has(ApiConstants.CREATED)) {

				postage.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			}
			if (json.has(ApiConstants.MODIFIED)) {
				postage.setModified(DateUtil.strToDate(json.getString(ApiConstants.MODIFIED)));
			}
		} catch (ParseException e) {
			throw new TaobaoApiException();
		}
		if(json.has(ApiConstants.POST_PRICE)){
			postage.setPostPrice(json.getString(ApiConstants.POST_PRICE));
		}
		if(json.has(ApiConstants.POST_INCREASE)){
			postage.setPostIncrease(json.getString(ApiConstants.POST_INCREASE));
		}
		if(json.has(ApiConstants.EXPRESS_PRICE)){
			postage.setExpressPrice(json.getString(ApiConstants.EXPRESS_PRICE));
		}
		if(json.has(ApiConstants.EXPRESS_INCREASE)){
			postage.setExpressIncrease(json.getString(ApiConstants.EXPRESS_INCREASE));
		}
		if(json.has(ApiConstants.EMS_PRICE)){
			postage.setEmsPrice(json.getString(ApiConstants.EMS_PRICE));
		}
		if(json.has(ApiConstants.EMS_INCREASE)){
			postage.setEmsIncrease(json.getString(ApiConstants.EMS_INCREASE));
		}
		if(json.has(ApiConstants.POSTAGE_MODE)){
			postage.setPostageModeList(convertJsonArrayToPostageModeList(json.getJSONArray(ApiConstants.POSTAGE_MODE),postage));
		}
		return postage;
	}
	
	public static List<PostageMode> convertJsonArrayToPostageModeList(JSONArray jsonArray,Postage postage) throws TaobaoApiException{
		List<PostageMode> modeList = new ArrayList<PostageMode>();
		PostageMode mode = null;
		for(int i=0; i < jsonArray.length(); i++) {
			try {
				mode = convertJsonToPostageMode(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			modeList.add(mode);
		}
		return modeList;
	}
	
	public static PostageMode convertJsonToPostageMode(JSONObject json) throws JSONException,TaobaoApiException{
		PostageMode mode = new PostageMode();
		if(json.has(ApiConstants.POSTAGE_MODE_ID_OLD)){
			mode.setPostageModeId(json.getString(ApiConstants.POSTAGE_MODE_ID_OLD));
		}
		if (json.has(ApiConstants.POSTAGE_MODE_TYPE_OLD)) {
			mode.setPostageModeType(json.getString(ApiConstants.POSTAGE_MODE_TYPE_OLD));
		}
		if(json.has(ApiConstants.DEST)){
			mode.setDest(json.getString(ApiConstants.DEST));
		}
		if(json.has(ApiConstants.PRICE)){
			mode.setPrice(json.getString(ApiConstants.PRICE));
		}
		if(json.has(ApiConstants.INCREASE)){
			mode.setIncrease(json.getString(ApiConstants.INCREASE));
		}
		return mode;
	}
	
	/**
	 * 将返回的JsonArray转换为List<postage>
	 * 
	 * @param jsonArray
	 * @return List<Postage>
	 * @throws TaobaoApiException
	 */
	public static List<Postage> convertJsonArrayToPostageList(JSONArray jsonArray) throws TaobaoApiException {
		List<Postage> getPostageList = new ArrayList<Postage>();
		Postage postage = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				postage = convertJsonToPostage(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			getPostageList.add(postage);
		}
		return getPostageList;
	}
	
}
