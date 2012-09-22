package com.mashup.service.impl;

import java.util.List;

import com.mashup.dao.ITrackDAO;
import com.mashup.domain.Track;
import com.mashup.service.ITrackService;

public class TrackService implements ITrackService {

	ITrackDAO trackDAO;

	public List<Track> findAll() {
		List trackList = trackDAO.findAll();

		return trackList;
	}

	public void insertRecord(Track track) {
		trackDAO.save(track);
	}

	public ITrackDAO getTrackDAO() {
		return trackDAO;
	}

	public void setTrackDAO(ITrackDAO trackDAO) {
		this.trackDAO = trackDAO;
	}

}
