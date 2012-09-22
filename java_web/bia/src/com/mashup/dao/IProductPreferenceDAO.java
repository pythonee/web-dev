package com.mashup.dao;

import java.util.List;

import com.mashup.domain.ProductPreference;


public interface IProductPreferenceDAO
{
	public void save(ProductPreference transientInstance);

	public void delete(ProductPreference persistentInstance);

	public ProductPreference findById(java.lang.Integer id);

	public List findByExample(ProductPreference instance);

	public List findByProperty(String propertyName, Object value);
	
	public List findByPreference(Object preference);

	public List findByTimestamp(Object timestamp);
	
	public List findAll();
}
