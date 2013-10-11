package com.noplan.services;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noplan.data.TrackDTO;
import com.noplan.persistence.repositories.TrackRepository;

/**
 * This is the main service for tracks
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/track")
@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackRepository trackRepository;

	@Override
	public List<TrackDTO> getAllTracksForConference(Long conferenceId) {
		return trackRepository.getAllTrackDTOsForConference(conferenceId);
	}

	@Override
	public TrackDTO getTrackById(Long trackId) {
		return trackRepository.getTrackDTOById(trackId);
	}

	@Override
	public TrackDTO createTrack(TrackDTO track) {
		return trackRepository.createTrack(track);
	}

	@Override
	public TrackDTO updateTrack(TrackDTO track) {
		return trackRepository.updateTrack(track);
	}

	@Override
	public void deleteTrackById(Long trackid) {
		trackRepository.deleteTrackById(trackid);
	}


}
