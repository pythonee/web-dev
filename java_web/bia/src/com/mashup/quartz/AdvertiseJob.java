package com.mashup.quartz;

import com.mashup.service.IAdvertiseService;

public class AdvertiseJob {
	IAdvertiseService advertiseService;

	public IAdvertiseService getAdvertiseService() {
		return advertiseService;
	}

	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}
	/**
	 * 定时清理过期广告
	 */
	public void delete(){
		advertiseService.deleteOutOfDate();
	}
	

}
