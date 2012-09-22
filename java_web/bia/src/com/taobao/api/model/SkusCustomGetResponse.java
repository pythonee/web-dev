package com.taobao.api.model;

import java.util.List;

/**
 * 
 * @author moling
 * @since 1.0, 2009-9-23
 */
public class SkusCustomGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = -4807365050099813979L;

	private List<Sku> skus;

	public SkusCustomGetResponse() {
		super();
	}

	public SkusCustomGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

}
