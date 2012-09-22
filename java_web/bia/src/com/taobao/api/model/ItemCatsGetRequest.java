package com.taobao.api.model;

import java.util.Date;

/**
 * 商品类目请求封装。
 * 
 * @author fengsheng
 * @since 1.0, Sep 24, 2009
 */
public class ItemCatsGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = -391826285163134885L;

	private String fields;
	private String parentCid;
	private String cids;
	private Date datetime;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getParentCid() {
		return parentCid;
	}

	public void setParentCid(String parentCid) {
		this.parentCid = parentCid;
	}

	public String getCids() {
		return cids;
	}

	public void setCids(String cids) {
		this.cids = cids;
	}

	public Date getDatetime() {
		return datetime;
	}

	public ItemCatsGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public ItemCatsGetRequest withParentCid(String parentCid) {
		setParentCid(parentCid);
		return this;
	}

	public ItemCatsGetRequest withCids(String cids) {
		setCids(cids);
		return this;
	}

}
