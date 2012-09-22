package com.taobao.api.model;

import java.util.Date;

/**
 * 卖家售出交易查询的新接口（走TC的备库查询）
 * 
 * taobao.trades.sold.increment.get
 * 
 * @author jeck.xie 2009-3-25
 */
public class TradesSoldIncrementGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = 5026465789210876903L;

	private String fields;
	private Date startModified;
	private Date endModified;
	private String type;
	private String status;
	private Integer pageNo;
	private Integer pageSize;

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

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public Date getStartModified() {
		return startModified;
	}

	public void setStartModified(Date startModified) {
		this.startModified = startModified;
	}

	public Date getEndModified() {
		return endModified;
	}

	public void setEndModified(Date endModified) {
		this.endModified = endModified;
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

}
