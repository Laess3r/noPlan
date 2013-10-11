package com.noplan.services;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noplan.data.EventDTO;
import com.noplan.persistence.repositories.EventRepository;

/**
 * This is the main service for events
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/event")
@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<EventDTO> getAllEventsForTrack(Long trackId) {
		return eventRepository.getAllEventDTOsForTrack(trackId);
	}

	@Override
	public EventDTO getEventById(Long eventId) {
		return eventRepository.getEventDTOById(eventId);
	}

	@Override
	public EventDTO createEvent(EventDTO event) {
		return eventRepository.createEvent(event);
	}

	@Override
	public EventDTO updateEvent(EventDTO event) {
		return eventRepository.updateEvent(event);
	}

	@Override
	public void deleteEventById(Long eventid) {
		eventRepository.deleteEventById(eventid);
	}

}
