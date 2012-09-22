//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemJointPropimgResponse.java
// Author: liupo
// Date: 2009-5-26 下午02:37:40 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

import com.taobao.api.model.TaobaoResponse;

/**
 * 
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ItemJointPropimgResponse extends TaobaoResponse {
	//
	private static final long serialVersionUID = 8918913916608182863L;
	private PropImg propImg;

	public ItemJointPropimgResponse() {
		super();
	}

	public ItemJointPropimgResponse(TaobaoResponse response) {
		super(response);
	}

	public PropImg getPropImg() {
		return propImg;
	}

	public void setPropImg(PropImg propImg) {
		this.propImg = propImg;
	}
}
