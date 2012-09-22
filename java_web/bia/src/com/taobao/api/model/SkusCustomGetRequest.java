package com.taobao.api.model;

/**
 * 
 * @author moling
 * @since 1.0, 2009-9-23
 */
public class SkusCustomGetRequest extends TaobaoRequest {

	private static final long serialVersionUID = 8168902467785872651L;

	private String outerId;
	private String fields;

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

}
