package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class AreasGetRequest extends TaobaoRequest {

	//
	private static final long serialVersionUID = -3336217249848509843L;
	/**
	 * 
	 */
	@Deprecated
	private String field;
	private String fields;
	
	@Deprecated
	public String getField() {
		return fields;
	}
	@Deprecated
	public void setField(String field) {
		this.fields = field;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
}
