package com.noplan.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.noplan.data.ConferenceDTO;
import com.noplan.data.EventDTO;
import com.noplan.data.TrackDTO;
import com.noplan.persistence.entity.ConferenceEntity;
import com.noplan.persistence.entity.EventEntity;
import com.noplan.persistence.entity.TrackEntity;
import com.noplan.persistence.entity.UserEntity;
import com.noplan.persistence.entity.UserEventMappingEntity;
import com.noplan.persistence.repositories.EventRepository;
import com.noplan.persistence.repositories.UserEventMappingRepository;
import com.noplan.persistence.repositories.UserRepository;

/**
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/userevents")
@Service
public class UserEventsServiceImpl implements UserEventsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserEventMappingRepository mappingRepository;

	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<ConferenceDTO> getConferencesForUser() {
		List<ConferenceDTO> result = new ArrayList<ConferenceDTO>();
		for (ConferenceEntity conf : getConferencesEntitiesForUser()) {
			result.add(conf.toDTO());
		}
		return result;
	}

	public List<ConferenceEntity> getConferencesEntitiesForUser() {

		UserEntity user = getLoggedInUser();
		if (user == null) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No user is logged in!");
		}

		Map<Long, ConferenceEntity> conferences = new HashMap<Long, ConferenceEntity>();
		for (UserEventMappingEntity mapping : mappingRepository.getMappingsForUser(user)) {
			ConferenceEntity conf = mapping.getEvent().getTrack().getConference();

			conferences.put(conf.getId(), conf);
		}

		return new ArrayList<ConferenceEntity>(conferences.values());
	}

	@Override
	public List<TrackDTO> getUserTracksForConference(long conferenceId) {
		List<TrackDTO> result = new ArrayList<TrackDTO>();
		for (TrackEntity track : getUserTrackEntitiesForConference(conferenceId)) {
			result.add(track.toDTO());
		}
		return result;
	}

	public List<TrackEntity> getUserTrackEntitiesForConference(long conferenceId) {

		UserEntity user = getLoggedInUser();
		if (user == null) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No user is logged in!");
		}

		Map<Long, TrackEntity> conferences = new HashMap<Long, TrackEntity>();
		for (UserEventMappingEntity mapping : mappingRepository.getMappingsForUser(user)) {
			TrackEntity track = mapping.getEvent().getTrack();
			if (track.getConference().getId() == conferenceId) {
				conferences.put(track.getId(), track);
			}
		}

		return new ArrayList<TrackEntity>(conferences.values());
	}

	@Override
	public List<EventDTO> getUserEventsForTrack(long trackId) {
		List<EventDTO> result = new ArrayList<EventDTO>();
		for (EventEntity event : getUserEventEntitiessForTrack(trackId)) {
			result.add(event.toDTO());
		}
		return result;
	}

	public List<EventEntity> getUserEventEntitiessForTrack(long trackId) {

		UserEntity user = getLoggedInUser();
		if (user == null) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No user is logged in!");
		}

		List<EventEntity> events = new ArrayList<EventEntity>();
		for (UserEventMappingEntity mapping : mappingRepository.getMappingsForUser(user)) {
			if (mapping.getEvent().getTrack().getId() == trackId) {
				events.add(mapping.getEvent());
			}
		}

		return events;
	}

	@Override
	public void addEvents(List<Long> eventIds) {

		UserEntity user = getLoggedInUser();
		if (user == null) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No user is logged in!");
		}

		for (Long eventId : eventIds) {
			EventEntity event = eventRepository.getEventById(eventId);

			mappingRepository.addMapping(new UserEventMappingEntity(user, event));
		}
	}

	@Override
	public void removeEvents(List<Long> eventIds) {
		UserEntity user = getLoggedInUser();
		if (user == null) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No user is logged in!");
		}

		for (Long eventId : eventIds) {
			EventEntity event = eventRepository.getEventById(eventId);

			mappingRepository.removeMappings(user, event);
		}
	}

	/**
	 * @returns logged in user or null if none is logged in
	 */
	public UserEntity getLoggedInUser() {
		SecurityContext context = SecurityContextHolder.getContext();

		if (context != null && context.getAuthentication() != null && context.getAuthentication().getPrincipal() != null) {
			Object username = context.getAuthentication().getPrincipal();
			if (username instanceof String) {
				return userRepository.getUserByUsername((String) username);
			}
			return null;
		}

		return null;
	}
}
