package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Track;

public interface ITrackDAO {
	public void save(Track transientInstance);

	public void delete(Track persistentInstance);

	public Track findById(java.lang.Integer id);

	public List findByExample(Track instance);

	public List findByProperty(String propertyName, Object value);

	public List findByIpAdress(Object ipAdress);

	public List findByUserName(Object userName);

	public List findByPageName(Object pageName);

	public List findByTime(Object time);

	public List findAll();

	public Track merge(Track detachedInstance);

	public void attachDirty(Track instance);
}
