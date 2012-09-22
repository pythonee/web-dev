package com.mashup.domain;

import java.sql.Timestamp;

/**
 * IpTrack entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class IpTrack implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String ipAddress;
	private Integer productId;
	private Integer times;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public IpTrack()
	{
	}

	/** full constructor */
	public IpTrack(String ipAddress, Integer productId, Integer times, Timestamp time)
	{
		this.ipAddress = ipAddress;
		this.productId = productId;
		this.times = times;
		this.time = time;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getIpAddress()
	{
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public Integer getProductId()
	{
		return this.productId;
	}

	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}

	public Integer getTimes()
	{
		return this.times;
	}

	public void setTimes(Integer times)
	{
		this.times = times;
	}

	public Timestamp getTime()
	{
		return this.time;
	}

	public void setTime(Timestamp time)
	{
		this.time = time;
	}

}