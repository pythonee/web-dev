package com.taobao.api.model;

import java.util.Date;
import java.util.List;

import com.taobao.api.convert.reader.ApiName;

/**
 * 
 * @author gaoweibin.tw
 *
 */
public class Postage extends TaobaoModel {
	
	private static final long serialVersionUID = 33230486742413888L;
	private String postageId;
	private String name;
	private String memo;
	private Date created;
	private Date modified;
	private String postPrice;
	private String postIncrease;
	private String expressPrice;
	private String expressIncrease;
	private String emsPrice;
	private String emsIncrease;
	
	@ApiName("postage_modes")
	List<PostageMode> postageModeList;

	public String getPostageId() {
		return postageId;
	}

	public void setPostageId(String postage_id) {
		this.postageId = postage_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	@Deprecated
	public List<PostageMode> getPostageModeList() {
		return postageModeList;
	}
	public List<PostageMode> getPostageModes() {
		return postageModeList;
	}

	@Deprecated
	public void setPostageModeList(List<PostageMode> postageModeList) {
		this.postageModeList = postageModeList;
	}
	public void setPostageModes(List<PostageMode> postageModeList) {
		this.postageModeList = postageModeList;
	}

	public String getPostPrice() {
		return postPrice;
	}

	public void setPostPrice(String postPrice) {
		this.postPrice = postPrice;
	}

	public String getPostIncrease() {
		return postIncrease;
	}

	public void setPostIncrease(String postIncrease) {
		this.postIncrease = postIncrease;
	}

	public String getExpressPrice() {
		return expressPrice;
	}

	public void setExpressPrice(String expressPrice) {
		this.expressPrice = expressPrice;
	}

	public String getExpressIncrease() {
		return expressIncrease;
	}

	public void setExpressIncrease(String expressIncrease) {
		this.expressIncrease = expressIncrease;
	}

	public String getEmsPrice() {
		return emsPrice;
	}

	public void setEmsPrice(String emsPrice) {
		this.emsPrice = emsPrice;
	}

	public String getEmsIncrease() {
		return emsIncrease;
	}

	public void setEmsIncrease(String emsIncrease) {
		this.emsIncrease = emsIncrease;
	}
	
}
