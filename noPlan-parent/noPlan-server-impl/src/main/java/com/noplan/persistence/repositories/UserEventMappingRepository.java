package com.noplan.persistence.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.persistence.entity.ConferenceEntity;
import com.noplan.persistence.entity.EventEntity;
import com.noplan.persistence.entity.TrackEntity;
import com.noplan.persistence.entity.UserEntity;
import com.noplan.persistence.entity.UserEventMappingEntity;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Service
public class UserEventMappingRepository extends AbstractRepository {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserEventMappingEntity> getMappingsForUser(UserEntity user) {
		Query q = getSession().createQuery("From UserEventMappingEntity mapping where mapping.user = :user");
		q.setParameter("user", user);

		return q.list();
	}

	@Transactional(readOnly = true)
	public Map<Long, ConferenceEntity> getConferenceEntitiesForUser(UserEntity user) {
		Map<Long, ConferenceEntity> conferences = new HashMap<Long, ConferenceEntity>();
		for (UserEventMappingEntity mapping : getMappingsForUser(user)) {
			ConferenceEntity conf = mapping.getEvent().getTrack().getConference();

			conferences.put(conf.getId(), conf);
		}
		return conferences;
	}

	@Transactional(readOnly = true)
	public Map<Long, TrackEntity> getTrackEntitiesForUser(long conferenceId, UserEntity user) {
		Map<Long, TrackEntity> conferences = new HashMap<Long, TrackEntity>();
		for (UserEventMappingEntity mapping : getMappingsForUser(user)) {
			TrackEntity track = mapping.getEvent().getTrack();
			if (track.getConference().getId() == conferenceId) {
				conferences.put(track.getId(), track);
			}
		}
		return conferences;
	}

	@Transactional(readOnly = true)
	public List<EventEntity> getEventEntitiesForUser(long trackId, UserEntity user) {
		List<EventEntity> events = new ArrayList<EventEntity>();
		for (UserEventMappingEntity mapping : getMappingsForUser(user)) {
			if (mapping.getEvent().getTrack().getId() == trackId) {
				events.add(mapping.getEvent());
			}
		}
		return events;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserEventMappingEntity> getMappings(UserEntity user, EventEntity event) {
		Query q = getSession().createQuery("From UserEventMappingEntity mapping where mapping.user = :user and mapping.event = :event");
		q.setParameter("user", user);
		q.setParameter("event", event);

		return q.list();
	}

	@Transactional(readOnly = false)
	public void addMapping(UserEventMappingEntity mapping) {
		save(mapping);
	}

	@Transactional(readOnly = false)
	public void addMappings(List<UserEventMappingEntity> mappings) {
		for (UserEventMappingEntity mapping : mappings) {
			save(mapping);
		}
	}

	@Transactional(readOnly = false)
	public void removeMapping(UserEventMappingEntity mapping) {
		delete(mapping);
	}

	@Transactional(readOnly = false)
	public void removeMappings(UserEntity user, EventEntity event) {
		for (UserEventMappingEntity mapping : getMappings(user, event)) {
			delete(mapping);
		}
	}

	@Transactional(readOnly = false)
	public void removeMappings(List<UserEventMappingEntity> mappings) {
		for (UserEventMappingEntity mapping : mappings) {
			delete(mapping);
		}
	}

}
