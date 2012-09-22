/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt 2009-2-19 上午10:23:34
 *
 */
public class RefundGetRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = 1047894615432713359L;

	private String refundId;
	
	private String fields;

	private String bizOrderId;
	
	public String getBizOrderId() {
		return bizOrderId;
	}

	public void setBizOrderId(String bizOrderId) {
		this.bizOrderId = bizOrderId;
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
	
	public RefundGetRequest withRefundId(String refundId){
		setRefundId(refundId);
		return this;
	}
	
	public RefundGetRequest withFields(String fields){
		setFields(fields);
		return this;
	}
}
