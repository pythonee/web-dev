package com.taobao.api.model;

import java.util.Date;

/**
 * 调用taobao.itempropvalues.get
 * 
 * @author Taylor xuwei840916@hotmail.com
 * @version 2008-12-22 上午10:19:59
 */
public class ItemPropValuesGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -5449238247801721929L;
	private String fields;
	private String cid;
	private String pvs;
	private Date datetime;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPvs() {
		return pvs;
	}

	public void setPvs(String pvs) {
		this.pvs = pvs;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
