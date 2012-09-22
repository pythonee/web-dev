/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt 2009-2-19 上午10:25:50
 * 
 */
public class RefundGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 4851417534657311089L;

	private Refund refund;

	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}

	public RefundGetResponse() {
		super();
	}

	public RefundGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

}
