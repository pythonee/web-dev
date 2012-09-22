package com.taobao.api.model;

import java.util.List;

/**
 * 淘客商品查询返回结果
 * @author gaoweibin.tw
 *
 */
public class TaokeItemsGetResponse extends TaobaoListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5107419293480748020L;
	
	private List<TaokeItem> items;
	
	public TaokeItemsGetResponse(){
		super();
	}
	
	public TaokeItemsGetResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public List<TaokeItem> getItems() {
		return items;
	}

	public void setItems(List<TaokeItem> items) {
		this.items = items;
	}
	
}
