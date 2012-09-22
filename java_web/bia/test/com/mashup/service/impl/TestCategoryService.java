/**
 * 
 */
package com.mashup.service.impl;

import org.junit.Test;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Category;
import com.mashup.service.ICategoryService;

/**
 * @author Administrator
 * 
 */
public class TestCategoryService extends BaseTransactionalDataSourceTests {
	ICategoryService categoryService;

	/**
	 * {@link com.mashup.service.impl.CategoryService#findAll()} 的测试方法。
	 */
	@Test
	public void testFindAll() {
		// 测试findAll函数能否返回所有商品类别
		assertEquals(68, categoryService.findAll().size());
		log.debug("findAll()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CategoryService#getCategoryById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testGetCategoryById() {
		// 测试能否根据某一个categoryId来获取一个Category的对象
		assertEquals("彩票", categoryService.getCategoryById(12)
				.getCategoryName());
		log.debug("getCategoryById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CategoryService#updateCategory(com.mashup.domain.Category)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdateCategory() {
		// 测试更新一个类别是否成功
		Category category = new Category();
		category.setCategoryId(12);
		category.setCategoryName("体育彩票");
		category.setTags("彩票 大乐透 排三 双色球 足彩");
		category.setFatherId(3);
		categoryService.updateCategory(category);
		assertEquals("体育彩票", categoryService.getCategoryById(12)
				.getCategoryName());
		log.debug("updateCategory()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CategoryService#insertCategory(com.mashup.domain.Category)}
	 * 的测试方法。
	 */
	@Test
	public void testInsertCategory() {
		// 测试插入一个类别是否成功
		Category category = new Category();
		category.setCategoryName("大片");
		category.setTags("欧美 日韩 东南亚 香港 大陆");
		category.setFatherId(8);
		int pre = categoryService.findAll().size();
		categoryService.insertCategory(category);
		assertEquals(pre + 1, categoryService.findAll().size());
		log.debug("insertCategory()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CategoryService#removeCategoryById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testRemoveCategoryById() {
		// 测试删除一个类别是否成功
		int pre = categoryService.findAll().size();
		categoryService.removeCategoryById(12);
		assertEquals(pre - 1, categoryService.findAll().size());
		log.debug("removeCategoryById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CategoryService#batchRemoveCategory(java.util.List)}
	 * 的测试方法。
	 */
	@Test
	public void testBatchRemoveCategory() {
		// 方法未实现
	}

	/**
	 * {@link com.mashup.service.impl.CategoryService#findByFatherId(int)}
	 * 的测试方法。
	 */
	@Test
	public void testFindByFatherId() {
		// 测试通过fatherId能否成功返回一些类别
		assertEquals(6, categoryService.findByFatherId(1).size());
		log.debug("findByFatherId()完成测试");
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public ICategoryService getCategoryService() {
		return this.categoryService;
	}
}
