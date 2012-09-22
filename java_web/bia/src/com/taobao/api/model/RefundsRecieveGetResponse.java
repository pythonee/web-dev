package com.taobao.api.model;

/**
 * @deprecated by RefundsReceiveGetResponse
 */
public class RefundsRecieveGetResponse extends RefundsReceiveGetResponse {

	private static final long serialVersionUID = 826775313648519009L;

	public RefundsRecieveGetResponse() {
		super();
	}

	public RefundsRecieveGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public RefundsRecieveGetResponse(RefundsReceiveGetResponse rsp) {
		super(rsp);
		super.setRefundList(rsp.getRefundList());
		super.setTotalResults(rsp.getTotalResults());
	}

}
