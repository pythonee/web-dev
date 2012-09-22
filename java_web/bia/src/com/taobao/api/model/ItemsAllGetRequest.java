/**
 * 
 */
package com.taobao.api.model;

/**
 * 调用taobao.items.All.get搜索当前会话用户（必须为卖家）的商品时，返回的response
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemsAllGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -4657341127170578441L;
	private String fields;
	private String q;
	private String cid;
	private String sellerCids;
	private Integer pageNo;
	private Integer pageSize;

	private String orderBy;

	
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

	

	public ItemsAllGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public ItemsAllGetRequest withQ(String q) {
		setQ(q);
		return this;
	}

	public ItemsAllGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public ItemsAllGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}


	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public ItemsAllGetRequest withOrderBY(String orderBy) {
		setOrderBy(orderBy);
		return this;
	}

}
