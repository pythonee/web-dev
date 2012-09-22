//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemJointImgRequest.java
// Author: liupo
// Date: 2009-5-26 下午02:33:11 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

import java.io.File;

import com.taobao.api.model.TaobaoRequest;

/**
 * 
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ItemJointImgRequest extends TaobaoRequest{

	//
	private static final long serialVersionUID = 2709825623101264437L;
	private String itemImgId;   //商品图片id 
	private String iid;			//商品id
	private String position;	//商品图片位置
	private boolean isMajor;	//是否将该图片设为主图
	private String url;         //图片URL
	
	@Deprecated
	public String getUrl() {
		return url;
	}
	public String getPicPath() {
		return url;
	}
	
	@Deprecated
	public void setUrl(String url) {
		this.url = url;
	}
	public void setPicPath(String picPath) {
		this.url = picPath;
	}
	
	@Deprecated
	public String getItemImgId() {
		return itemImgId;
	}
	public String getId() {
		return itemImgId;
	}
	
	@Deprecated
	public void setItemImgId(String itemImgId) {
		this.itemImgId = itemImgId;
	}
	public void setId(String id) {
		this.itemImgId = id;
	}
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isMajor() {
		return isMajor;
	}
	public void setMajor(boolean isMajor) {
		this.isMajor = isMajor;
	}
}
