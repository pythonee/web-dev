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

import java.util.List;

import com.taobao.api.convert.reader.ApiName;

/**
 * 调用 taobao.products.search 时,返回的Response
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 */

public class ProductSearchResponse extends TaobaoListResponse  {

	//
	private static final long serialVersionUID = -4191646820882595439L;
	@ApiName("products")
	private List<Product> product;

	public ProductSearchResponse() {
		super();
	}

	public ProductSearchResponse(TaobaoResponse rsp) {
		super(rsp);
	}
	@Deprecated
	public List<Product> getProduct() {
		return product;
	}
	public List<Product> getProducts() {
		return product;
	}

	@Deprecated
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public void setProducts(List<Product> product) {
		this.product = product;
	}

}
