package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;
import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Shop;
import com.taobao.api.model.ShopCat;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoShopJSONConvert {
	
	/**
	 * 将返回的Json转换为Shop
	 * 
	 * @param json
	 *            JSONObject
	 * @return Shop
	 * @throws TaobaoApiException
	 */
	public static Shop convertJsonToShop(JSONObject json) throws JSONException {
		Shop getShop = new Shop();
		if (json.has(ApiConstants.SID)) {
			getShop.setSid(json.getString(ApiConstants.SID));
		}
		if (json.has(ApiConstants.CID)) {
			getShop.setCid(json.getString(ApiConstants.CID));
		}
		if (json.has(ApiConstants.TITLE)) {
			getShop.setTitle(json.getString(ApiConstants.TITLE));
		}
		if (json.has(ApiConstants.NICK)) {
			getShop.setNick(json.getString(ApiConstants.NICK));
		}
		if (json.has(ApiConstants.DESC)) {
			getShop.setDesc(json.getString(ApiConstants.DESC));
		}
		if (json.has(ApiConstants.BULLETIN)) {
			getShop.setBulletin(json.getString(ApiConstants.BULLETIN));
		}
		if (json.has(ApiConstants.PIC_PATH)) {
			getShop.setPicPath(json.getString(ApiConstants.PIC_PATH));
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				getShop.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ApiConstants.MODIFIED)) {
			try {
				getShop.setModified(DateUtil
						.strToDate(json.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return getShop;
	}
	
	/**
	 * 将返回的Json转换为ShopCat
	 * 
	 * @param json
	 *            JSONObject
	 * @return ShopCat
	 * @throws TaobaoApiException
	 */
	public static ShopCat convertJsonToShopCat(JSONObject json) throws JSONException {
		ShopCat newShopCat = new ShopCat();
		if (json.has(ApiConstants.CID)) {
			newShopCat.setCid(json.getString(ApiConstants.CID));
		}
		if (json.has(ApiConstants.IS_PARENT)) {
			newShopCat
					.setIsParent("true".equals(json.getString(ApiConstants.IS_PARENT)) ? true
							: false);
		}
		if (json.has(ApiConstants.NAME)) {
			newShopCat.setName(json.getString(ApiConstants.NAME));
		}
		if (json.has(ApiConstants.PARENT_CID)) {
			newShopCat.setParentCid(json.getString(ApiConstants.PARENT_CID));
		}
		return newShopCat;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>ShopCat>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>ShopCat>
	 * @throws TaobaoApiException
	 */
	public static List<ShopCat> convertJsonArrayToShopCatList(JSONArray jsonArray) {
		List<ShopCat> getShopCatList = new ArrayList<ShopCat>();
		ShopCat shopCat = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			shopCat = new ShopCat();
			try {
				shopCat = convertJsonToShopCat(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getShopCatList.add(shopCat);
		}
		return getShopCatList;
	}
	
}
