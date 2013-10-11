package com.noplan.services;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noplan.data.ConferenceDTO;
import com.noplan.persistence.repositories.ConferenceRepository;

/**
 * This is the main service for conferences
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/conference")
@Service
public class ConferenceServiceImpl implements ConferenceService {

	@Autowired
	private ConferenceRepository conferenceRepository;

	@Override
	public List<ConferenceDTO> getAllConferences() {
		return conferenceRepository.getAllConferenceDTOs();
	}

	@Override
	public ConferenceDTO getConferenceById(Long id) {
		return conferenceRepository.getConferenceDTOById(id);
	}

	@Override
	public ConferenceDTO createConference(ConferenceDTO conference) {
		return conferenceRepository.createConference(conference);
	}

	@Override
	public ConferenceDTO updateConference(ConferenceDTO conference) {
		return conferenceRepository.updateConference(conference);
	}

	@Override
	public void deleteConferenceById(Long conferenceId) {
		conferenceRepository.deleteConference(conferenceId);
	}

}
