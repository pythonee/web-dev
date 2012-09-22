/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 *
 */
public class ItemCatsListGetRequest extends ItemCatsGetRequest{

	//
	private static final long serialVersionUID = 2014530343872208021L;
	public ItemCatsListGetRequest() {
		super();
	}
	
	public ItemCatsListGetRequest withParentCid(String parentCid){
		return (ItemCatsListGetRequest)super.withParentCid(parentCid);
	}
	public ItemCatsListGetRequest withCids(String cids){
		return (ItemCatsListGetRequest)super.withCids(cids);
	}
	
	
	
}
