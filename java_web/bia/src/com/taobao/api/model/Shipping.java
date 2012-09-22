/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * @author biyi
 *
 */
public class Shipping extends TaobaoModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2269010345709959733L;
	private String tid;
	private String sellerNick;
	private String buyerNick;
	private Date deliveryStart;
	private Date deliveryEnd;
	private String outSid;
	private String itemTitle;
	private String receiverName;
	private String receiverMobile;
	private String receiverPhone;
	private Location receiverLocation;
	private Date created;
	private Date modified;	
	private String status;
	private String type;
	private String freightPayer;
	private String companyName;
	private String sellerConfirm;
	private boolean isSuccess;
		
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
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
	public Date getDeliveryStart() {
		return deliveryStart;
	}
	public void setDeliveryStart(Date deliveryStart) {
		this.deliveryStart = deliveryStart;
	}
	public Date getDeliveryEnd() {
		return deliveryEnd;
	}
	public void setDeliveryEnd(Date deliveryEnd) {
		this.deliveryEnd = deliveryEnd;
	}
	public String getOutSid() {
		return outSid;
	}
	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
	public Location getReceiverLocation() {
		return receiverLocation;
	}
	public void setReceiverLocation(Location receiverLocation) {
		this.receiverLocation = receiverLocation;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFreightPayer() {
		return freightPayer;
	}
	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSellerConfirm() {
		return sellerConfirm;
	}
	public void setSellerConfirm(String sellerConfirm) {
		this.sellerConfirm = sellerConfirm;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	private boolean deliverSuccess;

	public boolean isDeliverSuccess() {
		return deliverSuccess;
	}
	public void setDeliverSuccess(boolean deliverSuccess) {
		this.deliverSuccess = deliverSuccess;
	}
}