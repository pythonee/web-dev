/**
 * 
 */
package com.mashup.service.impl;

import org.junit.Test;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Product;
import com.mashup.service.ICollectionService;
import com.mashup.service.IProductService;

/**
 * @author Administrator
 * 
 */
public class TestCollectionService extends BaseTransactionalDataSourceTests {
	ICollectionService collectionService;
	IProductService productService;

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	/**
	 * {@link com.mashup.service.impl.CollectionService#findAll()} 的测试方法。
	 */
	@Test
	public void testFindAll() {
		// 测试是否返回所有收藏的列表
		assertEquals(1, collectionService.findAll().size());
		log.debug("findAll()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CollectionService#getCollectionById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testGetCollectionById() {
		// 测试根据collectionId来获取收藏关系
		assertEquals("4", collectionService.getCollectionById(1).getUserId()
				.toString());
		log.debug("getCollectionById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CollectionService#updateCollection(com.mashup.domain.Collection)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdateCollection() {
		// 测试更新收藏是否成功
		Product product = productService.getProductById(1);
		com.mashup.domain.Collection collection = new com.mashup.domain.Collection();
		collection.setCollectionId(1);
		collection.setUserId(5);
		collection.setProduct(product);
		collectionService.updateCollection(collection);
		assertEquals("5", collectionService.getCollectionById(1).getUserId()
				.toString());
		log.debug("updateCollection()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CollectionService#insertCollection(com.mashup.domain.Collection)}
	 * 的测试方法。
	 */
	@Test
	public void testInsertCollection() {
		// 测试插入收藏是否成功
		Product product = productService.getProductById(10);
		com.mashup.domain.Collection collection = new com.mashup.domain.Collection();
		collection.setUserId(6);
		collection.setProduct(product);
		int pre = collectionService.findAll().size();
		collectionService.insertCollection(collection);
		assertEquals(pre + 1, collectionService.findAll().size());
		log.debug("insertCollection()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CollectionService#removeCollectionById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testRemoveCollectionById() {
		// 测试删除收藏是否成功
		int pre = collectionService.findAll().size();
		collectionService.removeCollectionById(1);
		assertEquals(pre - 1, collectionService.findAll().size());
		log.debug("removeCollectionById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CollectionService#batchRemoveCollection(java.util.List)}
	 * 的测试方法。
	 */
	@Test
	public void testBatchRemoveCollection() {
		// 方法未实现
	}

	/**
	 * {@link com.mashup.service.impl.CollectionService#getCollectionDAO()}
	 * 的测试方法。
	 */
	@Test
	public void testFindByUserId() {
		// 测试根据userId来找出此用户所有的收藏信息
		assertEquals(1, collectionService.findByUserId(4).size());
		log.debug("findByUserId()完成测试");
	}

	public void setCollectionService(ICollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public ICollectionService getCollectionService() {
		return this.collectionService;
	}

}
