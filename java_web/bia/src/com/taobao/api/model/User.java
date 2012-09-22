package com.taobao.api.model;

import java.util.Date;

/**
 * 用户信息
 * 
 * @author fengsheng
 * @since 1.0, Sep 29, 2009
 */
public class User extends TaobaoModel {

	private static final long serialVersionUID = -4976681873006739865L;

	private String userId;
	private String nick;
	private String sex;
	private UserCredit buyerCredit;
	private UserCredit sellerCredit;
	private Location location;
	private Date created;
	private Date lastVisit;
	private String realName;
	private String idCard;
	private String phone;
	private String mobile;
	private String email;
	private Date birthday;
	private String type;
	private boolean hasMorePic;
	private Integer itemImgNum;
	private Integer itemImgSize;
	private Integer propImgNum;
	private Integer propImgSize;
	private String autoRepost;
	private String promotedType;
	private String status;
	private String alipayBind;
	private String consumerProtection;
	private String alipayAccount;
	private String alipayNo;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPromotedType() {
		return promotedType;
	}
	public void setPromotedType(String promotedType) {
		this.promotedType = promotedType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAlipayBind() {
		return alipayBind;
	}
	public void setAlipayBind(String alipayBind) {
		this.alipayBind = alipayBind;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public UserCredit getBuyerCredit() {
		return buyerCredit;
	}
	public void setBuyerCredit(UserCredit buyerCredit) {
		this.buyerCredit = buyerCredit;
	}
	public UserCredit getSellerCredit() {
		return sellerCredit;
	}
	public void setSellerCredit(UserCredit sellerCredit) {
		this.sellerCredit = sellerCredit;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasMorePic() {
		return hasMorePic;
	}
	public void setHasMorePic(boolean hasMorePic) {
		this.hasMorePic = hasMorePic;
	}
	public Integer getItemImgNum() {
		return itemImgNum;
	}
	public void setItemImgNum(Integer itemImgNum) {
		this.itemImgNum = itemImgNum;
	}
	public Integer getItemImgSize() {
		return itemImgSize;
	}
	public void setItemImgSize(Integer itemImgSize) {
		this.itemImgSize = itemImgSize;
	}
	public Integer getPropImgNum() {
		return propImgNum;
	}
	public void setPropImgNum(Integer propImgNum) {
		this.propImgNum = propImgNum;
	}
	public Integer getPropImgSize() {
		return propImgSize;
	}
	public void setPropImgSize(Integer propImgSize) {
		this.propImgSize = propImgSize;
	}
	public String getAutoRepost() {
		return autoRepost;
	}
	public void setAutoRepost(String autoRepost) {
		this.autoRepost = autoRepost;
	}
	public String getConsumerProtection() {
		return consumerProtection;
	}
	public void setConsumerProtection(String consumerProtection) {
		this.consumerProtection = consumerProtection;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getAlipayNo() {
		return alipayNo;
	}
	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}
}
