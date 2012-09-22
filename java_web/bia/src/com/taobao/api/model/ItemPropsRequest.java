package com.taobao.api.model;

import java.util.Date;

/**
 * 商品属性请求封装。
 * 
 * @author fengsheng
 * @since 1.0, Sep 24, 2009
 */
public class ItemPropsRequest extends TaobaoRequest {

	private static final long serialVersionUID = -2299721899114330354L;

	private String fields;
	private String cid;
	private String pid;
	private String parentPid;
	private Boolean isKeyProp;
	private Boolean isSaleProp;
	private Boolean isColorProp;
	private Boolean isEnumProp;
	private Boolean isInputProp;
	private Boolean isItemProp;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getParentPid() {
		return parentPid;
	}

	public void setParentPid(String parentPid) {
		this.parentPid = parentPid;
	}

	public Boolean getIsKeyProp() {
		return isKeyProp;
	}

	public void setIsKeyProp(Boolean isKeyProp) {
		this.isKeyProp = isKeyProp;
	}

	public Boolean getIsSaleProp() {
		return isSaleProp;
	}

	public void setIsSaleProp(Boolean isSaleProp) {
		this.isSaleProp = isSaleProp;
	}

	public Boolean getIsColorProp() {
		return isColorProp;
	}

	public void setIsColorProp(Boolean isColorProp) {
		this.isColorProp = isColorProp;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Boolean getIsEnumProp() {
		return isEnumProp;
	}

	public void setIsEnumProp(Boolean isEnumProp) {
		this.isEnumProp = isEnumProp;
	}

	public Boolean getIsInputProp() {
		return isInputProp;
	}

	public void setIsInputProp(Boolean isInputProp) {
		this.isInputProp = isInputProp;
	}

	public Boolean getIsItemProp() {
		return isItemProp;
	}

	public void setIsItemProp(Boolean isItemProp) {
		this.isItemProp = isItemProp;
	}

	public ItemPropsRequest withCid(String cid) {
		setCid(cid);
		return this;
	}
}
