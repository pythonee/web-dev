package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 * 
 */
public class ItemVerticalProp extends TaobaoModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7235864153492384600L;

	private String id;
	private String name;
	private String type;
	private Boolean isRequired;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

}
