/**
 * 
 */
package com.taobao.api.model;

import com.taobao.api.model.TaobaoResponse;

/**
 * taobao.trade.memo.add
 * 
 * @author jeck.xie 2009-7-30
 */
public class TradeMemoAddResponse extends TradeMemoResponse {
	//
	private static final long serialVersionUID = -421896264775357953L;

	public TradeMemoAddResponse() {
		super();
	}

	public TradeMemoAddResponse(TaobaoResponse rsp) {
		super(rsp);
	}
}
