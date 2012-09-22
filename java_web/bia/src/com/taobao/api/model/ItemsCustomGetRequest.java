//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemsOutGetRequest.java
// Author: liupo
// Date: 2009-2-16 上午10:49:20 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

/**
 * taobao.items.custom.get
 * 
 * @author jeck218@gmail.com 2009-9-24
 */
public class ItemsCustomGetRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = 907995036017254347L;
	private String outerId;
	private String fields;

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

}
