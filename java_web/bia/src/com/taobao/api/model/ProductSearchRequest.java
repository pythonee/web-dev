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
 * 调用 taobao.products.search 需要传入的参数
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ProductSearchRequest extends TaobaoRequest{
    //
	private static final long serialVersionUID = -1318834832912859775L;
	private String q;
    public static final String Q="q";
    private String cid;
    public static final String CID="cid";
    private String props;
    public static final String PROPS="props";
    private String fields;
	private Integer pageNo;
	private Integer pageSize;
    public static final String FIELDS="fields";
    public static final String PRODUCTS="products";
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
	public ProductSearchRequest withQ(String q) {
		this.setQ(q);
		return this;
	}
	public ProductSearchRequest withCid(String cid) {
		this.setCid(cid);
		return this;
	}
	public ProductSearchRequest withFields(String fields) {
		this.setFields(fields);
		return this;
	}
	public ProductSearchRequest withProps(String props) {
		this.setProps(props);
		return this;
	}
	public ProductSearchRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public ProductSearchRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}
}
