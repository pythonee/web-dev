package com.taobao.api.model;

/**
 * 退款请求
 * 
 * @author fengsheng
 * @since 1.0, Sep 29, 2009
 */
public abstract class RefundsRequest extends TaobaoRequest {

	private static final long serialVersionUID = -4532188786201273339L;

	private String status;
	private Integer pageNo;
	private Integer pageSize;
	private String fields;
	private String type;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public abstract RefundsRequest withFields(String fields);
	public abstract RefundsRequest withPageNo(Integer pageNo);
	public abstract RefundsRequest withPageSize(Integer pageSize);
	public abstract RefundsRequest withStatus(String status);
	public abstract RefundsRequest withType(String type);
}
