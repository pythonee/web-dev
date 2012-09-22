package com.mashup.service.impl;

import java.util.*;
import com.mashup.domain.Adcategory;
import com.mashup.dao.IAdcategoryDAO;
import com.mashup.service.IAdcategoryService;

public class AdcategoryService implements IAdcategoryService
{
	
	IAdcategoryDAO adcategoryDAO;
	
	
	public List findAll()
	{
		List adcategoryList = adcategoryDAO.findAll();
		
		return adcategoryList;
	}
	
	public Adcategory getAdcategoryById(Integer id){
		return adcategoryDAO.findById(id);
	}

	
	public void updateAdcategory(Adcategory adcategory){
		adcategoryDAO.attachDirty(adcategory);
	}
	
	
	public void insertAdcategory(Adcategory adcategory){
		adcategoryDAO.save(adcategory);
	}
	
	public void removeAdcategoryById(Integer id){
		adcategoryDAO.delete(adcategoryDAO.findById(id));
	}
	
	public void batchRemoveAdcategory(List ls){
		
	}

	public IAdcategoryDAO getAdcategoryDAO() {
		return adcategoryDAO;
	}

	public void setAdcategoryDAO(IAdcategoryDAO adcategoryDAO) {
		this.adcategoryDAO = adcategoryDAO;
	}

	
	
	
	
}

