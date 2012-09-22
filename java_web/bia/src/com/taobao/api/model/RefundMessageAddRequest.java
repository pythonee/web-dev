package com.taobao.api.model;

import java.io.File;

/**
 * 调用 taobao.refund.message.add 添加商品时 需要传入的参数
 * @author tianchong
 *
 */
public class RefundMessageAddRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = -8339579022333685681L;

	private String content;
	
	private String ownerNick;
	
	private String refundId;
	
	private File image;
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOwnerNick() {
		return ownerNick;
	}

	public void setOwnerNick(String ownerNick) {
		this.ownerNick = ownerNick;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public RefundMessageAddRequest withRefundId(String refundId) {
		setRefundId(refundId);
		return this;
	}
	
	public RefundMessageAddRequest withOwnerNick(String ownerNick) {
		setOwnerNick(ownerNick);
		return this;
	}
	
}
