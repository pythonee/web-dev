package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.Feature;
import com.taobao.api.model.Item;
import com.taobao.api.model.ItemCat;
import com.taobao.api.model.ItemCategory;
import com.taobao.api.model.ItemImg;
import com.taobao.api.model.ItemProp;
import com.taobao.api.model.ItemSearch;
import com.taobao.api.model.Location;
import com.taobao.api.model.PropValue;
import com.taobao.api.util.DateUtil;

/**
 * 淘宝商品JSON转换类
 * @author gaoweibin.tw
 *
 */
public class TaobaoItemJSONConvert {
	
	/**
	 * 取到商品时将返回的json转换为item对象
	 * 
	 * @param json
	 * @return Item
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static Item convertJsonToItem(JSONObject json) throws JSONException,TaobaoApiException {
		Item getItem = new Item();
		if (json.has(ApiConstants.IID)) {
			getItem.setIid(json.getString(ApiConstants.IID));
		}
		if (json.has(ApiConstants.NUM_IID)) {
			getItem.setNumIid(json.getString(ApiConstants.NUM_IID));
		}
		if (json.has(ApiConstants.DETAIL_URL)) {
			getItem.setDetailUrl(json.getString(ApiConstants.DETAIL_URL));
		}
		if(json.has(ApiConstants.OUTER_ID)){
			getItem.setOuterId(json.getString(ApiConstants.OUTER_ID));
		}
		if (json.has(ApiConstants.TITLE)) {
			getItem.setTitle(json.getString(ApiConstants.TITLE));
		}
		if (json.has(ApiConstants.IS_VIRTURAL)){
			getItem.setIsVirtural(json.getString(ApiConstants.IS_VIRTURAL));
		}
		if (json.has(ApiConstants.NICK)) {
			getItem.setNick(json.getString(ApiConstants.NICK));
		}
		if (json.has(ApiConstants.TYPE)) {
			getItem.setType(json.getString(ApiConstants.TYPE));
		}
		if (json.has(ApiConstants.CID)) {
			getItem.setCid(json.getString(ApiConstants.CID));
		}
		if (json.has(ApiConstants.SELLER_CIDS)) {
			getItem.setSellerCids(json.getString(ApiConstants.SELLER_CIDS));
		}
		if (json.has(ApiConstants.PROPS)) {
			getItem.setProps(json.getString(ApiConstants.PROPS));
		}
		if (json.has(ApiConstants.DESC)) {
			getItem.setDesc(json.getString(ApiConstants.DESC));
		}
		if (json.has(ApiConstants.PIC_PATH)) {
			getItem.setPicPath(json.getString(ApiConstants.PIC_PATH));
		}
		if (json.has(ApiConstants.NUM)) {
			getItem.setNum(Integer.parseInt(json.getString(ApiConstants.NUM)));
		}
		if (json.has(ApiConstants.VALID_THRU)) {
			getItem.setValidThru(Integer.parseInt(json.getString(ApiConstants.VALID_THRU)));
		}
		if (json.has(ApiConstants.LIST_TIME)) {
			try {
				getItem.setListTime(DateUtil.strToDate(json.getString(ApiConstants.LIST_TIME)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.DELIST_TIME)) {
			try {
				getItem.setDelistTime(DateUtil.strToDate(json.getString(ApiConstants.DELIST_TIME)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.STUFF_STATUS)) {
			getItem.setStuffStatus(json.getString(ApiConstants.STUFF_STATUS));
		}
		if (json.has(ApiConstants.LOCATION)) {
			JSONObject tempJson = json.getJSONObject(ApiConstants.LOCATION);
			String state = null;
			String city = null;
			if (tempJson.has(ApiConstants.STATE)) {
				state = tempJson.getString(ApiConstants.STATE);
			}
			if (tempJson.has(ApiConstants.CITY)) {
				city = tempJson.getString(ApiConstants.CITY);
			}
			Location location = new Location();
			location.setState(state);
			location.setCity(city);
			getItem.setLocation(location);
		}
		if (json.has(ApiConstants.PRICE)) {
			getItem.setPrice(json.getString(ApiConstants.PRICE));
		}
		if (json.has(ApiConstants.POST_FEE)) {
			getItem.setPostFee(json.getString(ApiConstants.POST_FEE));
		}
		if (json.has(ApiConstants.EXPRESS_FEE)) {
			getItem.setExpressFee(json.getString(ApiConstants.EXPRESS_FEE));
		}
		if (json.has(ApiConstants.EMS_FEE)) {
			getItem.setEmsFee(json.getString(ApiConstants.EMS_FEE));
		}
		if (json.has(ApiConstants.HAS_DISCOUNT)) {
			getItem.setHasDiscount(json.getString(ApiConstants.HAS_DISCOUNT).equals("true") ? true : false);
		}
		if (json.has(ApiConstants.FREIGHT_PAYER)) {
			getItem.setFreightPayer(json.getString(ApiConstants.FREIGHT_PAYER));
		}
		if (json.has(ApiConstants.HAS_INVOICE)) {
			getItem.setHasInvoice(json.getString(ApiConstants.HAS_INVOICE).equals("true") ? true : false);
		}
		if (json.has(ApiConstants.HAS_WARRANTY)) {
			getItem.setHasWarranty(json.getString(ApiConstants.HAS_WARRANTY).equals("true") ? true	: false);
		}
		if (json.has(ApiConstants.HAS_SHOWCASE)) {
			getItem.setHasShowcase(json.getString(ApiConstants.HAS_SHOWCASE).equals("true") ? true	: false);
		}
		if (json.has(ApiConstants.BULK_BASE_NUM)) {
			getItem.setBulkBaseNum(Integer.parseInt(json.getString(ApiConstants.BULK_BASE_NUM)));
		}
		if (json.has(ApiConstants.MODIFIED)) {
			try {
				getItem.setModified(DateUtil.strToDate(json.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.INCREMENT)) {
			getItem.setIncrement(json.getString(ApiConstants.INCREMENT));
		}
		if (json.has(ApiConstants.AUTO_REPOST)) {
			getItem.setAutoRepost(json.getString(ApiConstants.AUTO_REPOST).equals("true") ? true : false);
		}
		if (json.has(ApiConstants.APPROVE_STATUS)) {
			getItem.setApproveStatus(json.getString(ApiConstants.APPROVE_STATUS));
		}
		if (json.has(ApiConstants.POSTAGE_ID)) {
			getItem.setPostageId(json.getString(ApiConstants.POSTAGE_ID));
		}
		if (json.has(ApiConstants.PRODUCT_ID)) {
			getItem.setProductId(json.getString(ApiConstants.PRODUCT_ID));
		}
		if (json.has(ApiConstants.AUCTION_POINT)) {
			getItem.setAuctionPoint(json.getString(ApiConstants.AUCTION_POINT));
		}
		if (json.has(ApiConstants.PROPERTY_ALIAS)) {
			getItem.setPropertyAlias(json.getString(ApiConstants.PROPERTY_ALIAS));
		}
		if (json.has(ApiConstants.ITEM_IMG)) {
			getItem.setItemImgs(convertJsonArrayToItemImgList(json.getJSONArray(ApiConstants.ITEM_IMG)));
		}
		if (json.has(ApiConstants.PROP_IMG)) {
			getItem.setPropImgs(TaobaoProductJSONConvert.convertJsonArrayToPropImgList(json.getJSONArray(ApiConstants.PROP_IMG)));
		}
		if (json.has(ApiConstants.SKU)) {
			getItem.setSkus(TaobaoSkuJSONConvert.convertJsonArrayToSkuList(json.getJSONArray(ApiConstants.SKU)));
		}
		if (json.has(ApiConstants.INPUT_PIDS)) {
			getItem.setInputPids(json.getString(ApiConstants.INPUT_PIDS));
		}
		if (json.has(ApiConstants.INPUT_STR)) {
			getItem.setInputStr(json.getString(ApiConstants.INPUT_STR));
		}
		if (json.has(ApiConstants.IS_TIMING)) {
			getItem.setIsTiming(json.getBoolean(ApiConstants.IS_TIMING));
		}
		if (json.has(ApiConstants.IS_TAOBAO)) {
			getItem.setIsTaobao(json.getBoolean(ApiConstants.IS_TAOBAO));
		}
		if (json.has(ApiConstants.IS_EX)) {
			getItem.setIsEx(json.getBoolean(ApiConstants.IS_EX));
		}
		if (json.has(ApiConstants.VIDEO)) {
			getItem.setVideos(TaobaoVideoJSONConvert.convertJsonArrayToVideoList(json.getJSONArray(ApiConstants.VIDEO)));
		}
		if (json.has(ApiConstants.IS_3D)) {
			getItem.setIs3D(json.getBoolean(ApiConstants.IS_3D));
		}
		if (json.has(ApiConstants.SCORE)) {
			getItem.setScore(json.getLong(ApiConstants.SCORE));
		}
		if (json.has(ApiConstants.VOLUME)) {
			getItem.setVolume(json.getLong(ApiConstants.VOLUME));
		}
		if (json.has(ApiConstants.ONE_STATION)) {
			getItem.setOneStation(json.getBoolean(ApiConstants.ONE_STATION));
		}
		return getItem;
	}

	/**
	 * 将返回的JsonArray转换为List<code><</code>Item>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>Item>
	 * @throws TaobaoApiException
	 */
	public static List<Item> convertJsonArrayToItemList(JSONArray jsonArray) throws TaobaoApiException {
		List<Item> getItemList = new ArrayList<Item>();
		Item getItem = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getItem = convertJsonToItem(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			getItemList.add(getItem);
		}
		return getItemList;
	}
	
