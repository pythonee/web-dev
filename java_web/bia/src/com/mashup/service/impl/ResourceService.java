package com.mashup.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mashup.dao.IResourcesDAO;
import com.mashup.dao.IResourcesRoleDAO;
import com.mashup.dao.impl.ResourcesRoleDAO;
import com.mashup.domain.Resources;
import com.mashup.domain.ResourcesRole;
import com.mashup.domain.Role;
import com.mashup.service.IResourceService;

public class ResourceService implements IResourceService{
	
	private IResourcesDAO resourcesDAO;
	private IResourcesRoleDAO resourcesRoleDAO;
	
	/* (non-Javadoc)
	 * @see com.mashup.service.IResourceService#add(com.mashup.domain.Resources)
	 */
	@Override
	public void insertResource(Resources resource) {
		// TODO Auto-generated method stub
		resourcesDAO.save(resource);
	}

	/* (non-Javadoc)
	 * @see com.mashup.service.IResourceService#del(com.mashup.domain.Resources)
	 */
	@Override
	public void deleteResource(Integer id) {
		// TODO Auto-generated method stub
		resourcesDAO.delete(resourcesDAO.findById(id));
	}

	/* (non-Javadoc)
	 * @see com.mashup.service.IResourceService#find(int)
	 */
	@Override
	public Resources find(int id) {
		// TODO Auto-generated method stub
		return resourcesDAO.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.mashup.service.IResourceService#update(com.mashup.domain.Resources)
	 */
	@Override
	public void updateResource(Resources resource) {
		// TODO Auto-generated method stub
		
	}
	
	public List<String> getResourcesType(){
		
		List<Resources> resList = resourcesDAO.findAll();
		
		List<String> types = new ArrayList<String>();
		
		for (Resources resource : resList) {
			types.add(resource.getResourceType());
		}
		
		return types;
		
	}
	
	public void batchRemove(List<Integer> ids){
		resourcesDAO.batchRemove(ids);
	}
	
	public void setResourceRole(Resources resources,Role role){

	}

	/* (non-Javadoc)
	 * @see com.mashup.service.IResourceService#findAll()
	 */
	@Override
	public List<Resources> findAll() {
		// TODO Auto-generated method stub
		return resourcesDAO.findAll();
	}

	/**
	 * @param resourcesDAO the resourcesDAO to set
	 */
	public void setResourcesDAO(IResourcesDAO resourcesDAO) {
		this.resourcesDAO = resourcesDAO;
	}

	/**
	 * @param resourcesRoleDAO the resourcesRoleDAO to set
	 */
	public void setResourcesRoleDAO(IResourcesRoleDAO resourcesRoleDAO) {
		this.resourcesRoleDAO = resourcesRoleDAO;
	}
}
