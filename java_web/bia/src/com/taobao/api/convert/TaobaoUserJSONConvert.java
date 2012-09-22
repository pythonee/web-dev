package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Location;
import com.taobao.api.model.ShippingAddress;
import com.taobao.api.model.User;
import com.taobao.api.model.UserCredit;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoUserJSONConvert {
	
	/**
	 * 将返回的Json转换为User
	 * 
	 * @param json
	 *            JSONObject
	 * @return User
	 * @throws TaobaoApiException
	 */
	public static User convertJsonToUser(JSONObject json) throws JSONException {
		User getUser = new User();
		if (json.has(ApiConstants.USER_ID)) {
			getUser.setUserId(json.getString(ApiConstants.USER_ID));
		}
		if (json.has(ApiConstants.NICK)) {
			getUser.setNick(json.getString(ApiConstants.NICK));
		}
		if (json.has(ApiConstants.SEX)) {
			getUser.setSex(json.getString(ApiConstants.SEX));
		}
		if (json.has(ApiConstants.BUYER_CREDIT)) {
			UserCredit buyer_credit = new UserCredit();
			JSONObject tempJson = json.getJSONObject(ApiConstants.BUYER_CREDIT);
			if (tempJson.has(ApiConstants.LEVEL)) {
				buyer_credit.setLevel(Integer.parseInt(tempJson
						.getString(ApiConstants.LEVEL)));
			}
			if (tempJson.has(ApiConstants.SCORE)) {
				buyer_credit.setScore(Integer.parseInt(tempJson
						.getString(ApiConstants.SCORE)));
			}
			if (tempJson.has(ApiConstants.TOTAL_NUM)) {
				buyer_credit.setTotalNum(Integer.parseInt(tempJson
						.getString(ApiConstants.TOTAL_NUM)));
			}
			if (tempJson.has(ApiConstants.GOOD_NUM)) {
				buyer_credit.setGoodNum(Integer.parseInt(tempJson
						.getString(ApiConstants.GOOD_NUM)));
			}
			getUser.setBuyerCredit(buyer_credit);
		}
		if (json.has(ApiConstants.SELLER_CREDIT)) {
			UserCredit seller_credit = new UserCredit();
			JSONObject tempJson = json.getJSONObject(ApiConstants.SELLER_CREDIT);
			if (tempJson.has(ApiConstants.LEVEL)) {
				seller_credit.setLevel(Integer.parseInt(tempJson
						.getString(ApiConstants.LEVEL)));
			}
			if (tempJson.has(ApiConstants.SCORE)) {
				seller_credit.setScore(Integer.parseInt(tempJson
						.getString(ApiConstants.SCORE)));
			}
			if (tempJson.has(ApiConstants.TOTAL_NUM)) {
				seller_credit.setTotalNum(Integer.parseInt(tempJson
						.getString(ApiConstants.TOTAL_NUM)));
			}
			if (tempJson.has(ApiConstants.GOOD_NUM)) {
				seller_credit.setGoodNum(Integer.parseInt(tempJson
						.getString(ApiConstants.GOOD_NUM)));
			}
			getUser.setSellerCredit(seller_credit);
		}
		if (json.has(ApiConstants.LOCATION)) {
			Location location = new Location();
			JSONObject tempJson = json.getJSONObject(ApiConstants.LOCATION);
			if (tempJson.has(ApiConstants.CITY)) {
				location.setCity(tempJson.getString(ApiConstants.CITY));
			}
			if (tempJson.has(ApiConstants.STATE)) {
				location.setState(tempJson.getString(ApiConstants.STATE));
			}
			if (tempJson.has(ApiConstants.COUNTRY)) {
				location.setCountry(tempJson.getString(ApiConstants.COUNTRY));
			}
			if (tempJson.has(ApiConstants.ZIP)) {
				location.setZip(tempJson.getString(ApiConstants.ZIP));
			}
			if (tempJson.has(ApiConstants.ADDRESS)) {
				location.setAddress(tempJson.getString(ApiConstants.ADDRESS));
			}
			getUser.setLocation(location);
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				getUser.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ApiConstants.LAST_VISIT)) {
			try {
				getUser.setLastVisit(DateUtil.strToDate(json
						.getString(ApiConstants.LAST_VISIT)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ApiConstants.REAL_NAME)) {
			getUser.setRealName(json.getString(ApiConstants.REAL_NAME));
		}
		if (json.has(ApiConstants.ID_CARD)) {
			getUser.setIdCard(json.getString(ApiConstants.ID_CARD));
		}
		if (json.has(ApiConstants.PHONE)) {
			getUser.setPhone(json.getString(ApiConstants.PHONE));
		}
		if (json.has(ApiConstants.MOBILE)) {
			getUser.setMobile(json.getString(ApiConstants.MOBILE));
		}
		if (json.has(ApiConstants.EMAIL)) {
			getUser.setEmail(json.getString(ApiConstants.EMAIL));
		}
		if (json.has(ApiConstants.BIRTHDAY)) {
			try {
				getUser.setBirthday(DateUtil
						.strToDate(json.getString(ApiConstants.BIRTHDAY)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(json.has(ApiConstants.TYPE)){
			getUser.setType(json.getString(ApiConstants.TYPE));
		}
		if(json.has(ApiConstants.HAS_MORE_PIC)){
			getUser.setHasMorePic(json.getString(ApiConstants.HAS_MORE_PIC).equals("true") ? true : false);
		}
		if(json.has(ApiConstants.ITEMIMG_NUM)){
			getUser.setItemImgNum(Integer.valueOf(json.getString(ApiConstants.ITEMIMG_NUM)));
		}
		if(json.has(ApiConstants.ITEMIMG_SIZE)){
			getUser.setItemImgSize(Integer.valueOf(json.getString(ApiConstants.ITEMIMG_SIZE)));
		}
		if(json.has(ApiConstants.PROP_IMG_NUM)){
			getUser.setPropImgNum(Integer.valueOf(json.getString(ApiConstants.PROP_IMG_NUM)));
		}
		if(json.has(ApiConstants.PROP_IMG_SIZE)){
			getUser.setPropImgSize(Integer.valueOf(json.getString(ApiConstants.PROP_IMG_SIZE)));
		}
		if(json.has(ApiConstants.AUTO_REPOST)){
			getUser.setAutoRepost(json.getString(ApiConstants.AUTO_REPOST));
		}
		if(json.has(ApiConstants.USER_PROMOTEDTYPE)){
			getUser.setPromotedType(json.getString(ApiConstants.USER_PROMOTEDTYPE));
		}
		if(json.has(ApiConstants.USER_STATUS)){
			getUser.setStatus(json.getString(ApiConstants.USER_STATUS));
		}
		if(json.has(ApiConstants.USER_ALIPAYBAND)){
			getUser.setAlipayBind(json.getString(ApiConstants.USER_ALIPAYBAND));
		}
		if(json.has(ApiConstants.CONSUMER_PROTECTION)){
			getUser.setConsumerProtection(json.getString(ApiConstants.CONSUMER_PROTECTION));
		}
		if(json.has(ApiConstants.ALIPAY_ACCOUNT)){
			getUser.setAlipayAccount(json.getString(ApiConstants.ALIPAY_ACCOUNT));
		}
		if(json.has(ApiConstants.ALIPAYNO)){
			getUser.setAlipayNo(json.getString(ApiConstants.ALIPAYNO));
		}
		return getUser;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>User>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>User>
	 * @throws TaobaoApiException
	 */
	public static List<User> convertJsonArrayToUserList(JSONArray jsonArray) {
		List<User> getUserList = new ArrayList<User>();
		User getUser = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getUser = convertJsonToUser(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getUserList.add(getUser);
		}
		return getUserList;
	}
	
	public static ShippingAddress convertJsonToShippingAddress(JSONObject json) throws JSONException {
		ShippingAddress shippingAddress = new ShippingAddress();
		if(json.has(ApiConstants.ADDRESS_ID)){
			shippingAddress.setAddressId(Integer.valueOf(json.getString(ApiConstants.ADDRESS_ID)));
		}
		if(json.has(ApiConstants.RECEIVER_NAME)){
			shippingAddress.setReceiverName(json.getString(ApiConstants.RECEIVER_NAME));
		}
		if(json.has(ApiConstants.PHONE)){
			shippingAddress.setPhone(json.getString(ApiConstants.PHONE));
		}
		if(json.has(ApiConstants.MOBILE)){
			shippingAddress.setMobile(json.getString(ApiConstants.MOBILE));
		}
		if(json.has(ApiConstants.IS_DEFAULT)){
			shippingAddress.setDefault(json.getBoolean(ApiConstants.IS_DEFAULT));
		}
		/* --- delete by jeck 2009-04-15 

		if(json.has(ApiConstants.CREATED)){
			try {
				shippingAddress.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	 	
		--- */
		if(json.has(ApiConstants.LOCATION)){
			Location location = new Location();
			JSONObject tempJson = json.getJSONObject(ApiConstants.LOCATION);
			if(tempJson.has(ApiConstants.CITY)){
				location.setCity(tempJson.getString(ApiConstants.CITY));
			}
			if(tempJson.has(ApiConstants.DISTRICT)){
				location.setDistrict(tempJson.getString(ApiConstants.DISTRICT));
			}
			if(tempJson.has(ApiConstants.ZIP)){
				location.setZip(tempJson.getString(ApiConstants.ZIP));
			}
			if(tempJson.has(ApiConstants.ADDRESS)){
				location.setAddress(tempJson.getString(ApiConstants.ADDRESS));
			}
			shippingAddress.setLocation(location);
		}
		return shippingAddress;
	}
	
	public static List<ShippingAddress> convertJsonArrayToShippingAddressList(JSONArray jsonArray){
		List<ShippingAddress> getShipingAddressList = new ArrayList<ShippingAddress>();
		ShippingAddress shippingAddress = null;
		for(int i = 0; i<jsonArray.length(); i++){
			try {
				shippingAddress = convertJsonToShippingAddress(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getShipingAddressList.add(shippingAddress);
		}
		return getShipingAddressList;
	}
}
