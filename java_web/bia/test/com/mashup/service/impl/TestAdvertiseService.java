/**
 * 
 */
package com.mashup.service.impl;

import java.util.Date;

import org.junit.Test;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Adcategory;
import com.mashup.domain.Advertise;
import com.mashup.service.IAdvertiseService;

/**
 * @author Administrator
 * 
 */
public class TestAdvertiseService extends BaseTransactionalDataSourceTests {
	IAdvertiseService advertiseService;

	/**
	 * {@link com.mashup.service.impl.AdvertiseService#findAll()} 的测试方法。
	 */
	@Test
	public void testFindAll() {
		// 测试findAll()方法
		assertEquals(1, advertiseService.findAll().size());
		log.debug("findAll()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.AdvertiseService#getAdvertiseByID(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testGetAdvertiseByID() {
		// 通过id来得到广告对象
		assertEquals("C罗的战靴", advertiseService.getAdvertiseByID(1).getAdDesc());
		log.debug("getAdvertiseByID()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.AdvertiseService#insertAdvertise(com.mashup.domain.Advertise)}
	 * 的测试方法。
	 */
	@Test
	public void testInsertAdvertise() {
		Adcategory adcategory = new Adcategory();
		adcategory.setAdCategoryId(1);
		Date dt = new Date();
		Advertise advertise = new Advertise(adcategory, "C罗的战靴", "第三广告位",
				"足球鞋", dt, dt, "NIKE最新战靴","");

		int pre = advertiseService.findAll().size();
		advertiseService.insertAdvertise(advertise);
		assertEquals(pre + 1, advertiseService.findAll().size());
		log.debug("insertAdvertise()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.AdvertiseService#updateAdvertise(com.mashup.domain.Advertise)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdateAdvertise() {
		// 测试更新广告是否成功
		Adcategory adcategory = new Adcategory();
		adcategory.setAdCategoryId(1);
		Date dt = new Date();
		Advertise advertise = new Advertise(adcategory, "梅西的战靴", "第三广告位",
				"足球鞋", dt, dt, "NIKE最新战靴","");
		advertise.setAdvertiseId(1);

		advertiseService.updateAdvertise(advertise);
		assertEquals("梅西的战靴", advertiseService.getAdvertiseByID(1).getAdDesc());
		log.debug("updateAdvertise()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.AdvertiseService#removeAdvertiseById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testRemoveAdvertiseById() {
		// 测试删除广告是否成功
		int pre = advertiseService.findAll().size();
		advertiseService.removeAdvertiseById(1);
		assertEquals(pre - 1, advertiseService.findAll().size());
		log.debug("removeAdvertiseById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.AdvertiseService#batchRemoveAdvertise(java.util.List)}
	 * 的测试方法。
	 */
	@Test
	public void testBatchRemoveAdvertise() {
		// 方法未实现
	}
	
	public void testDeleteOutOfDate(){
		advertiseService.deleteOutOfDate();
		log.debug("deleteOutOfDate");
	}

	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}

	public IAdvertiseService getAdvertiseService() {
		return this.advertiseService;
	}

}
