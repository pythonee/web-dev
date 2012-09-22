package com.taobao.api.model;

@Deprecated
public class LogisticCompaniesGetResponse extends LogisticsCompaniesGetResponse {

	private static final long serialVersionUID = 8592729449680934593L;

	public LogisticCompaniesGetResponse() {
		super();
	}

	public LogisticCompaniesGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public LogisticCompaniesGetResponse(LogisticsCompaniesGetResponse rsp) {
		super(rsp);
		super.setLogisticCompanies(rsp.getLogisticCompanies());
		super.setTotalResults(rsp.getTotalResults());
	}

}
