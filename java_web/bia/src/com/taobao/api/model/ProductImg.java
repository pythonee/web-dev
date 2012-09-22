//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ProductImg.java
// Author: liupo
// Date: 2008-12-17 下午03:42:51 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

import java.io.File;
import java.util.Date;

import com.taobao.api.convert.reader.ApiName;

/**
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 */
public class ProductImg extends TaobaoModel {

	private static final long serialVersionUID = -798234045004263150L;
	private String productId; // 产品ID

	@ApiName("id")
	private String picId;
	private String url; // 图片地址
	private int position; // 图片序号
	private Date created;
	private Date modified;
	private File image;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@Deprecated
	public String getPicId() {
		return picId;
	}

	@Deprecated
	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getId() {
		return picId;
	}

	public void setId(String id) {
		this.picId = id;
	}
}
