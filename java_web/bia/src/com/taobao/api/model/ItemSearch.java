//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemSearch.java
// Author: liupo
// Date: 2009-3-19 上午10:10:50 
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
 * 商品搜索
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ItemSearch extends TaobaoModel {
	
	private static final long serialVersionUID = 1630427478637756328L;
	@ApiName("items")
	private List<Item> itemList;
	@ApiName("item_categories")
    private List<ItemCategory>  categoryList;
	
	@Deprecated
	public List<Item> getItemList() {
		return itemList;
	}
	public List<Item> getItems() {
		return itemList;
	}
	@Deprecated
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public void setItems(List<Item> itemList) {
		this.itemList = itemList;
	}
	@Deprecated
	public List<ItemCategory> getCategoryList() {
		return categoryList;
	}
	public List<ItemCategory> getItemCategories() {
		return categoryList;
	}
	@Deprecated
	public void setCategoryList(List<ItemCategory> categoryList) {
		this.categoryList = categoryList;
	}
	public void setItemCategories(List<ItemCategory> categoryList) {
		this.categoryList = categoryList;
	}
}
