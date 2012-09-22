package com.taobao.api.model;

import java.util.List;

/**
 * @author Taylor xuwei840916@hotmail.com
 * @version 2008-12-22 上午10:21:40
 */
public class ItemPropValuesResponse extends TaobaoListResponse {

	private static final long serialVersionUID = 2713617455616139783L;

	private List<PropValue> propValues;

	public ItemPropValuesResponse() {
		super();
	}

	public ItemPropValuesResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<PropValue> getPropValues() {
		return propValues;
	}

	public void setPropValues(List<PropValue> propValues) {
		this.propValues = propValues;
	}

}
