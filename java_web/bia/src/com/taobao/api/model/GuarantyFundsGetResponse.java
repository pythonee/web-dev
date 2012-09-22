package com.taobao.api.model;

import java.util.List;

/**
 * 获取B商家保证金使用记录响应封装。
 * 
 * @author fengsheng
 * @since 1.0, Jun 30, 2009
 */
public class GuarantyFundsGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -2971466677898743460L;

	private List<GuarantyFund> guarantyFunds;

	public GuarantyFundsGetResponse() {
		super();
	}

	public GuarantyFundsGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<GuarantyFund> getGuarantyFunds() {
		return this.guarantyFunds;
	}

	public void setGuarantyFunds(List<GuarantyFund> guarantyFunds) {
		this.guarantyFunds = guarantyFunds;
	}

}
