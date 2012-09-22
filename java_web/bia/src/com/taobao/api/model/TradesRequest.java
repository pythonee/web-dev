package com.taobao.api.model;

import java.util.Date;

public abstract class TradesRequest extends TaobaoRequest {

	private static final long serialVersionUID = 6322049009712723442L;

	private String fields;
	private Date startCreated;
	private Date endCreated;
	private Integer pageNo;
	private Integer pageSize;
	private String title;
	private String status;
	private String type;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public Date getStartCreated() {
		return startCreated;
	}

	public void setStartCreated(Date startCreated) {
		this.startCreated = startCreated;
	}

	public Date getEndCreated() {
		return endCreated;
	}

	public void setEndCreated(Date endCreated) {
		this.endCreated = endCreated;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract TradesRequest withFields(String fields);
	public abstract TradesRequest withStartCreated(Date startCreated);
	public abstract TradesRequest withEndCreated(Date endCreated);
	public abstract TradesRequest withPageNo(Integer pageNo);
	public abstract TradesRequest withPageSize(Integer pageSize);
	public abstract TradesRequest withTitle(String title);
	public abstract TradesRequest withStatus(String status);
	public abstract TradesRequest withType(String type);
}
