package com.mashup.dao;

import java.util.List;

import org.hibernate.LockMode;

import com.mashup.domain.Adcategory;

public interface IAdcategoryDAO {
	
	public void save(Adcategory transientInstance) ;
    
	public void delete(Adcategory persistentInstance);
    
    public Adcategory findById( java.lang.Integer id);
    
    public List findByExample(Adcategory instance);
    
    public List findByProperty(String propertyName, Object value);

	public List findByAdCategoryName(Object adCategoryName);
	
	public List findAll() ;

	public void attachDirty(Adcategory instance);
}
