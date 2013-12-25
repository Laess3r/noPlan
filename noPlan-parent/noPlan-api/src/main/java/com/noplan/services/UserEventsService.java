package com.noplan.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.noplan.data.ConferenceDTO;
import com.noplan.data.EventDTO;
import com.noplan.data.TrackDTO;
import com.noplan.data.UserDTO;

/**
 * @author DaHu4wA (Stefan Huber)
 */
// @Path("/userevents")
public interface UserEventsService {

	@GET
	@Path("/getconferences")
	@Produces(MediaType.APPLICATION_JSON)
	List<ConferenceDTO> getConferencesForUser();

	@GET
	@Path("/gettracks/{conferenceId}")
	@Produces(MediaType.APPLICATION_JSON)
	List<TrackDTO> getUserTracksForConference(@PathParam("conferenceId") long conferenceId);

	@GET
	@Path("/getevents/{trackId}")
	@Produces(MediaType.APPLICATION_JSON)
	List<EventDTO> getUserEventsForTrack(@PathParam("trackId") long trackId);

	@POST
	@Path("/addevents")
	@Consumes(MediaType.APPLICATION_JSON)
	void addEvents(List<Long> eventIds);

	@POST
	@Path("/removeevents")
	@Consumes(MediaType.APPLICATION_JSON)
	void removeEvents(List<Long> eventIds);

	UserDTO getLoggedInUserDTO();

}
