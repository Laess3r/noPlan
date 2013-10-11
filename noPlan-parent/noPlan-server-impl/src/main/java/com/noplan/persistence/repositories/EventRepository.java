package com.noplan.persistence.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.data.EventDTO;
import com.noplan.persistence.entity.EventEntity;
import com.noplan.persistence.entity.TrackEntity;

/**
 * This repository is for the maintainence of events
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Repository
public class EventRepository extends AbstractRepository {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<EventEntity> getAllEventsForTrack(Long trackId) {
		Query q = getSession().createQuery("From EventEntity user where user.FK_TRACK = :FK_TRACK");
		q.setParameter("FK_TRACK", trackId);

		return q.list();
	}

	@Transactional(readOnly = true)
	public List<EventDTO> getAllEventDTOsForTrack(Long trackId) {
		List<EventDTO> result = new ArrayList<EventDTO>();

		for (EventEntity event : getAllEventsForTrack(trackId)) {
			result.add(event.toDTO());
		}

		return result;
	}

	@Transactional(readOnly = true)
	public EventEntity getEventById(Long id) {
		Query q = getSession().createQuery("From EventEntity evt where evt.id = :id");
		q.setParameter("id", id);

		return (EventEntity) q.uniqueResult();
	}

	@Transactional(readOnly = true)
	public EventDTO getEventDTOById(Long id) {
		EventEntity event = getEventById(id);

		if (event == null) {
			return null;
		}

		return event.toDTO();
	}

	@Transactional(readOnly = false)
	public EventDTO createEvent(EventDTO event) {

		TrackEntity track = (TrackEntity) getSession().get(TrackEntity.class, event.getTrackId());
		EventEntity entity = new EventEntity(event, track);

		save(entity);

		return entity.toDTO();
	}

	@Transactional(readOnly = false)
	public EventDTO updateEvent(EventDTO event) {

		EventEntity existing = getEventById(event.getId());

		existing.fromDTO(event, null, true);

		return existing.toDTO();
	}

	@Transactional(readOnly = false)
	public void deleteEventById(Long eventId) {
		delete(getEventById(eventId));
	}

}
