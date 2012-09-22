package com.taobao.api.model;

import java.util.List;

/**
 * 查询类目的Feature属性
 * 
 * @author jeck218@gmail.com 2009-9-5
 */
public class FeaturesGetResponse extends TaobaoResponse {
	//
	private static final long serialVersionUID = 2021032571676925528L;
	private List<Feature> features;

	public FeaturesGetResponse() {
		super();
	}

	public FeaturesGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

}
