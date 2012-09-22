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

import java.util.List;

/**
 * 
 * 
 * @author jeck218@gmail.com 2009-11-6
 */
public class ItemExtrasGetResponse extends TaobaoListResponse {

	//
	private static final long serialVersionUID = 2498405538475162007L;

	private List<ItemExtra> itemExtras;

	public ItemExtrasGetResponse() {
		super();
	}

	public ItemExtrasGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<ItemExtra> getItemExtras() {
		return itemExtras;
	}

	public void setItemExtras(List<ItemExtra> itemExtras) {
		this.itemExtras = itemExtras;
	}

}
