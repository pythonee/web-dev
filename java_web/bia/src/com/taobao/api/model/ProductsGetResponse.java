package com.taobao.api.model;

import java.util.List;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author gaoweibin.tw
 *
 */
public class ProductsGetResponse extends TaobaoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3281146735777993658L;
	
	public ProductsGetResponse(){
		super();
	}
	
	public ProductsGetResponse(TaobaoResponse rsp){
		super(rsp);
	}
	@ApiName("products")
	List<Product> productList;

	@Deprecated
	public List<Product> getProductList() {
		return productList;
	}
	public List<Product> getProducts() {
		return productList;
	}

	@Deprecated
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public void setProducts(List<Product> productList) {
		this.productList = productList;
	}
}
