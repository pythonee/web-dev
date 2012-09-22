package com.taobao.api.model;

/**
 * 发货参数
 * @author gaoweibin.tw
 *
 */
public class DeliverySendRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = 245779617234550257L;
	private String tid;
//	private String appIp;
	private String companyCode;
	private String outSid;
	private String sellerName;
	private String sellerAreaId;
	private String sellerAddress;
	private String sellerZip;
	private String sellerPhone;
	private String sellerMobile;
	private String memo;
	private String orderType;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
//	public String getAppIp() {
//		return appIp;
//	}
//	public void setAppIp(String appIp) {
//		this.appIp = appIp;
//	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getOutSid() {
		return outSid;
	}
	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerAreaId() {
		return sellerAreaId;
	}
	public void setSellerAreaId(String sellerAreaId) {
		this.sellerAreaId = sellerAreaId;
	}
	public String getSellerAddress() {
		return sellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	public String getSellerZip() {
		return sellerZip;
	}
	public void setSellerZip(String sellerZip) {
		this.sellerZip = sellerZip;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public String getSellerMobile() {
		return sellerMobile;
	}
	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
