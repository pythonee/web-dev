package com.taobao.api.model;

import java.util.Date;
import java.util.List;

/**
 * 商品属性响应封装。
 * 
 * @author fengsheng
 * @since 1.0, Sep 24, 2009
 */
public class ItemPropsResponse extends TaobaoListResponse {

	private static final long serialVersionUID = 816529945696701178L;

	private List<ItemProp> itemProps;
	private Date lastModifled;

	public ItemPropsResponse() {
		super();
	}

	public ItemPropsResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public List<ItemProp> getItemProps() {
		return itemProps;
	}

	public void setItemProps(List<ItemProp> itemProps) {
		this.itemProps = itemProps;
	}

	public Date getLastModifled() {
		return lastModifled;
	}

	public void setLastModifled(Date lastModifled) {
		this.lastModifled = lastModifled;
	}

}
