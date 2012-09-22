//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ProductPropImgUploadRequest.java
// Author: liupo
// Date: 2008-12-23 下午02:18:52 
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
 * 调用 taobao.product.propImg.upload,请求参数
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ProductPropImgUploadRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 8417737427038982624L;
	private String picId;
	public static final String PICID="pic_id";
	public static final String ID="id";
	private String productId;
	public static final String PRODUCTID="product_id";
	private String props;
	public static final String PROPS="props";
	private File image;
	private String position; // 图片序号
    public static final String POSITION="position";
    public static final String PRODUCTPROPIMG="productPropImgs";
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
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public ProductPropImgUploadRequest withPicId(String picId) {
		this.setPicId(picId);
		return this;
	}
	public ProductPropImgUploadRequest withProductId(String productId) {
		this.setProductId(productId);
		return this;
	}
	public ProductPropImgUploadRequest withPosition(String position) {
		this.setPosition(position);
		return this;
	}
	public ProductPropImgUploadRequest withProps(String props) {
		this.setProps(props);
		return this;
	}
	public ProductPropImgUploadRequest withImage(File image) {
		this.setImage(image);
		return this;
	}
}
