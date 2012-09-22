/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt 2009-2-19 上午09:57:57
 *
 */
public class RefundsApplyGetRequest extends RefundsRequest {
	private String sellerNick;
	
	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public RefundsApplyGetRequest withSellerNick(String sellerNick){
		setSellerNick(sellerNick);
		return this;
	}
	@Override
	public RefundsApplyGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	@Override
	public RefundsApplyGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	@Override
	public RefundsApplyGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	@Override
	public RefundsApplyGetRequest withStatus(String status) {
		setStatus(status);
		return this;
	}

	@Override
	public RefundsApplyGetRequest withType(String type) {
		setType(type);
		return this;
	}

}
