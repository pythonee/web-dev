
package com.taobao.api.model;

/**
 *  调用 taobao.refund.message.add 添加退款留言凭证时,返回的Response
 * @author tianchong
 *
 */
public class RefundMessageAddResponse extends TaobaoResponse {

	private static final long serialVersionUID = -8518793242231472532L;
	
	private RefundMessage refundMessage;
	
	public RefundMessage getRefundMessage() {
		return refundMessage;
	}

	public void setRefundMessage(RefundMessage refundMessage) {
		this.refundMessage = refundMessage;
	}

	public RefundMessageAddResponse() {
		super();
	}

	public RefundMessageAddResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	
}
