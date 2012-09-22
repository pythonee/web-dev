package com.mashup.dao;

import java.util.Date;
import java.util.List;

import com.mashup.domain.Adcategory;
import com.mashup.domain.Advertise;

public interface IAdvertiseDAO {
	public void save(Advertise transientInstance);

	public void delete(Advertise persistentInstance);

	public Advertise findById(java.lang.Integer id);

	public List findByExample(Advertise instance);

	public List findByProperty(String propertyName, Object value);

	public List findByAdDesc(Object adDesc);
	public List findByAdPostion(Object adPostion);
	public List findByTarget(Object target);
	public List findByAdName(Object adName);

	public List findByAdPath(Object adPath);
	public void deleteOutOfDate();
	public List findAll();

	public Advertise merge(Advertise detachedInstance);

	public void attachDirty(Advertise instance);
}
