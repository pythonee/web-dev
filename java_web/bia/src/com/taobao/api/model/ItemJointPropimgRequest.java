//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemJointPropImgRequest.java
// Author: liupo
// Date: 2009-5-26 下午02:35:45 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

import com.taobao.api.model.TaobaoRequest;

/**
 * 
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ItemJointPropimgRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 4258755673816127868L;
	private String propImgId;     //属性图片id
	private String iid;			  //商品id
	private String properties;    //属性图片属串
	private String position;	  //属性图片位置
	private String url;           //图片URL
	
	@Deprecated
	public String getPropImgId() {
		return propImgId;
	}
	public String getId() {
		return propImgId;
	}
	
	@Deprecated
	public void setPropImgId(String propImgId) {
		this.propImgId = propImgId;
	}
	public void setId(String id) {
		this.propImgId = id;
	}
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
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
}
