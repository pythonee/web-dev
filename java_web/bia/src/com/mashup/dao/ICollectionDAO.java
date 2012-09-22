package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Category;
import com.mashup.domain.Collection;

public interface ICollectionDAO {
	public void save(Collection transientInstance);

	public void delete(Collection persistentInstance);

	public Collection findById(java.lang.Integer id);

	public List findByExample(Collection instance);

	public List findByProperty(String propertyName, Object value);
	public List getCollectionTopList();
	public List findByUserId(Object userId);
	public List findAll();
	public void attachDirty(Collection instance);
}
