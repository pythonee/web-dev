package com.mashup.dao;

import java.util.List;

import com.mashup.domain.IpTrack;

public interface IIpTrackDAO {
	public void save(IpTrack transientInstance);

	public void delete(IpTrack persistentInstance);

	public IpTrack findById(java.lang.Integer id);

	public List<IpTrack> findByExample(IpTrack instance);

	public List<IpTrack> findByProperty(String propertyName, Object value);

	public List<IpTrack> findByIpAddress(Object ipAddress);

	public List<IpTrack> findByProductId(Object productId);

	public List<IpTrack> findAll();
	
	public List<IpTrack> findLatestAll(String ip);

	public boolean isExisted(String ip,int pid);
	
	public void clear();
	
	public void attachDirty(IpTrack instance);
}
