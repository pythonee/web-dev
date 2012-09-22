package com.taobao.api.model;

/**
 * 卖家授权信息查询---请求参数
 * 
 * @author jeck.xie 2009-3-25
 */
public class ItemCatsAuthorizeGetRequest extends TaobaoRequest {

	//
	private static final long serialVersionUID = -1316742793088425839L;
	String fields;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

}
