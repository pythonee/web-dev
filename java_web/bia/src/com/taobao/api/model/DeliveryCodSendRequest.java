package com.taobao.api.model;

/**
 * cod发货参数
 * @author daotong
 *
 */
public class DeliveryCodSendRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = 245779617234550257L;
	private String tid;
	private String companyCode;
	private String sellerName;
	private String sellerAreaId;
	private String sellerAddress;
	private String sellerZip;
	private String sellerPhone;
	private String sellerMobile;
	private String orderType;
	private String fetcherName;
	private String fetcherAreaId;
	private String fetcherAddress;
	private String fetcherZip;
	private String fetcherPhone;
	private String fetcherMobile;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public void setFetcherAddress(String fetcherAddress) {
		this.fetcherAddress = fetcherAddress;
	}
	public String getFetcherAddress() {
		return fetcherAddress;
	}
	public void setFetcherName(String fetcherName) {
		this.fetcherName = fetcherName;
	}
	public String getFetcherName() {
		return fetcherName;
	}
	public void setFetcherAreaId(String fetcherAreaId) {
		this.fetcherAreaId = fetcherAreaId;
	}
	public String getFetcherAreaId() {
		return fetcherAreaId;
	}
	public void setFetcherZip(String fetcherZip) {
		this.fetcherZip = fetcherZip;
	}
	public String getFetcherZip() {
		return fetcherZip;
	}
	public void setFetcherPhone(String fetcherPhone) {
		this.fetcherPhone = fetcherPhone;
	}
	public String getFetcherPhone() {
		return fetcherPhone;
	}
	public void setFetcherMobile(String fetcherMobile) {
		this.fetcherMobile = fetcherMobile;
	}
	public String getFetcherMobile() {
		return fetcherMobile;
	}
}
