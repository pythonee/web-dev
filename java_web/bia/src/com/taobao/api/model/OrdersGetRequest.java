/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt 2009-1-13 下午09:12:58
 * 
 */
public class OrdersGetRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = -2237623638505640061L;
	private String iid;
	private String fields;
	private String sellerNick;
	private Integer pageNo;
	private Integer pageSize;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the iid
	 */
	public String getIid() {
		return iid;
	}

	/**
	 * @param iid
	 *            the iid to set
	 */
	public void setIid(String iid) {
		this.iid = iid;
	}

	/**
	 * @return the fields
	 */
	public String getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}

	/**
	 * @return the sellerNick
	 */
	public String getSellerNick() {
		return sellerNick;
	}

	/**
	 * @param sellerNick
	 *            the sellerNick to set
	 */
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public OrdersGetRequest withIid(String iid) {
		setIid(iid);
		return this;
	}

	public OrdersGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public OrdersGetRequest withSellersNick(String sellerNick) {
		setSellerNick(sellerNick);
		return this;
	}

	public OrdersGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public OrdersGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}
}
