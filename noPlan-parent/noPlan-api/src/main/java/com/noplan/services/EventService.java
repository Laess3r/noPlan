package com.noplan.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.noplan.data.EventDTO;

/**
 * This is the main service for events
 * 
 * @author DaHu4wA (Stefan Huber)
 */
//@Path("/event")
public interface EventService {

	@GET
	@Path("/allfortrack/{trackid}")
	@Produces(MediaType.APPLICATION_JSON)
	List<EventDTO> getAllEventsForTrack(@PathParam("trackid") Long trackId);

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	EventDTO getEventById(@PathParam("id") Long eventId);

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	EventDTO createEvent(EventDTO event);

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	EventDTO updateEvent(EventDTO event);

	@DELETE
	@Path("/delete/{id}")
	void deleteEventById(@PathParam("id") Long eventid);
}
