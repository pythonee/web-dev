package com.taobao.api.model;

/**
 * 修改邮费模板 taobao.postage.update时需要传入的参数
 * @author gaoweibin.tw
 *
 */
public class PostageUpdateRequest extends TaobaoRequest {
	private static final long serialVersionUID = 6868405398084879957L;
	
	private String postage_id;
	private String name;
	private String memo;
	private String postPrice;
	private String postIncrease;
	private String expressPrice;
	private String expressIncrease;
	private String emsPrice;
	private String emsIncrease;
	private String postageModeId;
	private String postageModeType;
	private String postageModeDest;
	private String postageModePrice;
	private String postageModeIncrease;
	private String postageModeOpttype;
	public String getPostageModeOpttype() {
		return postageModeOpttype;
	}
	public void setPostageModeOpttype(String postageModeOpttype) {
		this.postageModeOpttype = postageModeOpttype;
	}
	public String getPostage_id() {
		return postage_id;
	}
	public void setPostage_id(String postage_id) {
		this.postage_id = postage_id;
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
	public String getPostageModeType() {
		return postageModeType;
	}
	public void setPostageModeType(String postageModeType) {
		this.postageModeType = postageModeType;
	}
	public String getPostageModeDest() {
		return postageModeDest;
	}
	public void setPostageModeDest(String postageModeDest) {
		this.postageModeDest = postageModeDest;
	}
	public String getPostageModePrice() {
		return postageModePrice;
	}
	public void setPostageModePrice(String postageModePrice) {
		this.postageModePrice = postageModePrice;
	}
	public String getPostageModeIncrease() {
		return postageModeIncrease;
	}
	public void setPostageModeIncrease(String postageModeIncrease) {
		this.postageModeIncrease = postageModeIncrease;
	}
	public String getPostageModeId() {
		return postageModeId;
	}
	public void setPostageModeId(String postageModeId) {
		this.postageModeId = postageModeId;
	}
}
