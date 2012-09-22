//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: TaobaoItemExtraJSONConvert.java
// Author: liupo
// Date: 2009-2-19 上午11:49:43 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.convert;

import java.text.ParseException;

import com.taobao.api.json.*;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.ItemExtra;
import com.taobao.api.util.DateUtil;

/**
 * ITEMEXTRA转换
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class TaobaoItemExtraJSONConvert {
	/**
	 * 把JSON转换成ITEMEXTRA对象
	 * @author liupo
	 * @param json
	 * @return
	 * @throws JSONException
	 * @throws TaobaoApiException
	 * @throws ParseException 
	 */
	public static ItemExtra convertJsonToItemExtra(JSONObject json) throws JSONException,TaobaoApiException {
		ItemExtra itemExtra=new ItemExtra();
		if (json.has(ApiConstants.IID)) {
			itemExtra.setIid(json.getString(ApiConstants.IID));
		}
		if(json.has(ApiConstants.TITLE)){
			itemExtra.setTitle(json.getString(ApiConstants.TITLE));
		}
		if(json.has(ApiConstants.DESC)){
			itemExtra.setDesc(json.getString(ApiConstants.DESC));
		}
		if(json.has(ApiConstants.MEMO)){
			itemExtra.setMemo(json.getString(ApiConstants.MEMO));
		}
		if(json.has(ApiConstants.FEATURE)){
			itemExtra.setFeature(json.getString(ApiConstants.FEATURE));
		}
		if(json.has(ApiConstants.TYPE)){
			itemExtra.setType(json.getString(ApiConstants.TYPE));
		}
		if(json.has(ApiConstants.RESERVE_PRICE)){
			itemExtra.setReservePrice(json.getString(ApiConstants.RESERVE_PRICE));
		}
		if(json.has(ApiConstants.EID)){
			itemExtra.setEid(json.getString(ApiConstants.EID));
		}
		if (json.has(ApiConstants.SKU)) {
			itemExtra.setSkus(TaobaoSkuJSONConvert.convertJsonArrayToSkuList(json.getJSONArray(ApiConstants.SKU)));
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				itemExtra.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.MODIFIED)){
			try {
				itemExtra.setModified(DateUtil.strToDate(json.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		
		if (json.has(ApiConstants.OPTIONS)){
			itemExtra.setOptions(json.getLong(ApiConstants.OPTIONS));
		}
		if (json.has(ApiConstants.APPROVE_STATUS)){
			itemExtra.setApproveStatus(json.getString(ApiConstants.APPROVE_STATUS));
		}
		if (json.has(ApiConstants.LIST_TIME)){
			try {
				itemExtra.setListTime(DateUtil.strToDate(json.getString(ApiConstants.LIST_TIME)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.DELIST_TIME)){
			try {
				itemExtra.setDelistTime(DateUtil.strToDate(json.getString(ApiConstants.DELIST_TIME)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		
		return itemExtra;
	}
}
