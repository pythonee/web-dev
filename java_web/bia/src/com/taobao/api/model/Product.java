//===================================================================================
// Copyright (c) 2008-2008 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: Product.java
// Author: liupo
// Date: 2008-12-17 下午03:40:56 
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
import java.util.List;

import com.taobao.api.convert.reader.ApiName;



/**
 * SPU
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class Product extends TaobaoModel{

	//
	private static final long serialVersionUID = -7686838180049028806L;
	private String productId; // 产品ID
	private String tsc;//商家编码;
	private String cid; // 商品类目ID，必须是叶子类目的
	private String catName; // 类目名称
	private String props; // 关键属性列表，格式：pid:vid;pid:vid
	private String propsStr; // 关键属性字符串列表
	private String name; // 产品名称
	private String binds; // 绑定属性列表，格式：pid:vid;pid:vid
	private String bindsStr; // 绑定属性字符串列表
	private String saleProps; // 销售属性列表，格式：pid:vid;pid:vid
	private String salePropsStr; // 销售属性字符串列表，格式同props_str
	private String price; // 产品的市场价
	private String desc;
	@ApiName("pic_url")
	private String picPath; // 主图片地址
	@ApiName("product_imgs")
	private List<ProductImg> productImg; // 子图片(目前最多支持4张)
	@ApiName("product_prop_imgs")
	private List<ProductPropImg> productPropImg; // 属性图片
	private Date created;
	private Date modified;
	private File image;
	private String outerId;//外部网店商品ID
	public String getOuterId() {
		return outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
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
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public String getPropsStr() {
		return propsStr;
	}
	public void setPropsStr(String propsStr) {
		this.propsStr = propsStr;
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
	public String getBindsStr() {
		return bindsStr;
	}
	public void setBindsStr(String bindsStr) {
		this.bindsStr = bindsStr;
	}
	public String getSaleProps() {
		return saleProps;
	}
	public void setSaleProps(String saleProps) {
		this.saleProps = saleProps;
	}
	public String getSalePropsStr() {
		return salePropsStr;
	}
	public void setSalePropsStr(String salePropsStr) {
		this.salePropsStr = salePropsStr;
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
	@Deprecated
	public String getPicPath() {
		return picPath;
	}
	public String getPicUrl() {
		return picPath;
	}
	@Deprecated
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public void setPicUrl(String picPath) {
		this.picPath = picPath;
	}
	@Deprecated
	public List<ProductImg> getProductImg() {
		return productImg;
	}
	public List<ProductImg> getProductImgs() {
		return productImg;
	}
	@Deprecated
	public void setProductImg(List<ProductImg> productImg) {
		this.productImg = productImg;
	}
	public void setProductImgs(List<ProductImg> productImg) {
		this.productImg = productImg;
	}
	@Deprecated
	public List<ProductPropImg> getProductPropImg() {
		return productPropImg;
	}
	public List<ProductPropImg> getProductPropImgs() {
		return productPropImg;
	}
	@Deprecated
	public void setProductPropImg(List<ProductPropImg> productPropImg) {
		this.productPropImg = productPropImg;
	}
	public void setProductPropImgs(List<ProductPropImg> productPropImg) {
		this.productPropImg = productPropImg;
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
	public String getTsc() {
		return tsc;
	}
	public void setTsc(String tsc) {
		this.tsc = tsc;
	}
	
	
}
