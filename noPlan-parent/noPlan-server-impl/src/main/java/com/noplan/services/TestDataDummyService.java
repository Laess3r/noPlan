package com.noplan.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.data.ConferenceDTO;
import com.noplan.data.EventDTO;
import com.noplan.data.TrackDTO;
import com.noplan.persistence.entity.ConferenceEntity;
import com.noplan.persistence.entity.EventEntity;
import com.noplan.persistence.entity.TrackEntity;
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

	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_HTML)
	public String createDummyDataNew() {
		// if (checkIfDataExists()) {
		// return "Data already exists!";
		// }

		ConferenceDTO conference = new ConferenceDTO();
		conference.setName(CONFERENCE_NAME);
		conference.setDescription("This is a cool conference");

		TrackDTO track = new TrackDTO();
		// track.setConference(conference);
		track.setName("Track nr 1");

		conference.getTracks().add(track);

		EventDTO eventOne = new EventDTO();
		eventOne.setName("First event");
		// eventOne.setTrack(track);

		track.getEvents().add(eventOne);

		EventDTO eventTwo = new EventDTO();
		eventTwo.setName("Second event");
		// eventTwo.setTrack(track);

		track.getEvents().add(eventTwo);

		conferenceService.createConference(conference);

		// save(conference);

		return "<h1>Successfully created dummy data!</h1>";
	}

	@GET
	@Path("/allold")
	@Produces(MediaType.TEXT_HTML)
	@Transactional
	public String createDummyData() {
		if (checkIfDataExists()) {
			return "Data already exists!";
		}

		ConferenceEntity conference = new ConferenceEntity();
		conference.setName(CONFERENCE_NAME);
		conference.setDescription("This is a cool conference");

		TrackEntity track = new TrackEntity();
		track.setConference(conference);
		track.setName("Track nr 1");

		conference.getTracks().add(track);

		EventEntity eventOne = new EventEntity();
		eventOne.setName("First event");
		eventOne.setTrack(track);

		track.getEvents().add(eventOne);

		EventEntity eventTwo = new EventEntity();
		eventTwo.setName("Second event");
		eventTwo.setTrack(track);

		track.getEvents().add(eventTwo);

		save(conference);

		return "<h1>Successfully created dummy data!</h1>";
	}

	@GET
	@Path("/del")
	@Produces(MediaType.TEXT_HTML)
	@Transactional
	public String deleteEvents() {

		Query q = getSession().createQuery("From ConferenceEntity conf where conf.name = :name");
		q.setParameter("name", CONFERENCE_NAME);

		ConferenceEntity uniqueResult = (ConferenceEntity) q.uniqueResult();

		uniqueResult.getTracks().get(0).getEvents().remove(0);

		return "event deleted";
	}

	@Transactional
	private boolean checkIfDataExists() {
		Query q = getSession().createQuery("From ConferenceEntity conf where conf.name = :name");
		q.setParameter("name", CONFERENCE_NAME);

		if (q.uniqueResult() != null) {
			return true;
		}
		return false;
	}

}
