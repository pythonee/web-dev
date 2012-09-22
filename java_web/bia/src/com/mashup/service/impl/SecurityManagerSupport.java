package com.mashup.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.velocity.runtime.log.SystemLogChute;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import com.mashup.dao.IUserDAO;
import com.mashup.dao.IResourcesDAO;
import com.mashup.domain.User;
import com.mashup.domain.Resources;
import com.mashup.service.ISecurityManager;

public class SecurityManagerSupport implements ISecurityManager,UserDetailsService {

	IUserDAO userDAO;
	IResourcesDAO resourcesDAO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mashup.utils.SecurityManager#loadUrlAuthorities()
	 */
	public Map<String, String> loadUrlAuthorities() {
		// TODO Auto-generated method stub
	        Map<String, String> urlAuthorities = new HashMap<String, String>();
	        
	        Resources example = new Resources();
	        example.setResourceType("URL");
	        
	        List<Resources> urlResources = resourcesDAO.findByProperty("resourceType", example.getResourceType());
	        for(Resources resource : urlResources) {        	
	            urlAuthorities.put(resource.getResourceValue(),resource.getRoleAuthorities());
	        }
	        
	        return urlAuthorities;
	}
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException
	{
		User user = new User();
		user.setUsername(username);
		user.setStatus(1);
		
		List<User> users = userDAO.findByExample(user);
		
		if(users.isEmpty()){
			throw new UsernameNotFoundException("User" + username + "has not GrantedAuthority");
		}
				
		return users.get(0);
	}

	
	/**
	 * @return the userDAO
	 */
	public IUserDAO getUserDAO()
	{
		return userDAO;
	}

	
	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	/**
	 * @param resourcesDAO the resourcesDAO to set
	 */
	public void setResourcesDAO(IResourcesDAO resourcesDAO) {
		this.resourcesDAO = resourcesDAO;
	}
	/**
	 * @return the resourcesDAO
	 */
	public IResourcesDAO getResourcesDAO() {
		return resourcesDAO;
	}
}
