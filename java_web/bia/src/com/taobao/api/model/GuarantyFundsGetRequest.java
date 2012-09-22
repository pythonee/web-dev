package com.taobao.api.model;

import java.util.Date;

/**
 * 获取B商家保证金使用记录请求封装。
 * 
 * @author fengsheng
 * @since 1.0, Jun 30, 2009
 */
public class GuarantyFundsGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = -3848588989016902409L;

	private Date beginDate; // 查询开始日期
	private Date endDate; // 查询结束日期

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
