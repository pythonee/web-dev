package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;
import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Product;
import com.taobao.api.model.ProductGetRequest;
import com.taobao.api.model.ProductImg;
import com.taobao.api.model.ProductPropImg;
import com.taobao.api.model.PropImg;
import com.taobao.api.model.PropValue;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoProductJSONConvert {
	
	/**
	 * 取到商品时将返回的json转换为product对象
	 * 
	 * @param json
	 *            JSONObject
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static Product convertJsonToProduct(JSONObject json) throws JSONException,TaobaoApiException {
		Product product = new Product();
		if (json.has(ApiConstants.PRODUCT_ID)) {
			product.setProductId(json.getString(ApiConstants.PRODUCT_ID));
		}
		if (json.has(ProductGetRequest.NAME)) {
			product.setName(json.getString(ProductGetRequest.NAME));
		}
		if (json.has(ProductGetRequest.CID)) {
			product.setCid(json.getString(ProductGetRequest.CID));
		}
		if (json.has(ProductGetRequest.PRICE)) {
			product.setPrice(json.getString(ProductGetRequest.PRICE));
		}
		if (json.has(ProductGetRequest.PROPS)) {
			product.setProps(json.getString(ProductGetRequest.PROPS));
		}
		if (json.has(ProductGetRequest.PIC_PATH)) {
			product.setPicPath(json.getString(ProductGetRequest.PIC_PATH));
		}
		if (json.has(ProductGetRequest.MODIFIED)) {
			try {
				product.setModified(DateUtil.strToDate(json.getString(ProductGetRequest.MODIFIED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(json.has(ApiConstants.CREATED)){
			try {
				product.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ProductGetRequest.CAT_NAME)) {
			product.setCatName(json.getString(ProductGetRequest.CAT_NAME));
		}
		if (json.has(ProductGetRequest.PROPS_STR)) {
			product.setPropsStr(json.getString(ProductGetRequest.PROPS_STR));
		}
		if (json.has(ProductGetRequest.BINDS)) {
			product.setBinds(json.getString(ProductGetRequest.BINDS));
		}
		if (json.has(ProductGetRequest.BINDS_STR)) {
			product.setBindsStr(json.getString(ProductGetRequest.BINDS_STR));
		}
		if (json.has(ProductGetRequest.SALE_PROPS)) {
			product.setSaleProps(json.getString(ProductGetRequest.SALE_PROPS));
		}
		if (json.has(ProductGetRequest.SALE_PROPS_STR)) {
			product.setSalePropsStr(json.getString(ProductGetRequest.SALE_PROPS_STR));
		}
		if (json.has(ProductGetRequest.DESC)) {
			product.setDesc(json.getString(ProductGetRequest.DESC));
		}
		if (json.has(ProductGetRequest.PRODUCT_IMG)) {
			product.setProductImg(convertJsonArrayToProductImgList(json.getJSONArray(ProductGetRequest.PRODUCT_IMG)));
		}
		if (json.has(ProductGetRequest.PRODUCT_PROP_IMG)) {
			product.setProductPropImg(convertJsonArrayToProductPropImgList(json.getJSONArray(ProductGetRequest.PRODUCT_PROP_IMG)));
		}
		if (json.has(ProductGetRequest.TSC)) {
			product.setTsc(json.getString(ProductGetRequest.TSC));
		}
		if (json.has(ProductGetRequest.OUTER_ID)) {
			product.setOuterId(json.getString(ProductGetRequest.OUTER_ID));
		}
		return product;
	}
	
	/**
	 * 将返回的JsonArray转换为List<ProductImg>
	 * @param jsonArray
	 * @return List<ProductImg>
	 * @throws TaobaoApiException
	 */
	public static List<ProductImg> convertJsonArrayToProductImgList(JSONArray jsonArray) throws TaobaoApiException {
		List<ProductImg> productImgs = new ArrayList<ProductImg>();
		ProductImg img = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				img = convertJsonToProductImg(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			productImgs.add(img);
		}
		return productImgs;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>Product>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>Item>
	 * @throws TaobaoApiException
	 */
	public static List<Product> convertJsonArrayToProductList(JSONArray jsonArray) throws TaobaoApiException {
		List<Product> getProductList = new ArrayList<Product>();
		Product getProduct = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getProduct = convertJsonToProduct(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			getProductList.add(getProduct);
		}
		return getProductList;
	}
	
	/** 将单个Json Object转换为ProductImg对象
	 * @param json
	 * @return ProductImg
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static ProductImg convertJsonToProductImg(JSONObject json) throws JSONException, TaobaoApiException {
		ProductImg img = new ProductImg();
		if (json.has(ApiConstants.PICID)) {
			img.setPicId(json.getString(ApiConstants.PICID));
		}
		if (json.has(ApiConstants.URL)) {
			img.setUrl(json.getString(ApiConstants.URL));
		}
		if (json.has(ApiConstants.POSITION)) {
			img.setPosition(json.getInt(ApiConstants.POSITION));
		}
		return img;
	}
	
	/**
	 * 将返回的JSonArray转换为List<ProductPropImg> 
	 * @param jsonArray
	 * @return List<ProductPropImg>
	 * @throws TaobaoApiException
	 */
	public static List<ProductPropImg> convertJsonArrayToProductPropImgList(JSONArray jsonArray) throws TaobaoApiException {
		List<ProductPropImg> productPropImgs = new ArrayList<ProductPropImg>();
		ProductPropImg img = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				img = convertJsonToProductPropImg(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			productPropImgs.add(img);
		}
		return productPropImgs;
	}
	
	/**
	 * 将返回的JsonArray转换为List<PropImg>
	 * 
	 * @param jsonArray
	 * @return List<PropImg>
	 * @throws TaobaoApiException
	 */
	public static List<PropImg> convertJsonArrayToPropImgList(JSONArray jsonArray) throws TaobaoApiException {
		List<PropImg> propImgs = new ArrayList<PropImg>();
		PropImg img = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				img = convertJsonToPropImg(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			propImgs.add(img);
		}
		return propImgs;
	}
	
	/**
	 * 将单个json object转换为商品属性图片
	 * 
	 * @param json
	 * @return PropImg
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static PropImg convertJsonToPropImg(JSONObject json) throws JSONException,
			TaobaoApiException {
		PropImg img = new PropImg();
		if (json.has(ApiConstants.PROPIMG_ID)) {
			img.setPropimgId(json.getString(ApiConstants.PROPIMG_ID));
		}
		if (json.has(ApiConstants.URL)) {
			img.setUrl(json.getString(ApiConstants.URL));
		}
		if (json.has(ApiConstants.POSITION)) {
			img.setPosition(json.getInt(ApiConstants.POSITION));
		}
		if (json.has(ApiConstants.PROPERTIES)) {
			img.setProperties(json.getString(ApiConstants.PROPERTIES));
		}
		return img;
	}
	
	/**
	 * 单个Json Object转换为ProductProImg对象
	 * @param json
	 * @return ProductProImg
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static ProductPropImg convertJsonToProductPropImg(JSONObject json) throws JSONException, TaobaoApiException {
		ProductPropImg img = new ProductPropImg();
		if (json.has(ApiConstants.PICID)) {
			img.setPicId(json.getString(ApiConstants.PICID));
		}
		if (json.has(ApiConstants.URL)) {
			img.setUrl(json.getString(ApiConstants.URL));
		}
		if (json.has(ApiConstants.POSITION)) {
			img.setPosition(json.getInt(ApiConstants.POSITION));
		}
		if (json.has(ApiConstants.PROPS)) {
			img.setProps(json.getString(ApiConstants.PROPS));
		}
		return img;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>PropValue>
	 * 
	 * @param propValues
	 *            Json数组
	 * @return List<code><</code>PropValue>
	 * @throws TaobaoApiException
	 */
	public static List<PropValue> convertJsonArrayToPropValueList(JSONArray propValues) {
		List<PropValue> propValueList = new ArrayList<PropValue>();
		PropValue propValue = null;
		for (int i = 0; i < propValues.length(); i++) {
			try {
				propValue = convertJsonToPropValue(propValues.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			propValueList.add(propValue);
		}
		return propValueList;
	}
	
	/**
	 * 将返回的Json转换为SellerCat
	 * 
	 * @param json
	 * @return PropValue
	 * @return SellerCat
	 * @throws TaobaoApiException
	 */
	public static PropValue convertJsonToPropValue(JSONObject json) throws JSONException {
		PropValue getPropValue = new PropValue();
		if (json.has(ApiConstants.VID)) {
			getPropValue.setVid(json.getString(ApiConstants.VID));
		}
		if (json.has(ApiConstants.NAME)) {
			getPropValue.setName(json.getString(ApiConstants.NAME));
		}
		if (json.has(ApiConstants.BINDS)) {
			getPropValue.setBinds(json.getString(ApiConstants.BINDS));
		}
		if (json.has(ApiConstants.IS_PARENT)) {
			getPropValue
					.setIsParent("true".equals(json.getString(ApiConstants.IS_PARENT)) ? true
							: false);
		}

		if (json.has(ApiConstants.CID)) {
			getPropValue.setCid(json.getString(ApiConstants.CID));
		}
		if (json.has(ApiConstants.PID)) {
			getPropValue.setPid(json.getString(ApiConstants.PID));
		}
		if (json.has(ApiConstants.PROP_NAME)) {
			getPropValue.setPropName(json.getString(ApiConstants.PROP_NAME));
		}
		if (json.has(ApiConstants.STATUS)) {
			getPropValue.setStatus(json.getString(ApiConstants.STATUS));
		}
		if (json.has(ApiConstants.SORT)) {
			getPropValue.setSortOrder(json.getInt(ApiConstants.SORT));
		}
		if (json.has(ApiConstants.NAME_ALIAS)) {
			getPropValue.setNameAlias(json.getString(ApiConstants.NAME_ALIAS));
		}
		
		return getPropValue;
	}
	
}
