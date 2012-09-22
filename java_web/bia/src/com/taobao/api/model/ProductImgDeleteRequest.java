package com.taobao.api.model;

/**
 * 调用 taobao.product.img.delete 需要传入的参数
 * 
 * @author liupo <liupo@taobao.com>
 * @version 1.0
 */
public class ProductImgDeleteRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 2177907531052436324L;
	private String productId;
	public static final String PRODUCTID = "product_id";
	private String picId;
	public static final String PICID = "pic_id";
	public static final String ID="id";

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	 @Deprecated
	public String getPicId() {
		return picId;
	}
	public String getId() {
		return picId;
	}
	    
	@Deprecated
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public void setId(String id) {
		this.picId = id;
	}

	public ProductImgDeleteRequest withPicId(String picId) {
		this.setPicId(picId);
		return this;
	}

	public ProductImgDeleteRequest withProductId(String productId) {
		this.setProductId(productId);
		return this;
	}
}
