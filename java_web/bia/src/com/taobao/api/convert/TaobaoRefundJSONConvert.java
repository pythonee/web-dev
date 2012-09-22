/**
 * 
 */
package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.json.*;
import com.taobao.api.ApiConstants;
import com.taobao.api.model.Refund;
import com.taobao.api.model.RefundRemindTimeOut;
import com.taobao.api.util.DateUtil;

/**
 * @author sulinchong.pt 2009-2-19 上午10:42:50
 *
 */
public class TaobaoRefundJSONConvert {

	/**
	 * 将JSON对象转换为Refund
	 * @author sulinchong.pt 2009-2-19 上午10:43:25
	 * @param json
	 * @return
	 * @throws JSONException 
	 */
	public static Refund convertJsonToRefund(JSONObject json) throws JSONException {
		Refund newRefund = new Refund();
		if (json.has(ApiConstants.REFUND_ID)) {
			newRefund.setRefundId(json.getString(ApiConstants.REFUND_ID));
		}
		if (json.has(ApiConstants.ALIPAYNO)) {
			newRefund.setAlipayNo(json.getString(ApiConstants.ALIPAYNO));
		}
		if (json.has(ApiConstants.TID)) {
			newRefund.setTid(json.getString(ApiConstants.TID));
		}
		if (json.has(ApiConstants.SELLER_NICK)) {
			newRefund.setSellerNick(json.getString(ApiConstants.SELLER_NICK));
		}
		if (json.has(ApiConstants.BUYER_NICK)) {
			newRefund.setBuyerNick(json.getString(ApiConstants.BUYER_NICK));
		}
		if (json.has(ApiConstants.TOTAL_FEE)) {
			newRefund.setTotalFee(json.getString(ApiConstants.TOTAL_FEE));
		}
		if (json.has(ApiConstants.STATUS)) {
			newRefund.setStatus(json.getString(ApiConstants.STATUS));
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				newRefund.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (json.has(ApiConstants.REFUND_FEE)) {
			newRefund.setRefundFee(json.getString(ApiConstants.REFUND_FEE));
		}
		if (json.has(ApiConstants.GOOD_STATUS)) {
			newRefund.setGoodStatus(json.getString(ApiConstants.GOOD_STATUS));
		}
		if (json.has(ApiConstants.HAS_GOOD_RETURN)) {
			newRefund.setHasGoodReturn(Boolean.valueOf(json.getString(ApiConstants.HAS_GOOD_RETURN)));
		}
		if (json.has(ApiConstants.PAYMENT)) {
			newRefund.setPayment(json.getString(ApiConstants.PAYMENT));
		}
		if (json.has(ApiConstants.REASON)) {
			newRefund.setReason(json.getString(ApiConstants.REASON));
		}
		if (json.has(ApiConstants.DESC)) {
			newRefund.setDesc(json.getString(ApiConstants.DESC));
		}
		if (json.has(ApiConstants.IID)) {
			newRefund.setIid(json.getString(ApiConstants.IID));
		}
		if (json.has(ApiConstants.TITLE)) {
			newRefund.setTitle(json.getString(ApiConstants.TITLE));
		}
		if (json.has(ApiConstants.PRICE)) {
			newRefund.setPrice(json.getString(ApiConstants.PRICE));
		}
		if (json.has(ApiConstants.NUM)) {
			newRefund.setNum(Integer.parseInt(json.getString(ApiConstants.NUM)));
		}
		if (json.has(ApiConstants.GOOD_RETURN_TIME)) {
			try {
				newRefund.setGoodReturnTime(DateUtil.strToDate(json.getString(ApiConstants.GOOD_RETURN_TIME)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(json.has(ApiConstants.COMPANY_NAME)){
			newRefund.setCompanyName(json.getString(ApiConstants.COMPANY_NAME));
		}
		if(json.has(ApiConstants.SID)){
			newRefund.setSid(json.getString(ApiConstants.SID));
		}
		if(json.has(ApiConstants.ADDRESS)){
			newRefund.setAddress(json.getString(ApiConstants.ADDRESS));
		}
		if(json.has(ApiConstants.ITEM_PICT_URL)){
			newRefund.setItemPictUrl(json.getString(ApiConstants.ITEM_PICT_URL));
		}
		if(json.has(ApiConstants.REFUND_REMIND_TIMEOUT)){
			JSONObject jsonObject = json.getJSONObject(ApiConstants.REFUND_REMIND_TIMEOUT);
			RefundRemindTimeOut refundRemindTimeOut = new RefundRemindTimeOut();
			if(jsonObject.has(ApiConstants.REMIND_TYPE)){
				refundRemindTimeOut.setRemindType((Integer)jsonObject.getInt(ApiConstants.REMIND_TYPE));
			}
			if(jsonObject.has(ApiConstants.EXIST_TIMEOUT)){
				Boolean isExistItemOut = jsonObject.getBoolean(ApiConstants.EXIST_TIMEOUT);
				refundRemindTimeOut.setExistTimeout(isExistItemOut == null ? false : isExistItemOut.booleanValue());
			}
			if(jsonObject.has(ApiConstants.TIMEOUT)){
				try {
					refundRemindTimeOut.setTimeout(DateUtil.strToDate(jsonObject.getString(ApiConstants.TIMEOUT)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			newRefund.setRefundRemindTimeOut(refundRemindTimeOut);
		}
		
		if (json.has(ApiConstants.OID))
			newRefund.setOid(json.getString(ApiConstants.OID));
		
		if (json.has(ApiConstants.MODIFIED)) {
			try {
				newRefund.setModified(DateUtil.strToDate(json.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (json.has(ApiConstants.ORDER_STATUS)) {
			newRefund.setOrderStatus(json.getString(ApiConstants.ORDER_STATUS));
		}

		if (json.has(ApiConstants.SHIPPING_TYPE)) { // by fengsheng 2009-08-26
			newRefund.setShippingType(json.getString(ApiConstants.SHIPPING_TYPE));
		}

		return newRefund;
	}

	public static List<Refund> convertJsonArrayToRefundList(JSONArray jsonArray) {
		List<Refund> refundList = new ArrayList<Refund>();
		Refund getRefund = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getRefund = convertJsonToRefund(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			refundList.add(getRefund);
		}
		return refundList;
	}
}
