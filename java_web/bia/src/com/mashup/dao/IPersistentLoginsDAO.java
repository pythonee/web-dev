package com.mashup.dao;

import java.util.List;

import org.hibernate.LockMode;

import com.mashup.domain.PersistentLogins;


public interface IPersistentLoginsDAO
{
	public void save(PersistentLogins transientInstance);

	public void delete(PersistentLogins persistentInstance);

	public PersistentLogins findById(java.lang.String id);

	public List findByExample(PersistentLogins instance);

	public List findByProperty(String propertyName, Object value);
	
	public List findByUsername(Object username);
	
	public List findAll();

	public void attachClean(PersistentLogins instance);
}
