/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;
import java.util.List;

import com.taobao.api.convert.reader.ApiName;
import com.taobao.api.model.Order;

/**
 * 交易结构
 * 
 * @author jeck.xie 2009-7-30
 */
public class Trade extends TaobaoModel {
	//
	private static final long serialVersionUID = -8410518683112517552L;

	private String sellerNick;
	private String buyerNick;
	private String iid;
	private String title;
	private String price;

	private String picPath;
	private Integer num;
	private Date created;
	private String type;
	private String tid;

	private String alipayNo;
	private String payment;
	private String status;
	private Boolean sellerRate;
	private Boolean buyerRate;

	private String buyerAlipayNo;
	private String receiverName;
	private String receiverState;
	private String receiverCity;
	private String receiverDistrict;
	private String receiverAddress;
	private String receiverZip;
	private String receiverMobile;
	private String receiverPhone;
	private String buyerEmail;
	private String commissionFee;
	private String sellerAlipayNo;
	private String sellerMobile;
	private String sellerPhone;
	private String sellerName;
	private String sellerEmail;

	/**
	 * 买家留言
	 */
	private String buyerMessage;
	/**
	 * 付款时间
	 */
	private Date payTime;
	/**
	 * 交易结束时间
	 */
	private Date endTime;
	/**
	 * 交易修改时间
	 */
	private Date modified;
	/**
	 * 买家获得积分
	 */
	private Integer buyerObtainPointFee;
	/**
	 * 买家使用积分
	 */
	private Integer pointFee;
	/**
	 * 买家实际使用积分 <li>没有退款时和pointFee的值一样
	 */
	private Integer realPointFee;
	/**
	 * 淘宝物流订单号
	 */
	private String sid;

	/**
	 * 卖家备注
	 */
	private String sellerMemo;
	/**
	 * 买家备注
	 */
	private String buyerMemo;
	/**
	 * 买家付款金额
	 */
	private String totalFee;
	/**
	 * 子订单详情
	 */
	private List<Order> orders;

	/**
	 * 邮费
	 */
	private String postFee;

	/**
	 * 确认收货时间
	 */
	private Date consignTime;

	/**
	 * 卖家优惠金额
	 */
	private String adjustFee;

	/**
	 * 系统折扣金额
	 */
	private String discountFee;

	/**
	 * 交易快照URL
	 */
	private String snapshotUrl;

	/**
	 * 确认收货时的总实付款
	 */
	private String availableConfirmFee;

	/**
	 * 超时到期时间
	 */
	private Date timeoutActionTime;

	/**
	 * 某笔交易中卖家实际收到支付宝的打款
	 */
	private String receivedPayment;

	/**
	 * 是否包含邮费 <li>与<code>availableConfirmFee</code>(确认收货时的总实付款)同时使用
	 */
	private Boolean hasPostFee;

	/**
	 * 快照 add by jeck 2009-04-13
	 */
	private String snapshot;

	/**
	 * 物流方式(冗余字段),创建交易时传入的参数（交易完成前，物流方式有可能改变，但tc里的这个字段一直不变）
	 */
	private String shippingType;

	/**
	 * 货到付款服务费
	 */
	private String codFee;

	/**
	 * 货到付款物流状态
	 */
	private String codStatus;

	/**
	 * 交易备注 专用
	 */
	private String tradeMemo;
	
	/**
	 * 是否是3D交易
	 */
	@ApiName("is_3D")
	private Boolean is3D;
	
	public Boolean getIs3D() {
		return is3D;
	}

	public void setIs3D(Boolean is3D) {
		this.is3D = is3D;
	}

	public String getTradeMemo() {
		return tradeMemo;
	}

	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getCodFee() {
		return codFee;
	}

	public void setCodFee(String codFee) {
		this.codFee = codFee;
	}

	public String getCodStatus() {
		return this.codStatus;
	}

