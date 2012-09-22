/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

/**
 * @author sulinchong.pt
 * 
 */
public class SpuGetResponse extends TaobaoListResponse {
	private static final long serialVersionUID = -4081768192773224275L;
	
	private List<Spu> spus;

	public SpuGetResponse() {
		super();
	}

	public SpuGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<Spu> getSpus() {
		return spus;
	}

	public void setSpus(List<Spu> spus) {
		this.spus = spus;
	}

}
