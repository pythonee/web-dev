package com.taobao.api.model;

import java.util.List;

import com.taobao.api.convert.reader.ApiName;

public class RefundMessagesGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -6524984278919021204L;

	@ApiName("refund_messages")
	private List<RefundMessage> refundMessageList;

	public List<RefundMessage> getRefundMessageList() {
		return refundMessageList;
	}

	public void setRefundMessageList(List<RefundMessage> refundMessageList) {
		this.refundMessageList = refundMessageList;
	}

	public RefundMessagesGetResponse() {
		super();
	}

	public RefundMessagesGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

}
