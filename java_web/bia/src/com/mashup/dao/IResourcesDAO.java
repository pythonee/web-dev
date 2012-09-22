package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Resources;


public interface IResourcesDAO
{
	public void save(Resources transientInstance);


	public void delete(Resources persistentInstance);

	public Resources findById(java.lang.Integer id);

	public List findByExample(Resources instance);

	public List findByProperty(String propertyName, Object value);
	
	public List findByResourceName(Object resourceName);

	public List findByResourceType(Object resourceType);

	public List findByResourceValue(Object resourceValue);
	
	public List findByDesc(Object desc);
	
	public void batchRemove(List<Integer> ids);

	public List findAll();
}
