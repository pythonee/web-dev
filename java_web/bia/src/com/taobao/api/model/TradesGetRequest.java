/**
 * 
 */
package com.taobao.api.model;

/**
 * taobao.trades.get
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradesGetRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = 2230671887730741879L;

	private String iid;
	private String fields;
	private String sellerNick;
	private Integer pageNo;
	private Integer pageSize;
	private String type;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TradesGetRequest withIid(String iid) {
		setIid(iid);
		return this;
	}

	public TradesGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public TradesGetRequest withSellerNick(String sellerNick) {
		setSellerNick(sellerNick);
		return this;
	}

	public TradesGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public TradesGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	public TradesGetRequest withType(String type) {
		setType(type);
		return this;
	}

}
