package com.mashup.domain;

import java.util.Date;

/**
 * PersistentLogins entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PersistentLogins implements java.io.Serializable
{

	// Fields

	private String series;
	private String username;
	private String token;
	private Date lastUsed;

	// Constructors

	/** default constructor */
	public PersistentLogins()
	{
	}

	/** full constructor */
	public PersistentLogins(String username, String token, Date lastUsed)
	{
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	// Property accessors

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("User In PersistentLogins: ");
		toString.append(this.getUsername());
		return toString.toString();
	}

	public String getSeries()
	{
		return this.series;
	}

	public void setSeries(String series)
	{
		this.series = series;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getToken()
	{
		return this.token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public Date getLastUsed()
	{
		return this.lastUsed;
	}

	public void setLastUsed(Date lastUsed)
	{
		this.lastUsed = lastUsed;
	}

}