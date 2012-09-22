/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 * 
 */
public class ItemCat extends Cat {

	private static final long serialVersionUID = 7408284029786485218L;

	private String status;
	private Integer sortOrder;

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
