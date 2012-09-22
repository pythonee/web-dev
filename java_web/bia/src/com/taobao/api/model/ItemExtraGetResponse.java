//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemExtraGetResponse.java
// Author: liupo
// Date: 2009-2-18 下午02:51:40 
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

public class ItemExtraGetResponse extends TaobaoResponse{

	//
	private static final long serialVersionUID = 456603978159595435L;
	@ApiName("item_extra")
	private ItemExtra itemextra;

	public ItemExtraGetResponse() {
		super();
	}

	public ItemExtraGetResponse(TaobaoResponse rsp) {
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
