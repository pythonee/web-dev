package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * 物流公司信息
 * @author gaoweibin.tw
 *
 */

public class LogisticCompany extends TaobaoModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7832590988057945299L;
	
	@ApiName("id")
	private String companyId;
	@ApiName("code")
	private String companyCode;
	@ApiName("name")
	private String companyName;
	
	@Deprecated
	public String getCompanyId() {
		return companyId;
	}
	@Deprecated
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Deprecated
	public String getCompanyCode() {
		return companyCode;
	}
	@Deprecated
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	@Deprecated
	public String getCompanyName() {
		return companyName;
	}
	@Deprecated
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getId(){
		return companyId;
	}
	public void setId(String id){
		this.companyId = id;
	}
	public String getCode(){
		return companyCode;
	}
	public void setCode(String code){
		this.companyCode = code;
	}
	public String getName() {
		return companyName;
	}
	public void setName(String name){
		this.companyName = name;
	}
}