package com.taobao.api.model;

/**
 * 卖家授权信息查询---返回参数
 * 
 * @author jeck.xie 2009-3-25
 * @see ItemCatsAuthorizeGetRequest
 */
@Deprecated
public class SellerAuthorizeGetResponse extends ItemCatsAuthorizeGetResponse {

	private static final long serialVersionUID = 2740094589642015112L;

	public SellerAuthorizeGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public SellerAuthorizeGetResponse(ItemCatsAuthorizeGetResponse rsp) {
		super(rsp);
		super.setAuthorizes(rsp.getAuthorizes());
		super.setTotalResults(rsp.getTotalResults());
	}

}
