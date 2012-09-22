package com.taobao.api.model;


/**
 * 收费地址
 * 
 * @author gaoweibin.tw
 * 
 */
public class ShippingAddress extends TaobaoModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4394011035684737010L;
	private int addressId;
	private String receiverName;
	private String phone;
	private String mobile;
	private boolean isDefault;
	private Location location;

	/* --- delete by jeck 2009-04-15 

		private Date created;
	 	private Date modified;
	 	
	--- */
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
