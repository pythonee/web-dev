package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Role;


public interface IRoleDAO
{
	public void save(Role transientInstance);

	public void delete(Role persistentInstance);

	public Role findById(java.lang.Integer id);

	public List findByExample(Role instance);

	public List findByProperty(String propertyName, Object value);
	
	public List findByRoleName(Object roleName);
	
	public List findByDesc(Object desc);
	
	public List findAll();

}
