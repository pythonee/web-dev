//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ProductImgUploadRequest.java
// Author: liupo
// Date: 2008-12-23 下午02:00:43 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

import java.io.File;

/**
 * 调用 taobao.product.img.upload 需要传入的参数
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 */

public class ProductImgUploadRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -2541860788708248583L;
	private String picId;
	public static final String ID="id";
	public static final String PICID="pic_id";
	private String productId;
	public static final String PRODUCTID="product_id";
	private String major;
	public static final String MAJOR="major";
	private File image;
	private String position; // 图片序号
    public static final String POSITION="position";
    public static final String PRODUCTIMG="productImgs";
    
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
    public void setId(String id) {
		this.picId = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public ProductImgUploadRequest withPicId(String picId) {
		this.setPicId(picId);
		return this;
	}
	public ProductImgUploadRequest withProductId(String productId) {
		this.setProductId(productId);
		return this;
	}
	public ProductImgUploadRequest withPosition(String position) {
		this.setPosition(position);
		return this;
	}
	public ProductImgUploadRequest withMajor(String major) {
		this.setMajor(major);
		return this;
	}
	public ProductImgUploadRequest withImage(File image) {
		this.setImage(image);
		return this;
	}
}
