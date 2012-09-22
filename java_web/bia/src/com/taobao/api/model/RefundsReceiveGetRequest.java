/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * @author sulinchong.pt 2009-2-19 上午10:07:47
 *
 */
public class RefundsReceiveGetRequest extends RefundsRequest{
	private String buyerNick;
	private Date startModified;
	private Date endModified;
	
	public Date getStartModified() {
		return startModified;
	}

	public void setStartModified(Date startModified) {
		this.startModified = startModified;
	}

	public Date getEndModified() {
		return endModified;
	}

	public void setEndModified(Date endModified) {
		this.endModified = endModified;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public RefundsReceiveGetRequest withBuyerNick(String buyerNick){
		setBuyerNick(buyerNick);
		return this;
	}
	@Override
	public RefundsReceiveGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	@Override
	public RefundsReceiveGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	@Override
	public RefundsReceiveGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	@Override
	public RefundsReceiveGetRequest withStatus(String status) {
		setStatus(status);
		return this;
	}

	@Override
	public RefundsReceiveGetRequest withType(String type) {
		setType(type);
		return this;
	}

}
