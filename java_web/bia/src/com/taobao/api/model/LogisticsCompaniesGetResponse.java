package com.taobao.api.model;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.convert.reader.ApiName;


/**
 * @author gaoweibin.tw
 *
 */
public class LogisticsCompaniesGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -7802351723980429275L;
	
	private List<LogisticsCompany> logisticsCompanies;

	public LogisticsCompaniesGetResponse() {
		super();
	}

	public LogisticsCompaniesGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	/**
	 * @see getLogisticsCompanies()
	 */
	@Deprecated
	public List<LogisticCompany> getLogisticCompanies() {
		if(logisticsCompanies == null) return null;
		List<LogisticCompany> logisticCompanies =  new ArrayList<LogisticCompany>();
		for(int i = 0;i<logisticsCompanies.size(); i++){
			LogisticCompany logisticCompany = new LogisticCompany();
			logisticCompany.setId(logisticsCompanies.get(i).getId());
			logisticCompany.setName(logisticsCompanies.get(i).getName());
			logisticCompany.setCode(logisticsCompanies.get(i).getCode());
			logisticCompanies.add(logisticCompany);
		}
		return logisticCompanies;
	}
	/**
	 * @see setLogisticsCompanies()
	 */
	@Deprecated
	public void setLogisticCompanies(List<LogisticCompany> logisticCompanies) {
		if(logisticCompanies!= null && logisticCompanies.size()>0){
			if(logisticsCompanies == null){
				logisticsCompanies = new ArrayList<LogisticsCompany>(); 
			}
			for(int i = 0;i<logisticCompanies.size(); i++){
				LogisticsCompany logisticsCompany = new LogisticsCompany();
				logisticsCompany.setId(logisticCompanies.get(i).getId());
				logisticsCompany.setName(logisticCompanies.get(i).getName());
				logisticsCompany.setCode(logisticCompanies.get(i).getCode());
				logisticsCompanies.add(logisticsCompany);
			}
		}
	}

	public List<LogisticsCompany> getLogisticsCompanies() {
		return logisticsCompanies;
	}

	public void setLogisticsCompanies(List<LogisticsCompany> logisticsCompanies) {
		this.logisticsCompanies = logisticsCompanies;
	}
	
}
