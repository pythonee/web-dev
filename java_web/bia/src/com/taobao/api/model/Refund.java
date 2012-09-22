package com.taobao.api.model;

import java.util.Date;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author sulinchong.pt 2009-2-19 PM 09:42:34
 */
public class Refund {
	/**
	 * 退款单号
	 */
	private String refundId;

	/**
	 * 淘宝交易号
	 */
	private String tid;

	/**
	 * 子订单号
	 */
	private String oid;

	/**
	 * 支付宝交易号
	 */
	private String alipayNo;

	/**
	 * 交易总金额
	 */
	private String totalFee;

	/**
	 * 买家昵称
	 */
	private String buyerNick;

	/**
	 * 卖家昵称
	 */
	private String sellerNick;

	/**
	 * 退款申请时间
	 */
	private Date created;

	/**
	 * 退款状态
	 */
	private String status;

	/**
	 * 货物状态
	 */
	private String goodStatus;

	/**
	 * 买家是否需要退货
	 */
	private Boolean hasGoodReturn;

	/**
	 * 退款金额
	 */
	private String refundFee;

	/**
	 * 支付给卖家的金额
	 */
	private String payment;

	/**
	 * 退款原因
	 */
	private String reason;

	/**
	 * 退款说明
	 */
	private String desc;

	/**
	 * 商品编号
	 */
	private String iid;

	/**
	 * 商品标题
	 */
	private String title;

	/**
	 * 商品价格
	 */
	private String price;

	/**
	 * 商品购买数量
	 */
	private Integer num;

	/**
	 * 退货时间
	 */
	private Date goodReturnTime;

	/**
	 * 物流公司名称
	 */
	private String companyName;

	/**
	 * 退货运单号
	 */
	private String sid;

	/**
	 * 卖家收货地址
	 */
	private String address;
	/**
	 * 商品的图片链接地址
	 */
	private String itemPictUrl;
	/**
	 * 超时时间
	 */
	@ApiName("refund_remind_timeout")
	private RefundRemindTimeOut refundRemindTimeOut;

	// add by jeck 2009-06-15
	/**
	 * 对应的订单状态
	 */
	private String orderStatus;

	/**
	 * 更新时间
	 */
	private Date modified;

	/** 物流方式 */
	private String shippingType;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getItemPictUrl() {
		return itemPictUrl;
	}

	public void setItemPictUrl(String itemPictUrl) {
		this.itemPictUrl = itemPictUrl;
	}

	public RefundRemindTimeOut getRefundRemindTimeOut() {
		return refundRemindTimeOut;
	}

	public void setRefundRemindTimeOut(RefundRemindTimeOut refundRemindTimeOut) {
		this.refundRemindTimeOut = refundRemindTimeOut;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getAlipayNo() {
		return alipayNo;
	}

	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGoodStatus() {
		return goodStatus;
	}

	public void setGoodStatus(String goodStatus) {
		this.goodStatus = goodStatus;
	}

	public Boolean getHasGoodReturn() {
		return hasGoodReturn;
	}

	public void setHasGoodReturn(Boolean hasGoodReturn) {
		this.hasGoodReturn = hasGoodReturn;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getGoodReturnTime() {
		return goodReturnTime;
	}

	public void setGoodReturnTime(Date goodReturnTime) {
		this.goodReturnTime = goodReturnTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

}
