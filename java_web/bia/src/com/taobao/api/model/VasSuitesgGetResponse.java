package com.taobao.api.model;

import java.util.List;

public class VasSuitesgGetResponse extends TaobaoListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4000734317374760347L;
	
	private List<Suite> suites;

	public VasSuitesgGetResponse() {
		super();
	}

	public VasSuitesgGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<Suite> getSuites() {
		return suites;
	}

	public void setSuites(List<Suite> suites) {
		this.suites = suites;
	}
}
