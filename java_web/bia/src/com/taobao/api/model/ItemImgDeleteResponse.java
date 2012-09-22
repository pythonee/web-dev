package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class ItemImgDeleteResponse extends TaobaoResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3112217104969471371L;
	
	private ItemImg itemImg;
	
	public ItemImgDeleteResponse(){
		super();
	}
	public ItemImgDeleteResponse(TaobaoResponse response){
		super(response);
	}
	public ItemImg getItemImg() {
		return itemImg;
	}
	public void setItemImg(ItemImg itemImg) {
		this.itemImg = itemImg;
	}
}
