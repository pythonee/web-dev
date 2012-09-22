/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author sulinchong.pt 2009-2-19 上午10:17:37
 * 
 */
public class RefundsResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -6524984278919021204L;

	@ApiName("refunds")
	private List<Refund> refundList;

	public List<Refund> getRefundList() {
		return refundList;
	}

	public void setRefundList(List<Refund> refundList) {
		this.refundList = refundList;
	}

	public RefundsResponse() {
		super();
	}

	public RefundsResponse(TaobaoResponse rsp) {
		super(rsp);
	}

}
