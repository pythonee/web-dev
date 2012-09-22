package com.mashup.service;

import java.util.List;

import com.mashup.domain.Advertise;


public interface IAdvertiseService
{
	public List findAll();
	
	public Advertise getAdvertiseByID(Integer id);
	
	
	public void insertAdvertise(Advertise ad);
	
	public void updateAdvertise(Advertise ad);
	
	public void removeAdvertiseById(Integer id);

	public void batchRemoveAdvertise(List ls);
	public List findSideBarAd();
	public List findUserSideBarAd();
	public List findAdByPosition(String position);
	public void deleteOutOfDate();
}
