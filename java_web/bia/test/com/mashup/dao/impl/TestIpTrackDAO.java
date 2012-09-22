package com.mashup.dao.impl;

import java.util.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.mashup.base.BaseDependencyInjectionTests;
import com.mashup.domain.IpTrack;

public class TestIpTrackDAO extends BaseDependencyInjectionTests
{
	IpTrackDAO ipTrackDAO;
	private final static Logger log = Logger.getLogger(TestIpTrackDAO.class);
	public void testFindLatestAll(){
		for(IpTrack ipTrack : ipTrackDAO.findLatestAll("127.0.0.1")){
			log.debug(ipTrack.getTime()+" === " + ipTrack.getTimes());
		}
	}
	
	public void testSave(){
		IpTrack obj = new IpTrack();
		obj.setIpAddress("127.0.0.1");
		obj.setProductId(2);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		obj.setTime(timestamp);
		obj.setTimes(1);
		ipTrackDAO.save(obj);
		
	}
	
	public void testIsExisted(){
		
		log.debug(ipTrackDAO.isExisted("127.0.0.1", 2));
		
	}
	
	/**
	 * @return the ipTrackDAO
	 */
	public IpTrackDAO getIpTrackDAO()
	{
		return ipTrackDAO;
	}
	
	/**
	 * @param ipTrackDAO the ipTrackDAO to set
	 */
	public void setIpTrackDAO(IpTrackDAO ipTrackDAO)
	{
		this.ipTrackDAO = ipTrackDAO;
	}
	
}