	public void setCodStatus(String codStatus) {
		this.codStatus = codStatus;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 * @return the postFee
	 */
	public String getPostFee() {
		return postFee;
	}

	/**
	 * @param postFee
	 *            the postFee to set
	 */
	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
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

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Boolean sellerRate) {
		this.sellerRate = sellerRate;
	}

	public Boolean getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Boolean buyerRate) {
		this.buyerRate = buyerRate;
	}

	/**
	 * @return the buyerMessage
	 */
	public String getBuyerMessage() {
		return buyerMessage;
	}

	/**
	 * @param buyerMessage
	 *            the buyerMessage to set
	 */
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	/**
	 * @return the payTime
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * @param payTime
	 *            the payTime to set
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the buyerObtainPointFee
	 */
	public Integer getBuyerObtainPointFee() {
		return buyerObtainPointFee;
	}

	/**
	 * @param buyerObtainPointFee
	 *            the buyerObtainPointFee to set
	 */
	public void setBuyerObtainPointFee(Integer buyerObtainPointFee) {
		this.buyerObtainPointFee = buyerObtainPointFee;
	}

	/**
	 * @return the pointFee
	 */
	public Integer getPointFee() {
		return pointFee;
	}

	/**
	 * @param pointFee
	 *            the pointFee to set
	 */
	public void setPointFee(Integer pointFee) {
		this.pointFee = pointFee;
	}

	/**
	 * @return the realPointFee
	 */
	public Integer getRealPointFee() {
		return realPointFee;
	}

	/**
	 * @param realPointFee
	 *            the realPointFee to set
	 */
	public void setRealPointFee(Integer realPointFee) {
		this.realPointFee = realPointFee;
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * @param sid
	 *            the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * @return the sellerMemo
	 */
	public String getSellerMemo() {
		return sellerMemo;
	}

	/**
	 * @param sellerMemo
	 *            the sellerMemo to set
	 */
	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}

	/**
	 * @return the buyerMemo
	 */
	public String getBuyerMemo() {
		return buyerMemo;
	}

	/**
	 * @param buyerMemo
	 *            the buyerMemo to set
	 */
	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}

	/**
	 * @return the totalFee
	 */
	public String getTotalFee() {
		return totalFee;
	}

	/**
	 * @param totalFee
	 *            the totalFee to set
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Date getConsignTime() {
		return consignTime;
	}

	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}

	public String getBuyerAlipayNo() {
		return buyerAlipayNo;
	}

	public void setBuyerAlipayNo(String buyerAlipayNo) {
		this.buyerAlipayNo = buyerAlipayNo;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getCommissionFee() {
		return commissionFee;
	}

	public void setCommissionFee(String commissionFee) {
		this.commissionFee = commissionFee;
	}

	public String getSellerAlipayNo() {
		return sellerAlipayNo;
	}

	public void setSellerAlipayNo(String sellerAlipayNo) {
		this.sellerAlipayNo = sellerAlipayNo;
	}

	public String getSellerMobile() {
		return sellerMobile;
	}

	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}

	public String getSnapshotUrl() {
		return snapshotUrl;
	}

	public void setSnapshotUrl(String snapshotUrl) {
		this.snapshotUrl = snapshotUrl;
	}

	public String getAvailableConfirmFee() {
		return availableConfirmFee;
	}

	public void setAvailableConfirmFee(String availableConfirmFee) {
		this.availableConfirmFee = availableConfirmFee;
	}

	public Boolean getHasPostFee() {
		return hasPostFee;
	}

	public void setHasPostFee(Boolean hasPostFee) {
		this.hasPostFee = hasPostFee;
	}

	public Date getTimeoutActionTime() {
		return timeoutActionTime;
	}

	public void setTimeoutActionTime(Date timeoutActionTime) {
		this.timeoutActionTime = timeoutActionTime;
	}

	public String getReceivedPayment() {
		return receivedPayment;
	}

	public void setReceivedPayment(String receivedPayment) {
		this.receivedPayment = receivedPayment;
	}

}
