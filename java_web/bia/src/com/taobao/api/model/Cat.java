package com.taobao.api.model;

/**
 * @author sulinchong.pt
 * 
 */
public abstract class Cat extends TaobaoModel {
	private static final long serialVersionUID = -765481977602589175L;
	private String cid;
	private String parentCid;
	private String name;
	private Boolean isParent;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getParentCid() {
		return parentCid;
	}

	public void setParentCid(String parentCid) {
		this.parentCid = parentCid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

}
