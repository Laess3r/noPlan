package com.noplan.services;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noplan.data.ConferenceDTO;
import com.noplan.data.EventDTO;
import com.noplan.data.TrackDTO;
import com.noplan.persistence.repositories.AbstractRepository;

/**
 * Service to create some dummy data for testing!
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Service
@Path("/testdata")
public class TestDataDummyService extends AbstractRepository {

	private static final String CONFERENCE_NAME = "wJax 2014";

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private TrackService trackService;

	@Autowired
	private EventService eventService;

	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_HTML)
	public String createDummyDataNew() {

		ConferenceDTO conference = new ConferenceDTO();
		conference.setName(CONFERENCE_NAME);
		conference.setDescription("This is a cool conference");
		conference.setLocation("Saalfelden");
		conference.setStartDate(new Date());
		conference.setEndDate(new Date());

		conference = conferenceService.createConference(conference);

		TrackDTO track = new TrackDTO();
		track.setConferenceId(conference.getId());
		track.setName("Track nr 1");

		track = trackService.createTrack(track);

		EventDTO eventOne = new EventDTO();
		eventOne.setName("First event");
		eventOne.setTrackId(track.getId());

		eventOne = eventService.createEvent(eventOne);

		EventDTO eventTwo = new EventDTO();
		eventTwo.setName("Second event");
		eventTwo.setTrackId(track.getId());

		eventTwo = eventService.createEvent(eventTwo);

		return "<h1>Successfully created dummy data!</h1>";
	}

}
