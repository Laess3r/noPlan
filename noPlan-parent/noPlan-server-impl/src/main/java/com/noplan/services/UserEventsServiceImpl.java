package com.noplan.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.noplan.UserRoles;
import com.noplan.data.ConferenceDTO;
import com.noplan.data.EventDTO;
import com.noplan.data.TrackDTO;
import com.noplan.data.UserDTO;
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

	@Transactional(readOnly = true)
	public List<ConferenceEntity> getConferencesEntitiesForUser() {

		UserEntity user = getLoggedInUser();
		if (user == null) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No user is logged in!");
		}

		Map<Long, ConferenceEntity> conferences = mappingRepository.getConferenceEntitiesForUser(user);

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

		Map<Long, TrackEntity> conferences = mappingRepository.getTrackEntitiesForUser(conferenceId, user);

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

		return mappingRepository.getEventEntitiesForUser(trackId, user);
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

	@Override
	public UserDTO getLoggedInUserDTO() {
		UserEntity user = getLoggedInUser();
		if (user == null) {
			return null;
		}
		return user.toDTO(userRepository.hasRole(user.getId(), UserRoles.ADMIN_ROLE));
	}

	/**
	 * @returns logged in user or null if none is logged in
	 */
	public UserEntity getLoggedInUser() {
		SecurityContext context = SecurityContextHolder.getContext();

		if (context != null && context.getAuthentication() != null && context.getAuthentication().getPrincipal() != null) {
			Object user = context.getAuthentication().getPrincipal();
			if (user instanceof User) {
				String username = ((User) user).getUsername();
				return userRepository.getUserByUsername(username);
			}
			return null;
		}

		return null;
	}
}
