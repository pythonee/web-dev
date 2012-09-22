package com.mashup.domain;

/**
 * ResourcesRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ResourcesRole implements java.io.Serializable
{

	// Fields

	private Integer resourcesRoleId;
	private Resources resources;
	private Role role;

	// Constructors

	/** default constructor */
	public ResourcesRole()
	{
	}

	/** full constructor */
	public ResourcesRole(Resources resources, Role role)
	{
		this.resources = resources;
		this.role = role;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Resource Name: ");
		toString.append(this.getResources().getResourceName());
		toString.append("Role Name:");
		toString.append(this.getRole().getRoleName());
		return toString.toString();
	}

	// Property accessors

	public Integer getResourcesRoleId()
	{
		return this.resourcesRoleId;
	}

	public void setResourcesRoleId(Integer resourcesRoleId)
	{
		this.resourcesRoleId = resourcesRoleId;
	}

	public Resources getResources()
	{
		return this.resources;
	}

	public void setResources(Resources resources)
	{
		this.resources = resources;
	}

	public Role getRole()
	{
		return this.role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

}