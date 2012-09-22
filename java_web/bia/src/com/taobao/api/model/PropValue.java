package com.taobao.api.model;

/**
 * @author sulinchong.pt
 * @version 1.1 fengsheng 2009/09/25
 */
public class PropValue extends TaobaoModel {

	private static final long serialVersionUID = -410404788361550090L;

	public static final String TABLE_NAME = "prop_values";
	public static final String DOMAIN_NAME = "prop_value";
	public static final String CID = "cid";
	public static final String PID = "pid";
	public static final String VID = "vid";
	public static final String NAME = "name";
	public static final String PROP_NAME = "prop_name";
	public static final String NAME_ALIAS = "name_alias";
	public static final String IS_PARENT = "is_parent";
	public static final String STATUS = "status";
	public static final String SORT_ORDER = "sort_order";

	private String vid;
	private String name;
	private String nameAlias;
	private Boolean isParent;
	private String binds;
	private String cid;
	private String pid;
	private String propName;
	private String status;
	private Integer sortOrder;

	public String getNameAlias() {
		return nameAlias;
	}

	public void setNameAlias(String nameAlias) {
		this.nameAlias = nameAlias;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
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

	public String getBinds() {
		return binds;
	}

	public void setBinds(String binds) {
		this.binds = binds;
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

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
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

}
