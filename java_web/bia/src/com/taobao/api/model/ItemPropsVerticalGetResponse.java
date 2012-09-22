package com.taobao.api.model;

import java.util.List;


public class ItemPropsVerticalGetResponse extends TaobaoListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8593487806006317720L;
	
	private List<ItemVerticalProp> itemVerticalProps;

	public ItemPropsVerticalGetResponse(){
		super();
	}
	
	public ItemPropsVerticalGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<ItemVerticalProp> getItemVerticalProps() {
		return itemVerticalProps;
	}

	public void setItemVerticalProps(List<ItemVerticalProp> itemVerticalProps) {
		this.itemVerticalProps = itemVerticalProps;
	}
}
