package com.taobao.api.model;

import java.util.Date;

/**
 * 套餐对象
 * 
 * @author Taylor xuwei840916@hotmail.com
 * @version 2009-2-18 下午07:21:45
 */
public class Suite extends TaobaoModel {
	private static final long serialVersionUID = -8958268299457644333L;
	
	/**
	 * 套餐id
	 */
	private String id;

	/**
	 * 订购者昵称
	 */
	private String nick;

	/**
	 * 套餐名称
	 */
	private String suiteName;

	/**
	 * 开始时间
	 */
	private Date startDate;

	/**
	 * 结束时间
	 */
	private Date endDate;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
