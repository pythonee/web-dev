package com.mashup.service;

import java.util.List;

import com.mashup.domain.IpTrack;
import com.mashup.domain.Track;

public interface IIpTrackService {
	public List findByIpAddress(String ipAddress);
	public List<IpTrack> latest(String ip);
	public void clear();
	public boolean isExisted(String ip,int pid);
	public void insertIpTrack(IpTrack ipTrack);
	public IpTrack findByIpAndProductID(String ip,int pid);
	public void update(IpTrack ipTrack);
}
