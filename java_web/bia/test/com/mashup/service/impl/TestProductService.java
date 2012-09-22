/**
 * 
 */
package com.mashup.service.impl;

import org.junit.Test;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Category;
import com.mashup.domain.Product;
import com.mashup.service.ICategoryService;
import com.mashup.service.IProductService;

/**
 * @author Administrator
 * 
 */
public class TestProductService extends BaseTransactionalDataSourceTests {
	IProductService productService;
	ICategoryService categoryService;

	/**
	 * {@link com.mashup.service.impl.ProductService#findAll()} 的测试方法。
	 */
	@Test
	public void testFindAll() {
		assertEquals(5, productService.findAll().size());
		log.debug("findAll()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.ProductService#getProductById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testGetProductById() {
		// 测试根据id来获取product对象
		assertEquals("衣服1", productService.getProductById(8).getProductName());
		log.debug("getProductById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.ProductService#updateProduct(com.mashup.domain.Product)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdateProduct() {
		Product product = productService.getProductById(7);
		product.setProductName("内衣");
		productService.updateProduct(product);
		assertEquals("内衣", productService.getProductById(7).getProductName());
		log.debug("updateProduct()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.ProductService#insertProduct(com.mashup.domain.Product)}
	 * 的测试方法。
	 */
	@Test
	public void testInsertProduct() {
		Category category = categoryService.getCategoryById(3);
		Product product = new Product();
		product.setProductName("男士内衣");
		product.setCategory(category);
		product.setProductDesc("laymu");
		product.setPrice(110.110);
		product.setSource("卓越");
		product.setUrl("http://www.taobao.com");
		product.setProductImg("images/img.jpg");
		product.setLevelClick(4);
		product.setScore(2.1000);
		int pre = productService.findAll().size();
		productService.insertProduct(product);
		assertEquals(pre + 1, productService.findAll().size());
		log.debug("insertProduct()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.ProductService#removeProductById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testRemoveProductById() {
		int pre = productService.findAll().size();
		productService.removeProductById(10);
		assertEquals(pre - 1, productService.findAll().size());
		log.debug("removeProductById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.ProductService#batchRemoveProduct(java.util.List)}
	 * 的测试方法。
	 */
	@Test
	public void testBatchRemoveProduct() {
		// 未实现
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public IProductService getProductService() {
		return this.productService;
	}

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
