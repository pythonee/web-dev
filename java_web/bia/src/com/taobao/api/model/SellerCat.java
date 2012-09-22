package com.taobao.api.model;

import java.util.Date;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author sulinchong.pt
 */
public class SellerCat extends Cat {

	private static final long serialVersionUID = 3705089128252846026L;

	private Date created;
	private Date modified;
	private Integer sortOrder;
	@ApiName("pic_url")
	private String pictUrl;

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @deprecated replaced by getPicUrl
	 */
	public String getPictUrl() {
		return pictUrl;
	}

	/**
	 * @deprecated replaced by setPicUrl
	 */
	public void setPictUrl(String picUrl) {
		this.pictUrl = picUrl;
	}

	public String getPicUrl() {
		return pictUrl;
	}

	public void setPicUrl(String picUrl) {
		this.pictUrl = picUrl;
	}

}
