/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * The response result of the calling of the taobao.item.update.showcase,
 * which are iid of the item and the modified time. 
 * 
 * @author biyi
 * 
 */
@Deprecated
public class ItemUpdateShowcaseResponse extends ItemRecommendAddResponse {
	private static final long serialVersionUID = 4221038364368073968L;
	
	public ItemUpdateShowcaseResponse() {
		super();
	}

	public ItemUpdateShowcaseResponse(TaobaoResponse rsp) {
		super(rsp);
	}
	
	public ItemUpdateShowcaseResponse(ItemRecommendAddResponse rsp) {
		super(rsp);
		super.setItem(rsp.getItem());
	}
}
