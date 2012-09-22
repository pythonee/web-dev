//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ProductAddResponse.java
// Author: liupo
// Date: 2008-12-17 下午04:50:26 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;


/**
 * 调用 taobao.product.img.delete,返回的Response
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 */

public class ProductImgDeleteResponse extends TaobaoResponse {

	//
	private static final long serialVersionUID = -4191646820882595439L;
	private ProductImg productImg;

	public ProductImgDeleteResponse() {
		super();
	}

	public ProductImgDeleteResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public ProductImg getProductImg() {
		return productImg;
	}

	public void setProductImg(ProductImg productImg) {
		this.productImg = productImg;
	}

	

	

}
