package com.taobao.api.model;

import java.util.Date;

/**
 * 物流订单查询
 */
public class LogisticsOrdersGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = 2067699657036337682L;

	private String fields;
	private String tid;
	private String buyerNick;
	private String status;
	private String receiverName;
	private Date startCreated;
	private Date endCreated;
	private String itemTitle;
	private String freightPayer;
	private String type;
	private Integer pageNo;
	private Integer pageSize;
	private String sellerConfirm;

	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getFreightPayer() {
		return freightPayer;
	}
	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getSellerConfirm() {
		return sellerConfirm;
	}
	public void setSellerConfirm(String sellerConfirm) {
		this.sellerConfirm = sellerConfirm;
	}
	public LogisticsOrdersGetRequest withSellerConfirm(String sellerConfirm) {
		setSellerConfirm(sellerConfirm);
		return this;
	}

	public LogisticsOrdersGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public LogisticsOrdersGetRequest withBuyerNick(String buyerNick) {
		setBuyerNick(buyerNick);
		return this;
	}

	public LogisticsOrdersGetRequest withStatus(String status) {
		setStatus(status);
		return this;
	}

	public LogisticsOrdersGetRequest withReceiverName(String receiverName) {
		setReceiverName(receiverName);
		return this;
	}

	public LogisticsOrdersGetRequest withStartCreated(Date startCreated) {
		setStartCreated(startCreated);
		return this;
	}

	public LogisticsOrdersGetRequest withEndCreated(Date endCreated) {
		setEndCreated(endCreated);
		return this;
	}

	public LogisticsOrdersGetRequest withItemTitle(String itemTitle) {
		setItemTitle(itemTitle);
		return this;
	}

	public LogisticsOrdersGetRequest withFreightPayer(String freightPayer) {
		setFreightPayer(freightPayer);
		return this;
	}

	public LogisticsOrdersGetRequest withType(String type) {
		setType(type);
		return this;
	}

	public LogisticsOrdersGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public LogisticsOrdersGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	public LogisticsOrdersGetRequest withTid(String tid) {
		setTid(tid);
		return this;
	}

	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}

}
