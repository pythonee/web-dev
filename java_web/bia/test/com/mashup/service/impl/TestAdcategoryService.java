/**
 * 
 */
package com.mashup.service.impl;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Adcategory;
import com.mashup.service.IAdcategoryService;

/**
 * @author Administrator
 * 
 */
public class TestAdcategoryService extends BaseTransactionalDataSourceTests {
	IAdcategoryService adcategoryService;

	public void testFindAll() {
		// 测试数据表中的所有记录数是否正确
		assertEquals(2, adcategoryService.findAll().size());
		log.debug("findAll()完成测试");
	}

	public void testGetAdcategoryById() {
		// 测试根据id，某一个记录是否返回相应的category name
		assertEquals("flash", adcategoryService.getAdcategoryById(1)
				.getAdCategoryName());
		log.debug("getAdcategoryById()完成测试");
	}

	public void testUpdateAdcategory() {
		// 更新一个广告类别
		Adcategory adcategory = new Adcategory();
		adcategory.setAdCategoryId(1);
		adcategory.setAdCategoryName("flash type");
		adcategoryService.updateAdcategory(adcategory);
		assertEquals("flash type", adcategoryService.getAdcategoryById(1)
				.getAdCategoryName());
		log.debug("updateAdcategory()完成测试");
	}

	public void testInsertAdcategory() {
		// 插入一个新的广告类别
		Adcategory adcategory = new Adcategory("movie");
		int pre = adcategoryService.findAll().size();
		adcategoryService.insertAdcategory(adcategory);
		assertEquals(pre + 1, adcategoryService.findAll().size());
		log.debug("insertAdcategory()完成测试");
	}

	public void testRemoveAdcategoryById() {
		// 测试删除一个广告类别
		int pre = adcategoryService.findAll().size();
		adcategoryService.removeAdcategoryById(1);
		assertEquals(pre - 1, adcategoryService.findAll().size());
		log.debug("removeAdcategoryById()完成测试");
	}

	public void testBatchRemoveAdcategory() {
		// 方法未实现
	}

	public void setAdcategoryService(IAdcategoryService adcategoryService) {
		this.adcategoryService = adcategoryService;
	}

	public IAdcategoryService getAdcategoryService() {
		return this.adcategoryService;
	}
}
