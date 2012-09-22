package com.taobao.api.model;

/**
 * 申请子应用响应封装。
 * 
 * @author fengsheng
 * @since 1.0, Jul 24, 2009
 */
public class SubappApplyResponse extends TaobaoResponse {

	private static final long serialVersionUID = -8529657626845418018L;

	private Tadget subapp;

	public SubappApplyResponse() {
		super();
	}

	public SubappApplyResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public Tadget getSubapp() {
		return this.subapp;
	}

	public void setSubapp(Tadget subapp) {
		this.subapp = subapp;
	}

}
