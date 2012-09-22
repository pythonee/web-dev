/**
 * 
 */
package com.taobao.api.model;


/**
 * 
 * @author liupo
 * 
 */
public class SellerCatsListAddResponse extends TaobaoListResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4166369349979852469L;
	private SellerCat sellerCat;

	public SellerCatsListAddResponse() {
		super();
	}

	public SellerCatsListAddResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public SellerCat getSellerCat() {
		return sellerCat;
	}

	public void setSellerCat(SellerCat sellerCat) {
		this.sellerCat = sellerCat;
	}

	

}
