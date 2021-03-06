/**
 * 
 */
package com.taobao.api.model;

/**
 * taobao.trade.memo.add
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradeMemoAddRequest extends TaobaoRequest {
	//
	private static final long serialVersionUID = 5645721901374936603L;

	/**
	 * 交易备注
	 */
	private String memo;

	/**
	 * 淘宝交易号
	 */
	private String tid;

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	public TradeMemoAddRequest withMemo(String memo) {
		setMemo(memo);
		return this;
	}

	public TradeMemoAddRequest withTid(String tid) {
		setTid(tid);
		return this;
	}
}
