package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * 邮费模板运费子项
 * @author gaoweibin.tw
 *
 */
public class PostageMode extends TaobaoModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1765809056944464898L;
	
	@ApiName("id")
	private String postageModeId;
	private String postageId;
	@ApiName("type")
	private String postageModeType;
	@ApiName("dests")
	private String dest;
	private String price;
	private String increase;
	private String opttype;
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	@Deprecated
	public String getPostageModeId() {
		return postageModeId;
	}
	public String getId() {
		return postageModeId;
	}
	@Deprecated
	public void setPostageModeId(String postageModeId) {
		this.postageModeId = postageModeId;
	}
	public void setId(String postageModeId) {
		this.postageModeId = postageModeId;
	}
	
	public String getPostageId() {
		return postageId;
	}
	public void setPostageId(String postageId) {
		this.postageId = postageId;
	}
	@Deprecated
	public String getPostageModeType() {
		return postageModeType;
	}
	public String getType() {
		return postageModeType;
	}
	@Deprecated
	public void setPostageModeType(String postageModeType) {
		this.postageModeType = postageModeType;
	}
	public void setType(String postageModeType) {
		this.postageModeType = postageModeType;
	}
	@Deprecated
	public String getDest() {
		return dest;
	}
	public String getDests() {
		return dest;
	}
	@Deprecated
	public void setDest(String dest) {
		this.dest = dest;
	}
	public void setDests(String dest) {
		this.dest = dest;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIncrease() {
		return increase;
	}
	public void setIncrease(String increase) {
		this.increase = increase;
	}
	
}
