package com.mashup.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mashup.dao.IIpTrackDAO;
import com.mashup.domain.IpTrack;
import com.mashup.domain.Track;
import com.mashup.service.IIpTrackService;
import com.sun.org.apache.regexp.internal.recompile;

public class IpTrackService implements IIpTrackService {
	IIpTrackDAO ipTrackDAO;

	@Override
	public List findByIpAddress(String ipAddress) {
		List ipTrackList = ipTrackDAO.findByIpAddress(ipAddress);
		if (ipTrackList.size() < 10)
			return ipTrackList;

		return ipTrackList.subList(0, 9);
	}
	
	public IpTrack findByIpAndProductID(String ip,int pid){
		IpTrack example = new IpTrack();
		example.setIpAddress(ip);
		example.setProductId(pid);
		List<IpTrack> list = ipTrackDAO.findByExample(example);
		if (list.size() > 0)
		{
			return (IpTrack)list.get(0);
		}
		else {
			return null;
		}
	}

	public void insertIpTrack(IpTrack ipTrack) {
		// TODO Auto-generated method stub
		ipTrackDAO.save(ipTrack);
	}
	
	@Override
	public void clear()
	{
		ipTrackDAO.clear();		
	}

	@Override
	public List<IpTrack> latest(String ip)
	{
		List<IpTrack> list = ipTrackDAO.findLatestAll(ip);
		return list;
	}

	@Override
	public boolean isExisted(String ip,int pid)
	{
		return ipTrackDAO.isExisted(ip, pid);
	}
	
	@Override
	public void update(IpTrack ipTrack)
	{
		ipTrackDAO.attachDirty(ipTrack);
	}

	public IIpTrackDAO getIpTrackDAO() {
		return ipTrackDAO;
	}

	public void setIpTrackDAO(IIpTrackDAO ipTrackDAO) {
		this.ipTrackDAO = ipTrackDAO;
	}

}
