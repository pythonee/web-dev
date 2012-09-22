package com.mashup.dao;

import java.util.List;

import com.mashup.domain.ResourcesRole;


public interface IResourcesRoleDAO
{
	public void save(ResourcesRole transientInstance);

	public void delete(ResourcesRole persistentInstance);

	public ResourcesRole findById(java.lang.Integer id);

	public List findByExample(ResourcesRole instance);

	public List findByProperty(String propertyName, Object value);

	public List findAll();
}
