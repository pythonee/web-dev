//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ProductSearchRequest.java
// Author: liupo
// Date: 2008-12-23 下午02:51:47 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

/**
 * 调用 taobao.product.get 需要传入的参数
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ProductGetRequest extends TaobaoRequest{
    //
	private static final long serialVersionUID = -3514985380411045388L;
	private String productId;
    public static final String PRODUCTID="product_id";
    private String cid;
    public static final String CID="cid";
    private String props;
    public static final String PROPS="props";
    private String fields;
    public static final String FIELDS="fields";
    public static final String NAME="name";
    public static final String PRICE="price";
    public static final String PIC_PATH="pic_path";
    public static final String MODIFIED="modified";
    public static final String CAT_NAME="cat_name";
    public static final String PROPS_STR="props_str";
    public static final String BINDS="binds";
    public static final String BINDS_STR="binds_str";
    public static final String SALE_PROPS="sale_props";
    public static final String SALE_PROPS_STR="sale_props_str";
    public static final String DESC="desc";
    public static final String PRODUCT_IMG="product_img";
    public static final String PRODUCT_PROP_IMG="product_prop_img";
    public static final String PRODUCT="products";
    public static final String TSC="tsc";
    public static final String OUTER_ID="outer_id";
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	
	
	public ProductGetRequest withCid(String cid) {
		this.setCid(cid);
		return this;
	}
	public ProductGetRequest withProductId(String productId) {
		this.setProductId(productId);
		return this;
	}
	public ProductGetRequest withFields(String fields) {
		this.setFields(fields);
		return this;
	}
	public ProductGetRequest withProps(String props) {
		this.setProps(props);
		return this;
	}
}
