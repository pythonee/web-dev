//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: SellerCatInsertRequest.java
// Author: liupo
// Date: 2009-1-19 下午07:10:53 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

/**
 * taobao.sellercat.update
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 */

public class SellerCatsListUpdateRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 6998964826695253446L;
	private String name;
	private String cid;
	private String sortOrder;
	private String pictUrl;
//	private String promotionInfo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SellerCatsListUpdateRequest withName(String name) {
		this.setName(name);
		return this;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getPictUrl() {
		return pictUrl;
	}

	public void setPictUrl(String pictUrl) {
		this.pictUrl = pictUrl;
	}

	public SellerCatsListUpdateRequest withCid(String cid) {
		this.setCid(cid);
		return this;
	}

	public SellerCatsListUpdateRequest withSortOrder(String sortOrder) {
		this.setSortOrder(sortOrder);
		return this;
	}

	public SellerCatsListUpdateRequest withPictUrl(String pictUrl) {
		this.setPictUrl(pictUrl);
		return this;

	}

//	public SellerCatsListUpdateRequest withPromotionInfo(String promotionInfo) {
//		this.setPromotionInfo(promotionInfo);
//		return this;
//	}
}
