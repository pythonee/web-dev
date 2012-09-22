/**
 * 
 */
package com.taobao.api.model;

/**
 * taobao.items.inventory.get
 * 
 * @author jeck218@gmail.com 2009-9-24
 */
public class ItemsInventoryGetRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = 3556159869296900626L;
	private String fields;
	private String q;
	private Integer pageNo;
	private Integer pageSize;
	private String banner;
	private String orderBy;
	private String itemType;
	private Boolean isTaobao;
	private Boolean isEx;

	public Boolean getIsTaobao() {
		return isTaobao;
	}

	public void setIsTaobao(Boolean isTaobao) {
		this.isTaobao = isTaobao;
	}

	public Boolean getIsEx() {
		return isEx;
	}

	public void setIsEx(Boolean isEx) {
		this.isEx = isEx;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public ItemsInventoryGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public ItemsInventoryGetRequest withQ(String q) {
		setQ(q);
		return this;
	}

	public ItemsInventoryGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public ItemsInventoryGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	public ItemsInventoryGetRequest withBanner(String banner) {
		setBanner(banner);
		return this;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public ItemsInventoryGetRequest withOrderBY(String orderBy) {
		setOrderBy(orderBy);
		return this;
	}

	public ItemsInventoryGetRequest withItemType(String itemType) {
		setItemType(itemType);
		return this;
	}
}
