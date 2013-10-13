package com.noplan.persistence.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.data.EventDTO;
import com.noplan.persistence.entity.EventEntity;
import com.noplan.persistence.entity.TrackEntity;

/**
 * This repository is for the maintenance of events
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Repository
public class EventRepository extends AbstractRepository {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<EventEntity> getAllEventsForTrack(Long trackId) {
		Criteria criteria = getSession().createCriteria(EventEntity.class);
		criteria.add(Restrictions.eq("track.id", trackId));

		return criteria.list();
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
		return (EventEntity) getSession().get(EventEntity.class, id);
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
