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

import com.noplan.data.TrackDTO;

/**
 * This is the main service for tracks
 * 
 * @author DaHu4wA (Stefan Huber)
 */
public interface TrackService {

	@GET
	@Path("/allforconference/{confid}")
	@Produces(MediaType.APPLICATION_JSON)
	List<TrackDTO> getAllTracksForConference(@PathParam("confid") Long conferenceId);

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	TrackDTO getTrackById(@PathParam("id") Long trackId);

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	TrackDTO createTrack(TrackDTO track);

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	TrackDTO updateTrack(TrackDTO track);

	@DELETE
	@Path("/delete/{id}")
	void deleteTrackById(@PathParam("id") Long trackid);
}
