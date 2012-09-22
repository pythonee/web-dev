package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Category;
import com.mashup.domain.Friend;

public interface IFriendDAO {

	public void save(Friend transientInstance);

	public void delete(Friend persistentInstance);

	public Friend findById(java.lang.Integer id);

	public List findByExample(Friend instance);
	public List findByProperty(String propertyName, Object value);

	public List findAll();
	public void attachDirty(Friend instance);
	

}
