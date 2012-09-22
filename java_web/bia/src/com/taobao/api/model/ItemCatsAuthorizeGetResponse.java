package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * 卖家授权信息查询---返回参数
 * 
 * @author jeck.xie 2009-3-25
 */
public class ItemCatsAuthorizeGetResponse extends TaobaoListResponse {

	private static final long serialVersionUID = 6874785965300398625L;

	@ApiName("seller_authorize")
	private SellerAuthorize authorizes; // 卖家授权信息

	public ItemCatsAuthorizeGetResponse() {
		super();
	}

	public ItemCatsAuthorizeGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public SellerAuthorize getAuthorizes() {
		return authorizes;
	}

	public void setAuthorizes(SellerAuthorize authorizes) {
		this.authorizes = authorizes;
	}

}
