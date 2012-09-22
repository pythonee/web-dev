package com.taobao.api.model;

/**
 * taobao.item.tbsupdate TBS专用
 * 
 * @author jeck.xie 2009-8-18
 */
public class ItemTbsUpdateRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = 5441770064222550606L;

	private String iid;
	
	/**
	 * 是否在淘宝显示
	 */
	private Boolean isTaobao;

	/**
	 * 是否在外部网店显示
	 */
	private Boolean isEx;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
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

}
