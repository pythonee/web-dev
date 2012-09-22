package com.taobao.api.model;

/**
 * 物流合作伙伴信息查询
 * @author daotong
 *
 */
public class LogisticsPartnersGetRequest extends TaobaoRequest{
	private static final long serialVersionUID = 245779617234550257L;
	private String sourceId;
	private String targetId;
	private String serviceType;
	private String goodsValue;

	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getGoodsValue() {
		return goodsValue;
	}
	public void setGoodsValue(String goodsValue) {
		this.goodsValue = goodsValue;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	
}
