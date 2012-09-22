package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;
import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Area;
import com.taobao.api.model.Location;
import com.taobao.api.model.LogisticCompany;
import com.taobao.api.model.Shipping;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoShippingJSONConvert {
	
	/**
	 * 将返回的Json转换为Shipping
	 * 
	 * @param json
	 *            JSONObject
	 * @return Shipping
	 * @throws TaobaoApiException 
	 * @throws TaobaoApiException
	 */
	public static  Shipping convertJsonToShipping(JSONObject json) throws JSONException, TaobaoApiException {
		Shipping getShipping = new Shipping();
		if (json.has(ApiConstants.TID)) {
			getShipping.setTid(json.getString(ApiConstants.TID));
		}
		if (json.has(ApiConstants.SELLER_NICK)) {
			getShipping.setSellerNick(json.getString(ApiConstants.SELLER_NICK));
		}
		if (json.has(ApiConstants.BUYER_NICK)) {
			getShipping.setBuyerNick(json.getString(ApiConstants.BUYER_NICK));
		}
		if (json.has(ApiConstants.DELIVERY_START)) {
			try {
				getShipping.setDeliveryStart(DateUtil.strToDate(json.getString(ApiConstants.DELIVERY_START)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.DELIVERY_END)) {
			try {
				getShipping.setDeliveryEnd(DateUtil.strToDate(json
						.getString(ApiConstants.DELIVERY_END)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ApiConstants.OUT_SID)) {
			getShipping.setOutSid(json.getString(ApiConstants.OUT_SID));
		}
		if (json.has(ApiConstants.ITEM_TITLE)) {
			getShipping.setItemTitle(json.getString(ApiConstants.ITEM_TITLE));
		}
		if (json.has(ApiConstants.RECEIVER_NAME)) {
			getShipping.setReceiverName(json.getString(ApiConstants.RECEIVER_NAME));
		}
		if (json.has(ApiConstants.RECEIVER_MOBILE)) {
			getShipping.setReceiverMobile(json.getString(ApiConstants.RECEIVER_MOBILE));
		}
		if (json.has(ApiConstants.RECEIVER_PHONE)) {
			getShipping.setReceiverPhone(json.getString(ApiConstants.RECEIVER_PHONE));
		}
		if (json.has(ApiConstants.RECEIVER_LOCATION)) {
			Location location = new Location();
			JSONObject tempJson = json.getJSONObject(ApiConstants.RECEIVER_LOCATION);
			if (tempJson.has(ApiConstants.STATE)) {
				location.setState(tempJson.getString(ApiConstants.STATE));
			}
			if (tempJson.has(ApiConstants.CITY)) {
				location.setCity(tempJson.getString(ApiConstants.CITY));
			}
			if (tempJson.has(ApiConstants.DISTRICT)) {
				location.setDistrict(tempJson.getString(ApiConstants.DISTRICT));
			}
			if (tempJson.has(ApiConstants.ZIP)) {
				location.setZip(tempJson.getString(ApiConstants.ZIP));
			}
			if (tempJson.has(ApiConstants.ADDRESS)) {
				location.setAddress(tempJson.getString(ApiConstants.ADDRESS));
			}
			getShipping.setReceiverLocation(location);
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				getShipping.setCreated(DateUtil.strToDate(json
						.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.MODIFIED)) {
			try {
				getShipping.setModified(DateUtil.strToDate(json
						.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.STATUS)) {
			getShipping.setStatus(json.getString(ApiConstants.STATUS));
		}
		if (json.has(ApiConstants.TYPE)) {
			getShipping.setType(json.getString(ApiConstants.TYPE));
		}
		if (json.has(ApiConstants.FREIGHT_PAYER)) {
			getShipping.setFreightPayer(json.getString(ApiConstants.FREIGHT_PAYER));
		}
		if (json.has(ApiConstants.COMPANY_NAME)) {
			getShipping.setCompanyName(json.getString(ApiConstants.COMPANY_NAME));
		}
		if (json.has(ApiConstants.SELLER_CONFIRM)) {
			getShipping.setSellerConfirm(json.getString(ApiConstants.SELLER_CONFIRM));
		}
		return getShipping;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>Shipping>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>Shipping>
	 * @throws TaobaoApiException 
	 * @throws TaobaoApiException
	 */
	public static List<Shipping> convertJsonArrayToShippingList(JSONArray jsonArray) throws TaobaoApiException {
		List<Shipping> getShippingList = new ArrayList<Shipping>();
		Shipping getShipping = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getShipping = convertJsonToShipping(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			getShippingList.add(getShipping);
		}
		return getShippingList;
	}
	
	/**
	 * 将返回的JsonArray转换为List<Area>
	 * @param jsonArray
	 * @return List<Area>
	 * @throws TaobaoApiException 
	 */
	public static List<Area> convertJsonArrayToAreaList(JSONArray jsonArray) throws TaobaoApiException{
		List<Area> getAreaList = new ArrayList<Area>();
		Area area = null;
		for(int i = 0; i<jsonArray.length(); i++){
			try {
				area = convertJsonToArea(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			getAreaList.add(area);
		}
		return getAreaList;
	}
	
	public static Area convertJsonToArea(JSONObject json) throws JSONException{
		Area area = new Area();
		if(json.has(ApiConstants.AREA_ID)){
			area.setAreaId(json.getString(ApiConstants.AREA_ID));
		}
		if(json.has(ApiConstants.AREA_TYPE)){
			area.setAreaType(json.getString(ApiConstants.AREA_TYPE));
		}
		if(json.has(ApiConstants.AREA_NAME)){
			area.setAreaName(json.getString(ApiConstants.AREA_NAME));
		}
		if(json.has(ApiConstants.PARENT_ID)){
			area.setParentId(json.getString(ApiConstants.PARENT_ID));
		}
		if(json.has(ApiConstants.ZIP)){
			area.setZip(json.getString(ApiConstants.ZIP));
		}
		return area;
		
	}
	
	/**
	 * 将返回的JsonArray转换为List<LogisticCompany>
	 * @param jsonArray
	 * @return List<LogisticCompany>
	 * @throws TaobaoApiException
	 */
	public static List<LogisticCompany> convertJsonArrayToLogisticCompanyList(JSONArray jsonArray) throws TaobaoApiException{
		List<LogisticCompany> getLogisticCompanyList = new ArrayList<LogisticCompany>();
		LogisticCompany logisticCompany = null;
		for(int i = 0; i<jsonArray.length(); i++){
			try {
				logisticCompany = convertJsonToLogisticCompany(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			getLogisticCompanyList.add(logisticCompany);
		}
		return getLogisticCompanyList;
	}
	
	public static LogisticCompany convertJsonToLogisticCompany(JSONObject json) throws JSONException{
		LogisticCompany logisticCompany = new LogisticCompany();
		if(json.has(ApiConstants.COMPANY_ID)){
			logisticCompany.setCompanyId(json.getString(ApiConstants.COMPANY_ID));
		}
		if(json.has(ApiConstants.COMPANY_CODE)){
			logisticCompany.setCompanyCode(json.getString(ApiConstants.COMPANY_CODE));
		}
		if(json.has(ApiConstants.COMPANY_NAME)){
			logisticCompany.setCompanyName(json.getString(ApiConstants.COMPANY_NAME));
		}
		return logisticCompany;
	}
	
	public static Shipping convertJsonToShippingDeliverySend(JSONObject json) throws JSONException{
		Shipping shipping = new Shipping();
		if(json.has(ApiConstants.IS_SUCCESS)){
			shipping.setSuccess(Boolean.valueOf(json.getString(ApiConstants.IS_SUCCESS)));
		}
		return shipping;
	}
}
