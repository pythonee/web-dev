package com.mashup.service;

import java.util.List;

import com.mashup.domain.Resources;

public interface IResourceService {
	
	public void insertResource(Resources resource);
	public void deleteResource(Integer id);
	public void updateResource(Resources resource);
	public List<Resources> findAll();
	public Resources find(int id);
	public List<String> getResourcesType();
	public void batchRemove(List<Integer> ids);
	
}
