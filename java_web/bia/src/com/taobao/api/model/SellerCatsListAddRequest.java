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
 * taobao.sellercat.insert
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class SellerCatsListAddRequest extends TaobaoRequest{
    //
	private static final long serialVersionUID = 11299691134166064L;
	private String name;
    private String parentCid;
    private String sortOrder;
    private String pictUrl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public SellerCatsListAddRequest withName(String name){
		this.setName(name);
		return this;
	}
	
	public String getParentCid() {
		return parentCid;
	}
	public void setParentCid(String parentCid) {
		this.parentCid = parentCid;
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
	public SellerCatsListAddRequest withParentCid(String parentCid){
		this.setParentCid(parentCid);
		return this;
	}
	public SellerCatsListAddRequest withSortOrder(String sortOrder){
		this.setSortOrder(sortOrder);
		return this;
	}
	public SellerCatsListAddRequest withPictUrl(String pictUrl){
		this.setPictUrl(pictUrl);
		return this;
		
	}
}
