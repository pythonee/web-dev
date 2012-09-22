package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Sensitiveword;


public interface ISensitivewordDAO
{
	public void save(Sensitiveword transientInstance);

	public void delete(Sensitiveword persistentInstance);

	public Sensitiveword findById(java.lang.Integer id);

	public List findByExample(Sensitiveword instance);

	public List findByProperty(String propertyName, Object value);

	public List findBySensitiveWord(Object sensitiveWord);

	public List findAll();

	public void attachDirty(Sensitiveword instance);

}
