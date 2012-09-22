package com.taobao.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性
 * 
 * @author jeck218@gmail.com 2009-9-3
 */
public class ItemProp extends TaobaoModel {

	private static final long serialVersionUID = -4337100429001420296L;

	private String pid;
	private String name;
	private Boolean pubMust;
	private Boolean pubMulti;
	private Boolean isKeyProp;
	private Boolean isSaleProp;
	private Boolean isColorProp;
	private Boolean isEnumProp;
	private Boolean isInputProp;
	private Boolean isItemProp;
	private String childTemplate;
	private Boolean must;
	private Boolean multi;
	private String parentPid;
	private String parentVid;
	private String status;
	private Integer sortOrder;
	private List<PropValue> propValues; // added in 2009/09/25

	/** 是否允许别名 */
	private Boolean isAllowAlias;

	public Boolean getIsAllowAlias() {
		return isAllowAlias;
	}

	public void setIsAllowAlias(Boolean isAllowAlias) {
		this.isAllowAlias = isAllowAlias;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPubMust() {
		return pubMust;
	}

	public void setPubMust(Boolean must) {
		this.pubMust = must;
	}

	public Boolean getPubMulti() {
		return pubMulti;
	}

	public void setPubMulti(Boolean multi) {
		this.pubMulti = multi;
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

	public Boolean getMust() {
		return must;
	}

	public void setMust(Boolean must) {
		this.must = must;
	}

	public Boolean getMulti() {
		return multi;
	}

	public void setMulti(Boolean multi) {
		this.multi = multi;
	}

	public String getParentPid() {
		return parentPid;
	}

	public void setParentPid(String parentPid) {
		this.parentPid = parentPid;
	}

	public String getParentVid() {
		return parentVid;
	}

	public void setParentVid(String parentVid) {
		this.parentVid = parentVid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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

	public String getChildTemplate() {
		return childTemplate;
	}

	public void setChildTemplate(String childTemplate) {
		this.childTemplate = childTemplate;
	}

	public Boolean getIsItemProp() {
		return isItemProp;
	}

	public void setIsItemProp(Boolean isItemProp) {
		this.isItemProp = isItemProp;
	}

	public List<PropValue> getPropValues() {
		return this.propValues;
	}

	public void setPropValues(List<PropValue> propValues) {
		this.propValues = propValues;
	}

	public void addPropValue(PropValue propValue) {
		if (this.propValues == null) {
			this.propValues = new ArrayList<PropValue>();
		}
		this.propValues.add(propValue);
	}

}
