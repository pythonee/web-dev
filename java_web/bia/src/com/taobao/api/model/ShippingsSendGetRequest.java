package com.taobao.api.model;

import java.util.Date;

/**
 * taobao.shippings.send.get
 */
@Deprecated
public class ShippingsSendGetRequest extends LogisticsOrdersGetRequest {

	private static final long serialVersionUID = -3943455568149745936L;

	@Override
	public ShippingsSendGetRequest withBuyerNick(String buyerNick) {
		super.withBuyerNick(buyerNick);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withEndCreated(Date endCreated) {
		super.withEndCreated(endCreated);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withFields(String fields) {
		super.withFields(fields);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withFreightPayer(String freightPayer) {
		super.withFreightPayer(freightPayer);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withItemTitle(String itemTitle) {
		super.withItemTitle(itemTitle);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withPageNo(Integer pageNo) {
		super.withPageNo(pageNo);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withPageSize(Integer pageSize) {
		super.withPageSize(pageSize);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withReceiverName(String receiverName) {
		super.withReceiverName(receiverName);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withSellerConfirm(String sellerConfirm) {
		super.withSellerConfirm(sellerConfirm);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withStartCreated(Date startCreated) {
		super.withStartCreated(startCreated);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withStatus(String status) {
		super.withStatus(status);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withTid(String tid) {
		super.withTid(tid);
		return this;
	}

	@Override
	public ShippingsSendGetRequest withType(String type) {
		super.withType(type);
		return this;
	}

}
