package com.mashup.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.apache.commons.lang.StringUtils;

/**
 * Resources entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Resources implements java.io.Serializable
{

	// Fields
	private Integer resourceId;
	private String resourceName;
	private String resourceType;
	private String resourceValue;
	private String resourceDesc;
	private Set<ResourcesRole> resourcesRoles = new HashSet<ResourcesRole>(0);

	
	// Constructors

	/** default constructor */
	public Resources()
	{
	}

	/** minimal constructor */
	public Resources(String resourceName, String resourceType,
			String resourceValue, String desc)
	{
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.resourceValue = resourceValue;
		this.resourceDesc = desc;
	}

	/** full constructor */
	public Resources(String resourceName, String resourceType,
			String resourceValue, String desc, Set resourcesRoles)
	{
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.resourceValue = resourceValue;
		this.resourceDesc = desc;
		this.resourcesRoles = resourcesRoles;
	}
	
	public String getRoleAuthorities() {
    	String[] roleAuthorities = new String[this.resourcesRoles.size()];
    	for(ResourcesRole resourcesRole : resourcesRoles) {   		
    		for(int i=0;i<roleAuthorities.length;i++){
    			roleAuthorities[i] = resourcesRole.getRole().getRoleName();
    		}
    	}
        return StringUtils.join(roleAuthorities,",");
    }
	

	// Property accessors

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Resource Name: ");
		toString.append(this.getResourceName());
		return toString.toString();
	}

	public Integer getResourceId()
	{
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId)
	{
		this.resourceId = resourceId;
	}

	public String getResourceName()
	{
		return this.resourceName;
	}

	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}

	public String getResourceType()
	{
		return this.resourceType;
	}

	public void setResourceType(String resourceType)
	{
		this.resourceType = resourceType;
	}

	public String getResourceValue()
	{
		return this.resourceValue;
	}

	public void setResourceValue(String resourceValue)
	{
		this.resourceValue = resourceValue;
	}


	/**
	 * @return the resourceDesc
	 */
	public String getResourceDesc() {
		return resourceDesc;
	}

	/**
	 * @param resourceDesc the resourceDesc to set
	 */
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	/**
	 * @param resourcesRoles the resourcesRoles to set
	 */
	public void setResourcesRoles(Set<ResourcesRole> resourcesRoles) {
		this.resourcesRoles = resourcesRoles;
	}

	public Set getResourcesRoles()
	{
		return this.resourcesRoles;
	}
}