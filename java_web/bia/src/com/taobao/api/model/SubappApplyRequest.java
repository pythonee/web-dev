package com.taobao.api.model;

/**
 * 申请子应用请求封装。
 * 
 * @author fengsheng
 * @since 1.0, Jul 24, 2009
 */
public class SubappApplyRequest extends TaobaoRequest {

	private static final long serialVersionUID = -4065263802941066541L;

	private String parentAppKey; // 父应用代号
	private String title; // 子应用标题
	private String callbackUrl; // 子应用回调地址
	private String notifyUrl; // 子应用通知地址

	public String getParentAppKey() {
		return this.parentAppKey;
	}

	public void setParentAppKey(String parentAppKey) {
		this.parentAppKey = parentAppKey;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCallbackUrl() {
		return this.callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getNotifyUrl() {
		return this.notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

}
