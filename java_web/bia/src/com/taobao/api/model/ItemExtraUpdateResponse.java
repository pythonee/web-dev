//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemExtraUpdateResponse.java
// Author: liupo
// Date: 2009-2-18 下午03:15:35 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * 
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ItemExtraUpdateResponse extends TaobaoResponse{

	//
	private static final long serialVersionUID = 310823722326324677L;
	@ApiName("item_extra")
	private ItemExtra itemextra;

	public ItemExtraUpdateResponse() {
		super();
	}

	public ItemExtraUpdateResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	@Deprecated
	public ItemExtra getItemextra() {
		return itemextra;
	}
	public ItemExtra getItemExtra() {
		return itemextra;
	}

	@Deprecated
	public void setItemextra(ItemExtra itemextra) {
		this.itemextra = itemextra;
	}
	public void setItemExtra(ItemExtra itemextra) {
		this.itemextra = itemextra;
	}
}
