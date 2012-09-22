package com.mashup.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable
{

	// Fields

	private Integer roleId;
	private String roleName;
	private String desc;
	private Set userRoles = new HashSet(0);
	private Set resourcesRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role()
	{
	}

	/** minimal constructor */
	public Role(String roleName, String desc)
	{
		this.roleName = roleName;
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Role Name: ");
		toString.append(this.getRoleName());
		return toString.toString();
	}

	/** full constructor */
	public Role(String roleName, String desc, Set userRoles, Set resourcesRoles)
	{
		this.roleName = roleName;
		this.desc = desc;
		this.userRoles = userRoles;
		this.resourcesRoles = resourcesRoles;
	}

	// Property accessors

	public Integer getRoleId()
	{
		return this.roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return this.roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getDesc()
	{
		return this.desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public Set getUserRoles()
	{
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles)
	{
		this.userRoles = userRoles;
	}

	public Set getResourcesRoles()
	{
		return this.resourcesRoles;
	}

	public void setResourcesRoles(Set resourcesRoles)
	{
		this.resourcesRoles = resourcesRoles;
	}

}