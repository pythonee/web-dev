package com.taobao.api.model;

/**
 * @deprecated by RefundsRecieveGetRequest
 */
public class RefundsRecieveGetRequest extends RefundsReceiveGetRequest {

	private static final long serialVersionUID = 6753042815541490366L;

	@Override
	public RefundsRecieveGetRequest withBuyerNick(String buyerNick) {
		super.withBuyerNick(buyerNick);
		return this;
	}

	@Override
	public RefundsRecieveGetRequest withFields(String fields) {
		super.withFields(fields);
		return this;
	}

	@Override
	public RefundsRecieveGetRequest withPageNo(Integer pageNo) {
		super.withPageNo(pageNo);
		return this;
	}

	@Override
	public RefundsRecieveGetRequest withPageSize(Integer pageSize) {
		super.withPageSize(pageSize);
		return this;
	}

	@Override
	public RefundsRecieveGetRequest withStatus(String status) {
		super.withStatus(status);
		return this;
	}

	@Override
	public RefundsRecieveGetRequest withType(String type) {
		super.withType(type);
		return this;
	}

}
