package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.GuarantyFund;
import com.taobao.api.model.SellerCat;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoSellerJSONConvert {
	
	/**
	 * 将返回的Json转换为SellerCat
	 * 
	 * @param json
	 *            JSONObject
	 * @return SellerCat
	 * @throws TaobaoApiException
	 */
	public static SellerCat convertJsonToSellerCat(JSONObject json) throws JSONException {
		SellerCat newSellerCat = new SellerCat();
		if (json.has(ApiConstants.CID)) {
			newSellerCat.setCid(json.getString(ApiConstants.CID));
		}
		if (json.has(ApiConstants.NAME)) {
			newSellerCat.setName(json.getString(ApiConstants.NAME));
		}
		if (json.has(ApiConstants.PARENT_CID)) {
			newSellerCat.setParentCid(json.getString(ApiConstants.PARENT_CID));
		}
		if(json.has(ApiConstants.PICT_URL)){
			newSellerCat.setPicUrl((json.getString(ApiConstants.PICT_URL)));
		}
		if(json.has(ApiConstants.SORT_ORDER)){
			newSellerCat.setSortOrder(json.getInt(ApiConstants.SORT_ORDER));
		}
//		if (json.has(ApiConstants.IS_PARENT)) {
//			newSellerCat
//					.setIsParent("true".equals(json.getString(ApiConstants.IS_PARENT)) ? true
//							: false);
//		}
		return newSellerCat;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>SellerCat>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>SellerCat>
	 * @throws TaobaoApiException
	 */
	public static List<SellerCat> convertJsonArrayToSellerCatList(JSONArray jsonArray) {
		List<SellerCat> getSellerCatList = new ArrayList<SellerCat>();
		SellerCat sellerCat = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			sellerCat = new SellerCat();
			try {
				sellerCat = convertJsonToSellerCat(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getSellerCatList.add(sellerCat);
		}
		return getSellerCatList;
	}

	public static List<GuarantyFund> convertJsonArrayToGuarantyFundList(
			JSONArray jsonArray) throws JSONException {
		List<GuarantyFund> guarantyFunds = new ArrayList<GuarantyFund>();

		for (int i = 0; i < jsonArray.length(); i++) {
			GuarantyFund guarantyFund = convertJsonToGuarantyFund(jsonArray.getJSONObject(i));
			guarantyFunds.add(guarantyFund);
		}

		return guarantyFunds;
	}

	private static GuarantyFund convertJsonToGuarantyFund(JSONObject jsonObject)
			throws JSONException {
		GuarantyFund guarantyFund = new GuarantyFund();

		if (jsonObject.has(ApiConstants.ID)) {
			guarantyFund.setId(jsonObject.getLong(ApiConstants.ID));
		}
		if (jsonObject.has(ApiConstants.TYPE)) {
			guarantyFund.setType(jsonObject.getInt(ApiConstants.TYPE));
		}
		if (jsonObject.has(ApiConstants.AMOUNT)) {
			guarantyFund.setAmount(jsonObject.getLong(ApiConstants.AMOUNT));
		}
		if (jsonObject.has(ApiConstants.BALANCE)) {
			guarantyFund.setBalance(jsonObject.getLong(ApiConstants.BALANCE));
		}
		if (jsonObject.has(ApiConstants.SELLER_ID)) {
			guarantyFund.setSellerId(jsonObject.getLong(ApiConstants.SELLER_ID));
		}
		if (jsonObject.has(ApiConstants.SELLER_NICK)) {
			guarantyFund.setSellerNick(jsonObject.getString(ApiConstants.SELLER_NICK));
		}
		if (jsonObject.has(ApiConstants.BUYER_ID)) {
			guarantyFund.setBuyerId(jsonObject.getLong(ApiConstants.BUYER_ID));
		}
		if (jsonObject.has(ApiConstants.BUYER_NICK)) {
			guarantyFund.setBuyerNick(jsonObject.getString(ApiConstants.BUYER_NICK));
		}
		if (jsonObject.has(ApiConstants.ORDER_ID)) {
			guarantyFund.setOrderId(jsonObject.getLong(ApiConstants.ORDER_ID));
		}
		if (jsonObject.has(ApiConstants.ACCUSE_ID)) {
			guarantyFund.setAccuseId(jsonObject.getLong(ApiConstants.ACCUSE_ID));
		}
		if (jsonObject.has(ApiConstants.OPERATION_TYPE)) {
			guarantyFund.setOperationType(jsonObject.getInt(ApiConstants.OPERATION_TYPE));
		}
		if (jsonObject.has(ApiConstants.OPERATION_ACTION)) {
			guarantyFund.setOperationAction(jsonObject.getString(ApiConstants.OPERATION_ACTION));
		}
		if (jsonObject.has(ApiConstants.MEMO)) {
			guarantyFund.setMemo(jsonObject.getString(ApiConstants.MEMO));
		}
		if (jsonObject.has(ApiConstants.CREATOR)) {
			guarantyFund.setCreator(jsonObject.getString(ApiConstants.CREATOR));
		}
		if (jsonObject.has(ApiConstants.OPERATOR)) {
			guarantyFund.setOperator(jsonObject.getString(ApiConstants.OPERATOR));
		}
		if (jsonObject.has(ApiConstants.CREATED)) {
			try {
				Date created = DateUtil.strToDate(jsonObject.getString(ApiConstants.CREATED));
				guarantyFund.setCreated(created);
			} catch (ParseException e) {
				throw new JSONException(e);
			}
		}
		if (jsonObject.has(ApiConstants.MODIFIED)) {
			try {
				Date modified = DateUtil.strToDate(jsonObject.getString(ApiConstants.MODIFIED));
				guarantyFund.setModified(modified);
			} catch (ParseException e) {
				throw new JSONException(e);
			}
		}
		if (jsonObject.has(ApiConstants.STATUS)) {
			guarantyFund.setStatus(jsonObject.getInt(ApiConstants.STATUS));
		}

		return guarantyFund;
	}

}
