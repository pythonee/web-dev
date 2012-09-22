/**
 * 
 */
package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.RefundMessage;
import com.taobao.api.util.DateUtil;

/**
 * 退款留言JSON转对象。
 * 
 * @author tianchong
 */
public class TaobaoRefundMessageJSONConvert {
	
	/**
	 *  将JSON对象转换为RefundMessage
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static RefundMessage convertJsonToRefundMessage(JSONObject json) throws JSONException,TaobaoApiException {
		RefundMessage refundMessage = new RefundMessage();
		if (json.has(ApiConstants.MESSAGE_ID)) {
			refundMessage.setMessageId(json.getString(ApiConstants.MESSAGE_ID));
		}
		if (json.has(ApiConstants.MESSAGE_TYPE)) {
			refundMessage.setMessageType(json.getString(ApiConstants.MESSAGE_TYPE));
		}
		if (json.has(ApiConstants.REFUND_ID)) {
			refundMessage.setRefundId(json.getString(ApiConstants.REFUND_ID));
		}
		if (json.has(ApiConstants.OWNER_ID)) {
			refundMessage.setOwnerId(json.getString(ApiConstants.OWNER_ID));
		}
		if (json.has(ApiConstants.OWNER_NICK)) {
			refundMessage.setOwnerNick(json.getString(ApiConstants.OWNER_NICK));
		}
		if (json.has(ApiConstants.OWNER_ROLE)) {
			refundMessage.setOwnerRole(json.getString(ApiConstants.OWNER_ROLE));
		}
		if (json.has(ApiConstants.CONTENT)) {
			refundMessage.setContent(json.getString(ApiConstants.CONTENT));
		}
		if (json.has(ApiConstants.PICTURE_URLS)) {
			JSONArray jsonArray = json.getJSONArray(ApiConstants.PICTURE_URLS);
			List<String> pictureUrlList = new ArrayList<String>();
			String picUrl = null;
			JSONObject jsonObject = null;
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					jsonObject = jsonArray.getJSONObject(i);
					picUrl = jsonObject.getString(ApiConstants.REFUND_MESSAGE_PICTURE_URL);
				} catch (JSONException e) {
					throw new TaobaoApiException(e);
				}
				pictureUrlList.add(picUrl);
			}
			refundMessage.setPictureUrlList(pictureUrlList);
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				refundMessage.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return refundMessage;
	}
	
	public static List<RefundMessage> convertJsonArrayToRefundMessageList(JSONArray jsonArray) throws JSONException,TaobaoApiException{
		List<RefundMessage> refundMessageList = new ArrayList<RefundMessage>();
		RefundMessage getRefundMessage = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				getRefundMessage = convertJsonToRefundMessage(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			refundMessageList.add(getRefundMessage);
		}
		return refundMessageList;
	}
}
