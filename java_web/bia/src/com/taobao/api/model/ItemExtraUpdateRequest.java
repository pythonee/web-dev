//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemExtraUpdateRequest.java
// Author: liupo
// Date: 2009-2-18 下午03:12:59 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

import java.util.Date;

/**
 * taobao.itemextra.update
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/
public class ItemExtraUpdateRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = 884021637067557453L;
	private String iid;
	private String title; // 标题
	private String desc; // 描述
	private String feature; // 自定义信息
	private String memo;
	private String type;
	private String skuProperties;
	private String skuQuantities;
	private String skuPrices;
	private String skuMemos;
	private String skuIds;
	private String skuExtraId;
	private String reservePrice;

	private Date listTime; // 上架时间
	private Date delistTime; // 下架时间
	private String approveStatus;
	private Long options;

	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	public Date getDelistTime() {
		return delistTime;
	}

	public void setDelistTime(Date delistTime) {
		this.delistTime = delistTime;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Long getOptions() {
		return options;
	}

	public void setOptions(Long options) {
		this.options = options;
	}

	public String getSkuExtraId() {
		return skuExtraId;
	}

	public void setSkuExtraId(String skuExtraId) {
		this.skuExtraId = skuExtraId;
	}

	public String getSkuMemos() {
		return skuMemos;
	}

	public void setSkuMemos(String skuMemos) {
		this.skuMemos = skuMemos;
	}

	public String getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(String reservePrice) {
		this.reservePrice = reservePrice;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSkuProperties() {
		return skuProperties;
	}

	public void setSkuProperties(String skuProperties) {
		this.skuProperties = skuProperties;
	}

	public String getSkuQuantities() {
		return skuQuantities;
	}

	public void setSkuQuantities(String skuQuantities) {
		this.skuQuantities = skuQuantities;
	}

	public String getSkuPrices() {
		return skuPrices;
	}

	public void setSkuPrices(String skuPrices) {
		this.skuPrices = skuPrices;
	}

	public ItemExtraUpdateRequest withSkuProperties(String skuProperties) {
		setSkuProperties(skuProperties);
		return this;
	}

	public ItemExtraUpdateRequest withSkuQuantities(String skuQuantities) {
		setSkuQuantities(skuQuantities);
		return this;
	}

	public ItemExtraUpdateRequest withSkuPrices(String skuPrices) {
		setSkuPrices(skuPrices);
		return this;
	}

	public ItemExtraUpdateRequest withIid(String iid) {
		this.setIid(iid);
		return this;
	}

	public ItemExtraUpdateRequest withTitle(String title) {
		this.setTitle(title);
		return this;
	}

	public ItemExtraUpdateRequest withDesc(String desc) {
		this.setDesc(desc);
		return this;
	}

	public ItemExtraUpdateRequest withType(String type) {
		this.setType(type);
		return this;
	}

	public ItemExtraUpdateRequest withMemo(String memo) {
		this.setMemo(memo);
		return this;
	}

	public ItemExtraUpdateRequest withFeature(String feature) {
		this.setFeature(feature);
		return this;
	}

	public ItemExtraUpdateRequest withReservePrice(String reservePrice) {
		this.setReservePrice(reservePrice);
		return this;
	}

	public ItemExtraUpdateRequest withSkuMemos(String skuMemos) {
		this.setSkuMemos(skuMemos);
		return this;
	}

	public ItemExtraUpdateRequest withSkuIds(String skuIds) {
		this.setSkuIds(skuIds);
		return this;
	}

	public ItemExtraUpdateRequest withSkuExtraIds(String skuExtraId) {
		this.setSkuExtraId(skuExtraId);
		return this;
	}

	public String getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(String skuIds) {
		this.skuIds = skuIds;
	}
}
