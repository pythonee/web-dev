//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ProductPropImg.java
// Author: liupo
// Date: 2008-12-17 下午03:43:18 
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
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ProductPropImg extends TaobaoModel{

	//
	private static final long serialVersionUID = -5655899416065863432L;
	private String productId;
	
	@ApiName("id")
	private String picId;
	private String props; // 属性串(pid:vid)
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
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
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
	public String getId() {
		return picId;
	}
	@Deprecated
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public void setId(String picId) {
		this.picId = picId;
	}
}
