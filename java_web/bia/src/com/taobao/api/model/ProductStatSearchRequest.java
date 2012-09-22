package com.taobao.api.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 代表一个 产品统计搜索的http请求
 * 
 * @author hukui
 * 
 */
public class ProductStatSearchRequest extends TaobaoRequest {

	private static final long serialVersionUID = -1116691125110886068L;

	public static final String QUOTE = "q"; // 搜索字段。

	public static final String CID = "cid"; // 商品类目ID。

	public static final String PROP_PATH = "prop_path"; // 属性导航。

	public static final String PAGE_NO = "page_no"; // 页码。

	public static final String PAGE_SIZE = "page_size"; // 每页条数。

	public static final String ORDER_BY = "order_by"; // 排序方式。

	private String quote; // 搜索字段。

	private String catId; // 商品类目ID。

	private String propPath; // 属性导航。

	private int pageNumber; // 页码。

	private int pageSize; // 每页条数。

	private String orderBy; // 排序方式。

	/**
	 * 获得请求的参数map列表
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put(ProductStatSearchRequest.QUOTE, quote);
		params.put(ProductStatSearchRequest.CID, catId);
		params.put(ProductStatSearchRequest.ORDER_BY, orderBy);
		params.put(ProductStatSearchRequest.PAGE_NO, pageNumber);
		params.put(ProductStatSearchRequest.PAGE_SIZE, pageSize);
		params.put(ProductStatSearchRequest.PROP_PATH, propPath);

		return params;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getPropPath() {
		return propPath;
	}

	public void setPropPath(String propPath) {
		this.propPath = propPath;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
