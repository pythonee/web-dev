package com.mashup.domain;

/**
 * Track entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Track implements java.io.Serializable {

	// Fields

	private Integer trackId;
	private String ipAdress;
	private String userName;
	private String pageName;
	private Integer time;

	// Constructors

	/** default constructor */
	public Track() {
	}

	/** full constructor */
	public Track(String ipAdress, String userName, String pageName, Integer time) {
		this.ipAdress = ipAdress;
		this.userName = userName;
		this.pageName = pageName;
		this.time = time;
	}

	// Property accessors

	public Integer getTrackId() {
		return this.trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	public String getIpAdress() {
		return this.ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}