/**
 * 
 */
package com.taobao.api.model;

/**
 * 调用taobao.items.instock.get搜索当前会话用户（必须为卖家）的库存商品时，返回的response
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemsOnSaleGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -4657341127170578441L;
	private String fields;
	private String q;
	private String cid;
	private String sellerCids;
	private Integer pageNo;
	private Integer pageSize;
	private Boolean hasDiscount;
	private Boolean hasShowcase;
	private String orderBy;
	private String itemType;
	private Boolean isTaobao;
	private Boolean isEx; 
	public String getItemType() {
		return itemType;
	}

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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSellerCids() {
		return sellerCids;
	}

	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
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

	public Boolean getHasDiscount() {
		return hasDiscount;
	}

	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}

	public Boolean getHasShowcase() {
		return hasShowcase;
	}

	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}

	public ItemsOnSaleGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public ItemsOnSaleGetRequest withQ(String q) {
		setQ(q);
		return this;
	}

	public ItemsOnSaleGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public ItemsOnSaleGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	public ItemsOnSaleGetRequest withHasDiscount(Boolean hasDiscount) {
		setHasDiscount(hasDiscount);
		return this;
	}

	public ItemsOnSaleGetRequest withHasShowcase(Boolean hasShowcase) {
		setHasShowcase(hasShowcase);
		return this;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public ItemsOnSaleGetRequest withOrderBY(String orderBy) {
		setOrderBy(orderBy);
		return this;
	}
	public ItemsOnSaleGetRequest withItemType(String itemType) {
		setItemType(itemType);
		return this;
	}
}
