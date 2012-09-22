//===================================================================================
// Copyright (c) 2008-2009 by www.TaoBao.com, All rights reserved.
//  391# wen'er road, HangZhou, China
// 
// This software is the confidential and proprietary information of 
// TaoBao.com, Inc. ("Confidential Information"). You shall not disclose 
// such Confidential Information and shall use it only in accordance 
// with the terms of the license agreement you entered into with TaoBao.com, Inc.
//===================================================================================
// File name: ItemExtraGetRequest.java
// Author: liupo
// Date: 2009-2-18 下午02:49:25 
// Description: 	 
// 		无
// Function List: 	 
// 		1. 无
// History: 
// 		1. 无
//===================================================================================

package com.taobao.api.model;

/**
 * 取单个扩展信息  taobao.itemextra.get
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 **/

public class ItemExtraGetRequest extends TaobaoRequest{
    //
	private static final long serialVersionUID = -2839527785628572972L;
	private String iid;
    private String fields;
    private String type;
    private String nick;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getFields() {
		return fields;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
    
	public ItemExtraGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}
	public ItemExtraGetRequest withIid(String iid) {
		this.setIid(iid);
		return this;
	}
	public ItemExtraGetRequest withType(String type) {
		this.setType(type);
		return this;
	}
	public ItemExtraGetRequest withNick(String nick) {
		this.setNick(nick);
		return this;
	}
}
