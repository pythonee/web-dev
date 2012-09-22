package com.taobao.api.model;

/**
 * 更改交易的收货地址请求。
 * 
 * @author fengsheng
 * @since 1.0, Oct 27, 2009
 */
public class TradeShippingAddressUpdateRequest extends TaobaoRequest {

	private static final long serialVersionUID = 495694855671192220L;

	private String tid; // 交易编号
	private String receiverName; // 收货人全名
	private String receiverPhone; // 固定电话
	private String receiverMobile; // 移动电话
	private String receiverState; // 省份
	private String receiverCity;// 市
	private String receiverDistrict; // 区/县
	private String receiverAddress; // 收货地址
	private String receiverZip; // 邮编

	public String getTid() {
		return this.tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getReceiverName() {
		return this.receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return this.receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverMobile() {
		return this.receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverState() {
		return this.receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverCity() {
		return this.receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverDistrict() {
		return this.receiverDistrict;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverAddress() {
		return this.receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverZip() {
		return this.receiverZip;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

}
