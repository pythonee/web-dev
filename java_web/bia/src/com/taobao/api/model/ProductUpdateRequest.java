//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ProductAddRequest.java
// Author: liupo
// Date: 2008-12-17 下午04:35:11 
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
 * 调用 taobao.product.update 修改SPU时 需要传入的参数
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 */

public class ProductUpdateRequest extends TaobaoRequest{
    //
	private static final long serialVersionUID = -6924365298046880970L;
	private String productId;
    public static final String PRODUCTID="product_id";
	private String name; // 产品名称
	public static final String NAME="name";
	private String binds; // 绑定属性列表，格式：pid:vid;pid:vid
	public static final String BINDS="binds";
	private String saleProps; // 销售属性列表，格式：pid:vid;pid:vid
	public static final String SALEPROPS="sale_props";
	private String price; // 产品的市场价
	public static final String PRICE="price";
	private String desc;
	public static final String DESC="desc";
	private File image;
    public static final String PRODUCT="products";
//    private String tsc;
//	public static final String TSC="tsc";
	private String outerId;
	public static final String OUTER_ID="outer_id";
	private String customerProps; //自定义关键属性
	public static final String CUSETOMER_PROPS="customer_props";

	public String getCustomerProps() {
		return customerProps;
	}

	public void setCustomerProps(String customerProps) {
		this.customerProps = customerProps;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBinds() {
		return binds;
	}

	public void setBinds(String binds) {
		this.binds = binds;
	}

	public String getSaleProps() {
		return saleProps;
	}

	public void setSaleProps(String saleProps) {
		this.saleProps = saleProps;
	}

	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}



//	public String getTsc() {
//		return tsc;
//	}
//
//	public void setTsc(String tsc) {
//		this.tsc = tsc;
//	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ProductUpdateRequest withName(String name) {
		this.setName(name);
		return this;
	}
	public ProductUpdateRequest withBinds(String binds) {
		this.setBinds(binds);
		return this;
	}
	public ProductUpdateRequest withSaleProps(String saleProps) {
		this.setSaleProps(saleProps);
		return this;
	}
	public ProductUpdateRequest withPrice(String price) {
		this.setPrice(price);
		return this;
	}
	public ProductUpdateRequest withDesc(String desc) {
		this.setDesc(desc);
		return this;
	}
	public ProductUpdateRequest withProductId(String productId) {
		this.setProductId(productId);
		return this;
	}
	public ProductUpdateRequest withImage(File image) {
		this.setImage(image);
		return this;
	}
	public ProductUpdateRequest withOuterId(String outerId) {
		this.setOuterId(outerId);
		return this;
	}
	public ProductUpdateRequest withCustomerProps(String customerProps) {
		this.setCustomerProps(customerProps);
		return this;
	}

}
