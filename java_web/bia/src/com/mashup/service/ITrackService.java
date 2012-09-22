package com.mashup.service;

import java.util.List;

import com.mashup.domain.Track;

public interface ITrackService {
	public List<Track> findAll();

	public void insertRecord(Track track);
}
