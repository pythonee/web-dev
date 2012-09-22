package com.mashup.dao;

import java.util.List;

import org.hibernate.LockMode;

import com.mashup.domain.User;
import com.mashup.domain.UserRole;

public interface IUserRoleDAO {
	
	public void save(UserRole transientInstance);

	public void delete(UserRole persistentInstance);

	public UserRole findById(java.lang.Integer id);

	public List findByExample(UserRole instance);

	public List findByProperty(String propertyName, Object value);

	public List findAll();

	public UserRole merge(UserRole detachedInstance);

	public void attachDirty(UserRole instance);

}
