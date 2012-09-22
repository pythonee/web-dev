package com.mashup.domain;

/**
 * UserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserRole implements java.io.Serializable
{

	// Fields

	private Integer userRoleId;
	private User user;
	private Role role;

	// Constructors

	/** default constructor */
	public UserRole()
	{
	}

	/** full constructor */
	public UserRole(User user, Role role)
	{
		this.user = user;
		this.role = role;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("User Name: ");
		toString.append(this.getUser().getUsername());
		toString.append("Role Name: ");
		toString.append(this.getRole().getRoleName());
		return toString.toString();
	}
	
	// Property accessors

	public Integer getUserRoleId()
	{
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId)
	{
		this.userRoleId = userRoleId;
	}

	public User getUser()
	{
		return this.user;
	}

	public void setUser(User user)
	{
		this.user = user;
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