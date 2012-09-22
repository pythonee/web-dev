package com.taobao.api.model;

import java.util.List;

/**
 * @author Taylor xuwei840916@hotmail.com
 * @version 2009-2-18 下午07:25:36
 */
public class SuitesGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = 1350553536562520811L;

	private List<Suite> suites;

	public SuitesGetResponse() {
		super();
	}

	public SuitesGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<Suite> getSuites() {
		return suites;
	}

	public void setSuites(List<Suite> suites) {
		this.suites = suites;
	}

}
