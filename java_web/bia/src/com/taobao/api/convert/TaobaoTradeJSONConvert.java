package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.Order;
import com.taobao.api.model.Trade;
import com.taobao.api.model.TradeRate;
import com.taobao.api.util.DateUtil;

/**
 * @author gaoweibin.tw
 *
 */
public class TaobaoTradeJSONConvert {
	
	/**
	 * 将返回的Json转换为Trade
	 * 
	 * @param json
	 *            JSONObject
	 * @return Trade
	 * @throws TaobaoApiException
	 */
	public static Trade convertJsonToTrade(JSONObject json) throws JSONException {
		Trade newTrade = new Trade();
		if (json.has(ApiConstants.SELLER_NICK)) {
			newTrade.setSellerNick(json.getString(ApiConstants.SELLER_NICK));
		}
		if (json.has(ApiConstants.BUYER_NICK)) {
			newTrade.setBuyerNick(json.getString(ApiConstants.BUYER_NICK));
		}
		if (json.has(ApiConstants.IID)) {
			newTrade.setIid(json.getString(ApiConstants.IID));
		}
		if (json.has(ApiConstants.TITLE)) {
			newTrade.setTitle(json.getString(ApiConstants.TITLE));
		}
		if (json.has(ApiConstants.PRICE)) {
			newTrade.setPrice(json.getString(ApiConstants.PRICE));
		}
		if (json.has(ApiConstants.PIC_PATH)) {
			newTrade.setPicPath(json.getString(ApiConstants.PIC_PATH));
		}
		if (json.has(ApiConstants.NUM)) {
			newTrade.setNum(Integer.parseInt(json.getString(ApiConstants.NUM)));
		}
		if (json.has(ApiConstants.CREATED)) {
			String created = json.getString(ApiConstants.CREATED);
			if(created != null && created.length() != 0){
				try {
					newTrade.setCreated(DateUtil.strToDate(created));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		if (json.has(ApiConstants.TYPE)) {
			newTrade.setType(json.getString(ApiConstants.TYPE));
		}
		if (json.has(ApiConstants.TID)) {
			newTrade.setTid(json.getString(ApiConstants.TID));
		}
		if (json.has(ApiConstants.STATUS)) {
			newTrade.setStatus(json.getString(ApiConstants.STATUS));
		}
		if (json.has(ApiConstants.SELLER_RATE)) {
			newTrade.setSellerRate(json.getString(ApiConstants.SELLER_RATE).equals("true") ? true	: false);
		}
		if (json.has(ApiConstants.BUYER_RATE)) {
			newTrade
					.setBuyerRate(json.getString(ApiConstants.BUYER_RATE).equals("true") ? true
							: false);
		}
		if (json.has(ApiConstants.ALIPAYNO)) {
			newTrade.setAlipayNo(json.getString(ApiConstants.ALIPAYNO));
		}
		if (json.has(ApiConstants.PAYMENT)) {
			newTrade.setPayment(json.getString(ApiConstants.PAYMENT));
		}
		
		if(json.has(ApiConstants.BUYER_MESSAGE)){
			newTrade.setBuyerMessage(json.getString(ApiConstants.BUYER_MESSAGE));
		}
		if(json.has(ApiConstants.PAY_TIME)){
			String pay_time = json.getString(ApiConstants.PAY_TIME);
			if(pay_time != null && pay_time.length() != 0){
				try {
					newTrade.setPayTime(DateUtil.strToDate(pay_time));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
		if(json.has(ApiConstants.END_TIME)){
			String end_time = json.getString(ApiConstants.END_TIME);
			if(end_time != null && end_time.length() != 0){
				try {
					newTrade.setEndTime(DateUtil.strToDate(end_time));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
		if(json.has(ApiConstants.MODIFIED)){
			String modify = json.getString(ApiConstants.MODIFIED);
			if(modify != null && modify.length() != 0){
				try {
					newTrade.setModified(DateUtil.strToDate(modify));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		if(json.has(ApiConstants.POST_FEE)){
			newTrade.setPostFee(json.getString(ApiConstants.POST_FEE));
		}
		if(json.has(ApiConstants.BUYER_OBTAIN_POINT_FEE)){
			newTrade.setBuyerObtainPointFee(Integer.parseInt(json.getString(ApiConstants.BUYER_OBTAIN_POINT_FEE)));
		}
		if(json.has(ApiConstants.POINT_FEE)){
			newTrade.setPointFee(Integer.parseInt(json.getString(ApiConstants.POINT_FEE)));
		}
		if(json.has(ApiConstants.REAL_POINT_FEE)){
			newTrade.setRealPointFee(Integer.parseInt(json.getString(ApiConstants.REAL_POINT_FEE)));
		}
		if(json.has(ApiConstants.SID)){
			newTrade.setSid(json.getString(ApiConstants.SID));
		}
//		Delete "out_sid" by jeck 2009-06-22 JIRA: TAOBAOAPI-743
//		if(json.has(ApiConstants.OUT_SID)){
//			newTrade.setOutSid(json.getString(ApiConstants.OUT_SID));
//		}
		if(json.has(ApiConstants.SELLER_MEMO)){
			newTrade.setSellerMemo(json.getString(ApiConstants.SELLER_MEMO));
		}
		if(json.has(ApiConstants.BUYER_MEMO)){
			newTrade.setBuyerMemo(json.getString(ApiConstants.BUYER_MEMO));
		}
		if(json.has(ApiConstants.TOTAL_FEE)){
			newTrade.setTotalFee(json.getString(ApiConstants.TOTAL_FEE));
		}
		if(json.has(ApiConstants.ORDERS)){
			newTrade.setOrders(convertJsonArrayToOrderList(json.getJSONArray(ApiConstants.ORDERS)));
		}
		if(json.has(ApiConstants.CONSIGN_TIME)){
			String consign_time = json.getString(ApiConstants.CONSIGN_TIME);
			if(consign_time != null && consign_time.length() != 0){
				try {
					newTrade.setConsignTime(DateUtil.strToDate(consign_time));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(json.has(ApiConstants.BUYER_ALIPAY_NO)){
			newTrade.setBuyerAlipayNo(json.getString(ApiConstants.BUYER_ALIPAY_NO));
		}
		if(json.has(ApiConstants.RECEIVER_NAME)){
			newTrade.setReceiverName(json.getString(ApiConstants.RECEIVER_NAME));
		}
		
		if(json.has(ApiConstants.RECEIVER_STATE)){
			newTrade.setReceiverState(json.getString(ApiConstants.RECEIVER_STATE));
		}
		if(json.has(ApiConstants.RECEIVER_CITY)){
			newTrade.setReceiverCity(json.getString(ApiConstants.RECEIVER_CITY));
		}
		if(json.has(ApiConstants.RECEIVER_DISTRINCT)){
			newTrade.setReceiverDistrict(json.getString(ApiConstants.RECEIVER_DISTRINCT));
		}
		if(json.has(ApiConstants.RECEIVER_ADDRESS)){
			newTrade.setReceiverAddress(json.getString(ApiConstants.RECEIVER_ADDRESS));
		}
		if(json.has(ApiConstants.RECEIVER_ZIP)){
			newTrade.setReceiverZip(json.getString(ApiConstants.RECEIVER_ZIP));
		}
		if(json.has(ApiConstants.RECEIVER_MOBILE)){
			newTrade.setReceiverMobile(json.getString(ApiConstants.RECEIVER_MOBILE));
		}
		if(json.has(ApiConstants.RECEIVER_PHONE)){
			newTrade.setReceiverPhone(json.getString(ApiConstants.RECEIVER_PHONE));
		}
//		if(json.has(ApiConstants.CONSIGN_TIME)){
//			try {
//				newTrade.setConsignTime(DateUtil.strToDate(json.getString(ApiConstants.CONSIGN_TIME)));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
		if(json.has(ApiConstants.BUYER_EAMIL)){
			newTrade.setBuyerEmail(json.getString(ApiConstants.BUYER_EAMIL));
		}
		if(json.has(ApiConstants.COMMISSION_FEE)){
			newTrade.setCommissionFee(json.getString(ApiConstants.COMMISSION_FEE));
		}
		if(json.has(ApiConstants.SELLER_ALIPAY_NO)){
			newTrade.setSellerAlipayNo(json.getString(ApiConstants.SELLER_ALIPAY_NO));
		}
		if(json.has(ApiConstants.SELLER_MOBILE)){
			newTrade.setSellerMobile(json.getString(ApiConstants.SELLER_MOBILE));
		}
		if(json.has(ApiConstants.SELLER_PHONE)){
			newTrade.setSellerPhone(json.getString(ApiConstants.SELLER_PHONE));
		}
		if(json.has(ApiConstants.SELLER_NAME)){
			newTrade.setSellerName(json.getString(ApiConstants.SELLER_NAME));
		}
		if(json.has(ApiConstants.SELLER_EAMIL)){
			newTrade.setSellerEmail(json.getString(ApiConstants.SELLER_EAMIL));
		}
		if(json.has(ApiConstants.DISCOUNT_FEE)){
			newTrade.setDiscountFee(json.getString(ApiConstants.DISCOUNT_FEE));
		}
		if(json.has(ApiConstants.ADJUST_FEE)){
			newTrade.setAdjustFee(json.getString(ApiConstants.ADJUST_FEE));
		}
		if(json.has(ApiConstants.SNAPSHOT_URL)){
			newTrade.setSnapshotUrl(json.getString(ApiConstants.SNAPSHOT_URL));
		}
		if(json.has(ApiConstants.AVAILABLE_CONFIRM_FEE)){
			newTrade.setAvailableConfirmFee(json.getString(ApiConstants.AVAILABLE_CONFIRM_FEE));
		}
		if(json.has(ApiConstants.HAS_POST_FEE)){
			newTrade.setHasPostFee(Boolean.parseBoolean(json.getString(ApiConstants.HAS_POST_FEE)));
		}
		if(json.has(ApiConstants.TIMEOUT_ACTION_TIME)){
			try {
				newTrade.setTimeoutActionTime(DateUtil.strToDate(json.getString(ApiConstants.TIMEOUT_ACTION_TIME)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(json.has(ApiConstants.RECEIVED_PAYMENT)){
			newTrade.setReceivedPayment(json.getString(ApiConstants.RECEIVED_PAYMENT));
		}
//		if(json.has(ApiConstants.OUTER_SKUID_ID)){
//			newTrade.setOuter_sku_id(json.getString(ApiConstants.OUTER_SKUID_ID));
//		}
//		if(json.has(ApiConstants.OUTER_IID)){
//			newTrade.setOuter_iid(json.getString(ApiConstants.OUTER_IID));
//		}
		if (json.has(ApiConstants.SNAPSHOT)) {
			newTrade.setSnapshot(json.getString(ApiConstants.SNAPSHOT));
		}
		if (json.has(ApiConstants.SHIPPING_TYPE)) {
			newTrade.setShippingType(json.getString(ApiConstants.SHIPPING_TYPE));
		}
		if (json.has(ApiConstants.COD_FEE)) {
			newTrade.setCodFee(json.getString(ApiConstants.COD_FEE));
		}
		if (json.has(ApiConstants.COD_STATUS)) {
			newTrade.setCodStatus(json.getString(ApiConstants.COD_STATUS));
		}

		if (json.has(ApiConstants.TRADE_MEMO))
			newTrade.setTradeMemo(json.getString(ApiConstants.TRADE_MEMO));
		
		if (json.has(ApiConstants.IS_3D))
			newTrade.setIs3D(Boolean.parseBoolean(json.getString(ApiConstants.IS_3D)));
			
		return newTrade;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>TradeRate>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>TradeRate>
	 * @throws TaobaoApiException
	 */
	public static List<TradeRate> convertJsonArrayToTradeRateList(JSONArray jsonArray) {
		List<TradeRate> getTradeRateList = new ArrayList<TradeRate>();
		TradeRate getTradeRate = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getTradeRate = convertJsonToTradeRate(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getTradeRateList.add(getTradeRate);
		}
		return getTradeRateList;
	}
	
	/**
	 * 将返回的Json转换为TradeRate
	 * 
	 * @param json
	 *            JSONObject
	 * @return TradeRate
	 * @throws TaobaoApiException
	 */
	public static TradeRate convertJsonToTradeRate(JSONObject json) throws JSONException {
		TradeRate getTradeRate = new TradeRate();
		if (json.has(ApiConstants.TID)) {
			getTradeRate.setTid(json.getString(ApiConstants.TID));
		}
		if (json.has(ApiConstants.ORDER_ID)) {
			getTradeRate.setOrderId(json.getString(ApiConstants.ORDER_ID));
		}
		if (json.has(ApiConstants.ROLE)) {
			getTradeRate.setRole(json.getString(ApiConstants.ROLE));
		}
		if (json.has(ApiConstants.RATED_NICK)) {
			getTradeRate.setRatedNick(json.getString(ApiConstants.RATED_NICK));
		}
		if (json.has(ApiConstants.NICK)) {
			getTradeRate.setNick(json.getString(ApiConstants.NICK));
		}
		if (json.has(ApiConstants.RESULT)) {
			getTradeRate.setResult(json.getString(ApiConstants.RESULT));
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				getTradeRate.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ApiConstants.ITEM_TITLE)) {
			getTradeRate.setItemTitle(json.getString(ApiConstants.ITEM_TITLE));
		}
		if (json.has(ApiConstants.ITEM_PRICE)) {
			getTradeRate.setItemPrice(json.getString(ApiConstants.ITEM_PRICE));
		}
		if (json.has(ApiConstants.CONTENT)) {
			getTradeRate.setContent(json.getString(ApiConstants.CONTENT));
		}
		if (json.has(ApiConstants.REPLY)) {
			getTradeRate.setReply(json.getString(ApiConstants.REPLY));
		}
		return getTradeRate;
	}
	
	/**
	 * 将返回的JsonArray转换为List<code><</code>Trade>
	 * 
	 * @param jsonArray
	 *            Json数组
	 * @return List<code><</code>Trade>
	 * @throws TaobaoApiException
	 */
	public static List<Trade> convertJsonArrayToTradeList(JSONArray jsonArray) {
		List<Trade> getTradesList = new ArrayList<Trade>();
		Trade getTrade = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getTrade = convertJsonToTrade(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getTradesList.add(getTrade);
		}
		return getTradesList;
	}
	
	public static List<Order> convertJsonArrayToOrderList(JSONArray jsonArray){
		List<Order> getOrderList = new ArrayList<Order>();
		Order getOrder = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getOrder = convertJsonToOrder(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getOrderList.add(getOrder);
		}
		return getOrderList;
	}
	
	public static Order convertJsonToOrder(JSONObject json) throws JSONException{
		Order order = new Order();
		if(json.has(ApiConstants.SELLER_NICK)){
			order.setSellerNick(json.getString(ApiConstants.SELLER_NICK));
		}
		if(json.has(ApiConstants.BUYER_NICK)){
			order.setBuyerNick(json.getString(ApiConstants.BUYER_NICK));
		}
		if(json.has(ApiConstants.TYPE)){
			order.setType(json.getString(ApiConstants.TYPE));
		}
		if(json.has(ApiConstants.CREATED)){
			try {
				order.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(json.has(ApiConstants.MODIFIED)){
			try {
				order.setModified(DateUtil.strToDate(json.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(json.has(ApiConstants.IID)){
			order.setIid(json.getString(ApiConstants.IID));
		}
		if(json.has(ApiConstants.SKU_ID)){
			order.setSkuId(json.getString(ApiConstants.SKU_ID));
		}
		if(json.has(ApiConstants.NUM)){
			order.setNum(Integer.parseInt(json.getString(ApiConstants.NUM)));
		}
		if(json.has(ApiConstants.TITLE)){
			order.setTitle(json.getString(ApiConstants.TITLE));
		}
		if(json.has(ApiConstants.PIC_PATH)){
			order.setPicPath(json.getString(ApiConstants.PIC_PATH));
		}
		if(json.has(ApiConstants.PRICE)){
			order.setPrice(json.getString(ApiConstants.PRICE));
		}
		if(json.has(ApiConstants.TID)){
			order.setTid(json.getString(ApiConstants.TID));
		}
		if(json.has(ApiConstants.REFUND_STATUS)){
			order.setRefundStatus(json.getString(ApiConstants.REFUND_STATUS));
		}
		if(json.has(ApiConstants.OUTERIID)){
			order.setOuterIid(json.getString(ApiConstants.OUTERIID));
		}
		if(json.has(ApiConstants.OUTERSKUIDID)){
			order.setOuterSkuId(json.getString(ApiConstants.OUTERSKUIDID));
		}
		if(json.has(ApiConstants.TOTAL_FEE)){
			order.setTotalFee(json.getString(ApiConstants.TOTAL_FEE));
		}
		if(json.has(ApiConstants.PAYMENT)){
			order.setPayment(json.getString(ApiConstants.PAYMENT));
		}
		if(json.has(ApiConstants.DISCOUNT_FEE)){
			order.setDiscountFee(json.getString(ApiConstants.DISCOUNT_FEE));
		}
		if(json.has(ApiConstants.ADJUST_FEE)){
			order.setAdjustFee(json.getString(ApiConstants.ADJUST_FEE));
		}
		if(json.has(ApiConstants.STATUS)){
			order.setStatus(json.getString(ApiConstants.STATUS));
		}
		if(json.has(ApiConstants.SNAPSHOT_URL)){
			order.setSnapshotUrl(json.getString(ApiConstants.SNAPSHOT_URL));
		}
		if(json.has(ApiConstants.TIMEOUT_ACTION_TIME)){
			try {
				order.setTimeoutActionTime(DateUtil.strToDate(json.getString(ApiConstants.TIMEOUT_ACTION_TIME)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ApiConstants.SNAPSHOT)) {
			order.setSnapshot(json.getString(ApiConstants.SNAPSHOT));
		}
		
		/* ------------ add jeck 2009-06-26 TAOBAOAPI-756 ------------ */
		if (json.has(ApiConstants.SKU_PROPERTIES_NAME)) {
			order.setSkuPropertiesName(json.getString(ApiConstants.SKU_PROPERTIES_NAME));
		}
		if (json.has(ApiConstants.ITEM_MEAL_NAME)) {
			order.setItemMealName(json.getString(ApiConstants.ITEM_MEAL_NAME));
		}
		/* ------------ end ------------ */
		
		if (json.has(ApiConstants.ITEM_MEMO)) {// add by jeck 2009-08-27
			order.setItemMemo(json.getString(ApiConstants.ITEM_MEMO));
		}
		
		return order;
	}

	public static List<Order> convertJsonToOrderList(JSONArray orders) {
		List<Order> getOrdersList = new ArrayList<Order>();
		Order getOrder = null;
		for (int i = 0; i < orders.length(); i++) {
			try {
				getOrder = convertJsonToOrder(orders.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			getOrdersList.add(getOrder);
		}
		return getOrdersList;
	}
}
