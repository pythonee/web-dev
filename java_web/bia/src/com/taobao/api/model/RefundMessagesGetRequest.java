
package com.taobao.api.model;

/**
 * 退款留言凭证
 * @author tianchong
 *
 */
public class RefundMessagesGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -2934876062799926069L;

	private String refundId;
	
	private String fields;

	private Integer pageNo;
	
	private Integer pageSize;
	
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

	
	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	
	
	public RefundMessagesGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	
	public RefundMessagesGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}
	
	public RefundMessagesGetRequest withRefundId(String refundId){
		setRefundId(refundId);
		return this;
	}
	
	public RefundMessagesGetRequest withFields(String fields){
		setFields(fields);
		return this;
	}
}
