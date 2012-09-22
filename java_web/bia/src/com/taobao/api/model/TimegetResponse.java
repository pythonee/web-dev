package com.taobao.api.model;

import java.util.Date;

/**
 * 得到淘宝系统当前时间
 * taobao.time.get
 */
public class TimegetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 6484328964203900521L;

	private Date maintime;

	public TimegetResponse() {
		super();
	}

	public TimegetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Date getMaintime() {
		return maintime;
	}

	public void setMaintime(Date maintime) {
		this.maintime = maintime;
	}

}
