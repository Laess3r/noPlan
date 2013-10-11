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

import com.noplan.data.ConferenceDTO;

/**
 * This is the main service for the conferences
 * 
 * @author DaHu4wA (Stefan Huber)
 */
//@Path("/conference")
public interface ConferenceService {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	List<ConferenceDTO> getAllConferences();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	ConferenceDTO getConferenceById(@PathParam("id") Long conferenceId);

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ConferenceDTO createConference(ConferenceDTO conference);

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ConferenceDTO updateConference(ConferenceDTO conference);

	@DELETE
	@Path("/delete/{id}")
	void deleteConferenceById(@PathParam("id") Long conferenceId);
}
