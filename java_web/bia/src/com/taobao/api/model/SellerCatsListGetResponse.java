/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

/**
 * @author sulinchong.pt
 * 
 */
public class SellerCatsListGetResponse extends TaobaoListResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4166369349979852469L;
	private List<SellerCat> sellerCats;

	public SellerCatsListGetResponse() {
		super();
	}

	public SellerCatsListGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<SellerCat> getSellerCats() {
		return sellerCats;
	}

	public void setSellerCats(List<SellerCat> sellerCats) {
		this.sellerCats = sellerCats;
	}

}
