package com.taobao.api.model;

import java.util.Date;
/**
 * 退款提醒信息DO
 * @author tianchong
 *
 */
public class RefundRemindTimeOut{


	/**
	 * 超时信息提醒类型
	 */
	private Integer remindType;

	
	/**
	 * 是否有超时时间
	 */
	private boolean isExistTimeout;
	
	
	/**
	 * 退款超时时间
	 */
	private Date timeout;
	
	
	
	public Integer getRemindType() {
		return remindType;
	}

	public void setRemindType(Integer remindType) {
		this.remindType = remindType;
	}

	public boolean isExistTimeout() {
		return isExistTimeout;
	}

	public void setExistTimeout(boolean isExistTimeOut) {
		this.isExistTimeout = isExistTimeOut;
	}

	public Date getTimeout() {
		return timeout;
	}

	public void setTimeout(Date timeOut) {
		this.timeout = timeOut;
	}
}