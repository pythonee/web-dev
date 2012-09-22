package com.mashup.domain;

import java.util.Date;

/**
 * Advertise entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Advertise implements java.io.Serializable
{

	// Fields

	private Integer advertiseId;
	private Adcategory adcategory;
	private String adDesc;
	private String adPostion;
	private String target;
	private Date startTime;
	private Date endTime;
	private String adName;
	private String adPath;

	// Constructors

	/** default constructor */
	public Advertise()
	{
	}

	/** minimal constructor */
	public Advertise(Adcategory adcategory, String adDesc, String adPostion,
			Date startTime, Date endTime, String adName, String adPath)
	{
		this.adcategory = adcategory;
		this.adDesc = adDesc;
		this.adPostion = adPostion;
		this.startTime = startTime;
		this.endTime = endTime;
		this.adName = adName;
		this.adPath = adPath;
	}

	/** full constructor */
	public Advertise(Adcategory adcategory, String adDesc, String adPostion,
			String target, Date startTime, Date endTime, String adName,
			String adPath)
	{
		this.adcategory = adcategory;
		this.adDesc = adDesc;
		this.adPostion = adPostion;
		this.target = target;
		this.startTime = startTime;
		this.endTime = endTime;
		this.adName = adName;
		this.adPath = adPath;
	}

	// Property accessors

	public Integer getAdvertiseId()
	{
		return this.advertiseId;
	}

	public void setAdvertiseId(Integer advertiseId)
	{
		this.advertiseId = advertiseId;
	}

	public Adcategory getAdcategory()
	{
		return this.adcategory;
	}

	public void setAdcategory(Adcategory adcategory)
	{
		this.adcategory = adcategory;
	}

	public String getAdDesc()
	{
		return this.adDesc;
	}

	public void setAdDesc(String adDesc)
	{
		this.adDesc = adDesc;
	}

	public String getAdPostion()
	{
		return this.adPostion;
	}

	public void setAdPostion(String adPostion)
	{
		this.adPostion = adPostion;
	}

	public String getTarget()
	{
		return this.target;
	}

	public void setTarget(String target)
	{
		this.target = target;
	}

	public Date getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public String getAdName()
	{
		return this.adName;
	}

	public void setAdName(String adName)
	{
		this.adName = adName;
	}

	public String getAdPath()
	{
		return this.adPath;
	}

	public void setAdPath(String adPath)
	{
		this.adPath = adPath;
	}

}