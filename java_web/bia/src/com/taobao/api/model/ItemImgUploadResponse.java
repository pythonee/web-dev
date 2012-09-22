package com.taobao.api.model;

/**
 * 添加商品图片返回的Response
 * @author gaoweibin.tw
 *
 */
public class ItemImgUploadResponse extends TaobaoResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -435859406410369912L;
	private ItemImg itemImg;
	
	public ItemImgUploadResponse(){
		super();
	}
	public ItemImgUploadResponse(TaobaoResponse response){
		super(response);
	}
	public ItemImg getItemImg() {
		return itemImg;
	}
	public void setItemImg(ItemImg itemImg) {
		this.itemImg = itemImg;
	}
	
}
