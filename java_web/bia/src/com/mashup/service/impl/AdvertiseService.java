package com.mashup.service.impl;

import com.mashup.domain.Advertise;
import com.mashup.service.IAdvertiseService;
import com.mashup.dao.IAdvertiseDAO;

import java.util.*;

public class AdvertiseService implements IAdvertiseService
{
	IAdvertiseDAO advertiseDAO;
	
	public List findAll()
	{
		return advertiseDAO.findAll();
	}
	
	public Advertise getAdvertiseByID(Integer id){
		return advertiseDAO.findById(id);
	}
	
	
	public void insertAdvertise(Advertise ad){
		advertiseDAO.save(ad);
	}
	
	public void updateAdvertise(Advertise ad){
		advertiseDAO.attachDirty(ad);
	}
	
	public void removeAdvertiseById(Integer id){
		advertiseDAO.delete(advertiseDAO.findById(id));
	}

	public void batchRemoveAdvertise(List ls){
		
	}
	
	public List findSideBarAd()
	{
		return advertiseDAO.findByProperty("adPostion", "index.jsp");
	}
	public List findUserSideBarAd()
	{
		return advertiseDAO.findByProperty("adPostion", "user/sidebar.jsp");
	}
	public List findAdByPosition(String position)
	{
		return advertiseDAO.findByProperty("adPostion", position);
	}
	
	public void deleteOutOfDate(){
		advertiseDAO.deleteOutOfDate();
	}
	
	/**
	 * @return the advertiseDAO
	 */
	public IAdvertiseDAO getAdvertiseDAO()
	{
		return advertiseDAO;
	}

	
	/**
	 * @param advertiseDAO the advertiseDAO to set
	 */
	public void setAdvertiseDAO(IAdvertiseDAO advertiseDAO)
	{
		this.advertiseDAO = advertiseDAO;
	}
	
	
}
