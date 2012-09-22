package com.taobao.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taobao.api.convert.reader.ApiName;

/**
 * 退款留言凭证DO
 * 
 * @author tianchong
 */
public class RefundMessage {

	/**
	 * 退款留言凭证单号
	 */
	@ApiName("id")
	private String messageId;

	/**
	 * 退款留言类型
	 */
	private String messageType;

	/**
	 * 退款单号
	 */
	private String refundId;

	/**
	 * 拥有者单号
	 */
	private String ownerId;

	/**
	 * 拥有者的登录名
	 */
	private String ownerNick;

	/**
	 * 拥有者的角色：卖家，买家
	 */
	private String ownerRole;

	/**
	 * 留言内容
	 */
	private String content;

	/**
	 * 凭证的图片地址
	 */
	private List<String> pictureUrlList;

	@ApiName("pic_urls")
	private List<PicUrl> picUrlList;

	/**
	 * 退款留言创建时间
	 */
	private Date created;

	/**
	 * @deprecated replaced by getId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @deprecated replaced by setId
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getId() {
		return messageId;
	}

	public void setId(String id) {
		this.messageId = id;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerNick() {
		return ownerNick;
	}

	public void setOwnerNick(String ownerNick) {
		this.ownerNick = ownerNick;
	}

	public String getOwnerRole() {
		return ownerRole;
	}

	public void setOwnerRole(String ownerRole) {
		this.ownerRole = ownerRole;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @deprecated replaced by getPicUrls
	 */
	public List<String> getPictureUrlList() {
		return pictureUrlList;
	}

	/**
	 * @deprecated replaced by setPicUrls
	 */
	public void setPictureUrlList(List<String> pictureUrlList) {
		this.pictureUrlList = pictureUrlList;
	}

	public List<PicUrl> getPicUrlList() {
		return this.picUrlList;
	}

	public void setPicUrlList(List<PicUrl> picUrls) {
		this.picUrlList = picUrls;
		if (this.picUrlList != null && !this.picUrlList.isEmpty()) {
			List<String> target = new ArrayList<String>();
			for (PicUrl picUrl : this.picUrlList) {
				target.add(picUrl.getUrl());
			}
			setPicUrls(target);
		}
	}

	public List<String> getPicUrls() {
		return pictureUrlList;
	}

	public void setPicUrls(List<String> picUrls) {
		this.pictureUrlList = picUrls;
	}

}