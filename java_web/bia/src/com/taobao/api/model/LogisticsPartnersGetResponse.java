package com.taobao.api.model;

import java.util.List;
/**
 * @author daotong
 *
 */
public class LogisticsPartnersGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = -7802351723980429275L;
	
	private List<LogisticsPartner> logisticsPartners;

	public LogisticsPartnersGetResponse() {
		super();
	}

	public LogisticsPartnersGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<LogisticsPartner> getLogisticsPartners() {
		return logisticsPartners;
	}

	public void setLogisticsPartners(List<LogisticsPartner> logisticsPartners) {
		this.logisticsPartners = logisticsPartners;
	}
	
}
