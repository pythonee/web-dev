package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * 交易确认收货费用
 * 
 * @author jeck.xie 2009-3-25
 */
public class TradeConfirmFee extends TaobaoModel {

	private static final long serialVersionUID = -4660121907897161852L;

	/**
	 * 确认收货的金额，不包含邮费，单位是元
	 */
	private String confirmFee;

	/**
	 * 需确认收货的邮费，当不是最后一笔收货或者没有邮费时是0，单位是元
	 */
	private String confirmPostFee;

	/**
	 * 是否是最后一笔订单，如果既是父订单，又是子订单，返回true 如果只是父订单，返回false
	 * 如果是子订单，当其他子订单都是交易完成时，返回true，否则false
	 */
	@ApiName("is_last_order")
	private String isLastDetailOrder;

	public String getConfirmFee() {
		return confirmFee;
	}

	public void setConfirmFee(String confirmFee) {
		this.confirmFee = confirmFee;
	}

	public String getConfirmPostFee() {
		return confirmPostFee;
	}

	public void setConfirmPostFee(String confirmPostFee) {
		this.confirmPostFee = confirmPostFee;
	}

	/**
	 * @deprecated replaced by getIsLastOrder
	 */
	public String getIsLastDetailOrder() {
		return isLastDetailOrder;
	}

	/**
	 * @deprecated replaced by setIsLastOrder
	 */
	public void setIsLastDetailOrder(String isLastDetailOrder) {
		this.isLastDetailOrder = isLastDetailOrder;
	}

	public String getIsLastOrder() {
		return isLastDetailOrder;
	}

	public void setIsLastOrder(String isLastOrder) {
		this.isLastDetailOrder = isLastOrder;
	}

}