	/**
	 * 将返回的JSonArray 转换为 Item List
	 * @param jsonArray
	 * @return ItemList
	 * @throws TaobaoApiException
	 */
	public static List<Item> convertJsonArrayToItemsList(JSONArray jsonArray) throws TaobaoApiException{
		List<Item> itemsList = new ArrayList<Item>();
		Item item = null;
		for(int i = 0; i<jsonArray.length(); i++){
			try {
				item = convertJsonToItem(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			itemsList.add(item);
		}
		return itemsList;
	}
	
	/**
	 * 将返回的JsonArray转换为List<ItemImg>
	 * 
	 * @param jsonArray
	 * @return List<ItemImg>
	 * @throws TaobaoApiException
	 */
	public static List<ItemImg> convertJsonArrayToItemImgList(JSONArray jsonArray) throws TaobaoApiException {
		List<ItemImg> itemImgs = new ArrayList<ItemImg>();
		ItemImg img = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				img = convertJsonToItemImg(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			itemImgs.add(img);
		}
		return itemImgs;
	}
	
	/**
	 * 将单个json object转换为商品图片
	 * 
	 * @param json
	 * @return ItemImg
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static ItemImg convertJsonToItemImg(JSONObject json) throws JSONException,TaobaoApiException {
		ItemImg img = new ItemImg();
		if (json.has(ApiConstants.ITEMIMG_ID)) {
			img.setItemimgId(json.getString(ApiConstants.ITEMIMG_ID));
		}
		if (json.has(ApiConstants.URL)) {
			img.setUrl(json.getString(ApiConstants.URL));
		}
		if (json.has(ApiConstants.POSITION)) {
			img.setPosition(json.getInt(ApiConstants.POSITION));
		}
		return img;
	}
	
	public static ItemSearch convertJsonToItemSearch(JSONObject json)throws JSONException,TaobaoApiException {
		ItemSearch  search =new ItemSearch();
		if (json.has(ApiConstants.ITEM_LISTS)) {
			search.setItemList(convertJsonArrayToItemList(json.getJSONArray(ApiConstants.ITEM_LISTS)));
		}
		if (json.has(ApiConstants.CATEGORY_LISTS)) {
			search.setCategoryList(convertJsonArrayToItemCategory(json.getJSONArray(ApiConstants.CATEGORY_LISTS)));
		}
		return search;
	}
	/**
	 * 将单个json object转换为商品搜索分类
	 * @author liupo
	 * @param json
	 * @return
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static ItemCategory convertJsonToItemCategory(JSONObject json)throws JSONException,TaobaoApiException {
		ItemCategory category=new ItemCategory();
		if(json.has(ApiConstants.CATEGORY_ID)){
			category.setCategoryId(json.getString(ApiConstants.CATEGORY_ID));
		}
		if(json.has(ApiConstants.COUNT)){
			category.setCount(json.getString(ApiConstants.COUNT));
		}
		return category;
	}
	/**
	 * 将返回的JsonArray转换为List<ItemCategory>
	 * @author liupo
	 * @param jsonArray
	 * @return
	 * @throws TaobaoApiException
	 */
	public static List<ItemCategory> convertJsonArrayToItemCategory(JSONArray jsonArray) throws TaobaoApiException {
		List<ItemCategory> categorys = new ArrayList<ItemCategory>();
		ItemCategory category = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				category = convertJsonToItemCategory(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			categorys.add(category);
		}
		return categorys;
	}
	/**
	 * 将返回的Json转换为ItemCat
	 * 
	 * @param json
	 *            JSONObject
	 * @return ItemCat
	 * @throws TaobaoApiException
	 */
	public static ItemCat convertJsonToItemCat(JSONObject json) throws JSONException {
		ItemCat newItemCat = new ItemCat();
		if (json.has(ApiConstants.CID)) {
			newItemCat.setCid(json.getString(ApiConstants.CID));
		}
		if (json.has(ApiConstants.IS_PARENT)) {
			newItemCat
					.setIsParent("true".equals(json.getString(ApiConstants.IS_PARENT)) ? true
							: false);
		}
		if (json.has(ApiConstants.NAME)) {
			newItemCat.setName(json.getString(ApiConstants.NAME));
		}
		if (json.has(ApiConstants.PARENT_CID)) {
			newItemCat.setParentCid(json.getString(ApiConstants.PARENT_CID));
		}
		if (json.has(ApiConstants.STATUS)) {
			newItemCat.setStatus(json.getString(ApiConstants.STATUS));
		}
		if (json.has(ApiConstants.SORT_ORDER)) {
			newItemCat.setSortOrder(json.getInt(ApiConstants.SORT_ORDER));
		}

		return newItemCat;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>ItemCat>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>ItemCat>
	 * @throws TaobaoApiException
	 */
	public static List<ItemCat> convertJsonArrayToItemCatList(JSONArray jsonArray) {
		List<ItemCat> getItemCatList = new ArrayList<ItemCat>();
		ItemCat itemCat = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			itemCat = new ItemCat();
			try {
				itemCat = convertJsonToItemCat(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getItemCatList.add(itemCat);
		}
		return getItemCatList;
	}
	
	public static List<Feature> convertJsonArrayToFeatureList(JSONArray jsonArray) {
		List<Feature> featureList = new ArrayList<Feature>();
		Feature feature = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			feature = new Feature();
			try {
				JSONObject json = jsonArray.getJSONObject(i);
				if (json.has(ApiConstants.ATTR_KEY)) {
					feature.setAttrKey(json.getString(ApiConstants.ATTR_KEY));
				}
				if (json.has(ApiConstants.ATTR_VALUE)) {
					feature.setAttrValue(json.getString(ApiConstants.ATTR_VALUE));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			featureList.add(feature);
		}
		return featureList;
	}
	
	/**
	 * 将返回的Json转换为ItemProp
	 * 
	 * @param json
	 *            JSONObject
	 * @return ItemProp
	 * @throws TaobaoApiException 
	 * @throws TaobaoApiException
	 */
	public static ItemProp convertJsonObjectToItemProp(JSONObject json) throws JSONException, TaobaoApiException {
		ItemProp itemProp = new ItemProp();
		if (json.has(ApiConstants.PID)) {
			itemProp.setPid(json.getString(ApiConstants.PID));
		}
		if (json.has(ApiConstants.NAME)) {
			itemProp.setName(json.getString(ApiConstants.NAME));
		}
		if (json.has(ApiConstants.PUB_MUST)) {
			itemProp.setPubMust("true".equals(json.getString(ApiConstants.PUB_MUST)) ? true : false);
		}
		if (json.has(ApiConstants.PUB_MULTI)) {
			itemProp.setPubMulti("true".equals(json.getString(ApiConstants.PUB_MULTI)) ? true : false);
		}
		if (json.has(ApiConstants.MUST)) {
			itemProp.setMust("true".equals(json.getString(ApiConstants.MUST)) ? true : false);
		}
		if (json.has(ApiConstants.MULTI)) {
			itemProp.setMulti("true".equals(json.getString(ApiConstants.MULTI)) ? true : false);
		}
		if (json.has(ApiConstants.IS_KEY_PROP)) {
			itemProp.setIsKeyProp("true".equals(json
					.getString(ApiConstants.IS_KEY_PROP)) ? true : false);
		}
		if (json.has(ApiConstants.IS_SALE_PROP)) {
			itemProp.setIsSaleProp("true".equals(json
					.getString(ApiConstants.IS_SALE_PROP)) ? true : false);
		}
		if (json.has(ApiConstants.IS_COLOR_PROP)) {
			itemProp.setIsColorProp("true".equals(json
					.getString(ApiConstants.IS_COLOR_PROP)) ? true : false);
		}
		if (json.has(ApiConstants.IS_ENUM_PROP)) {
			itemProp.setIsEnumProp("true".equals(json
					.getString(ApiConstants.IS_ENUM_PROP)) ? true : false);
		}
		if (json.has(ApiConstants.IS_INPUT_PROP)) {
			itemProp.setIsInputProp("true".equals(json
					.getString(ApiConstants.IS_INPUT_PROP)) ? true : false);
		}
		if (json.has(ApiConstants.IS_ITEM_PROP)) {
			itemProp.setIsItemProp("true".equals(json
					.getString(ApiConstants.IS_ITEM_PROP)) ?  true : false);
		}
		if (json.has(ApiConstants.CHILD_TEMPLATE)) {
			itemProp.setChildTemplate(json.getString(ApiConstants.CHILD_TEMPLATE));
		}
		if (json.has(ApiConstants.PARENT_PID)) {
			itemProp.setParentPid(json.getString(ApiConstants.PARENT_PID));
		}
		if (json.has(ApiConstants.PARENT_VID)) {
			itemProp.setParentVid(json.getString(ApiConstants.PARENT_VID));
		}
		if (json.has(ApiConstants.STATUS)) {
			itemProp.setStatus(json.getString(ApiConstants.STATUS));
		}
		if (json.has(ApiConstants.SORT)) {
			itemProp.setSortOrder(json.getInt(ApiConstants.SORT));
		}
		if (json.has(ApiConstants.IS_ALLOW_ALIAS)) {
			itemProp.setIsAllowAlias(json.getBoolean(ApiConstants.IS_ALLOW_ALIAS));
		}

		if (json.has(PropValue.TABLE_NAME)) {
			JSONArray propValues = json.getJSONArray(PropValue.TABLE_NAME);
			for (int i = 0; i < propValues.length(); i++) {
				JSONObject propValue = propValues.getJSONObject(i);
				itemProp.addPropValue(convertJsonToPropValue(propValue));
			}
		}

		return itemProp;
	}

	public static PropValue convertJsonToPropValue(JSONObject json) throws JSONException {
		PropValue propValue = new PropValue();

		if (json.has(PropValue.CID)) {
			propValue.setCid(json.getString(PropValue.CID));
		}

		if (json.has(PropValue.PID)) {
			propValue.setPid(json.getString(PropValue.PID));
		}

		if (json.has(PropValue.VID)) {
			propValue.setVid(json.getString(PropValue.VID));
		}

		if (json.has(PropValue.NAME)) {
			propValue.setName(json.getString(PropValue.NAME));
		}

		if (json.has(PropValue.PROP_NAME)) {
			propValue.setPropName(json.getString(PropValue.PROP_NAME));
		}

		if (json.has(PropValue.NAME_ALIAS)) {
			propValue.setNameAlias(json.getString(PropValue.NAME_ALIAS));
		}

		if (json.has(PropValue.IS_PARENT)) {
			propValue.setIsParent(json.getBoolean(PropValue.IS_PARENT));
		}

		if (json.has(PropValue.STATUS)) {
			propValue.setStatus(json.getString(PropValue.STATUS));
		}

		if (json.has(PropValue.SORT_ORDER)) {
			propValue.setSortOrder(json.getInt(PropValue.SORT_ORDER));
		}

		return propValue;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>ItemProp>
	 * 
	 * @param item_props
	 *            Json数组
	 * @return List<code><</code>ItemProp>
	 * @throws TaobaoApiException 
	 * @throws TaobaoApiException
	 */
	public static List<ItemProp> convertJSONArrayToItemPropList(JSONArray item_props) throws TaobaoApiException {
		List<ItemProp> itemProps = new ArrayList<ItemProp>();
		ItemProp itemProp = null;
		for (int i = 0; i < item_props.length(); i++) {
			try {
				itemProp = convertJsonObjectToItemProp(item_props.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			itemProps.add(itemProp);
		}
		return itemProps;
	}
	
}
