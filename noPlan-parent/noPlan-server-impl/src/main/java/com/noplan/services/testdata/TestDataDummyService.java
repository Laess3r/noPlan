package com.noplan.services.testdata;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_HTML)
	@Transactional
	public String createDummyData() {
		if (checkIfDataExists()) {
			return "Data already exists!";
		}

		ConferenceEntity conference = new ConferenceEntity();
		conference.setName(CONFERENCE_NAME);
		conference.setDescription("This is a cool conference");
		save(conference);

		TrackEntity track = new TrackEntity();
		track.setConference(conference);
		track.setName("Track nr 1");
		save(track);

		EventEntity eventOne = new EventEntity();
		eventOne.setName("First event");
		eventOne.setTrack(track);
		save(eventOne);

		EventEntity eventTwo = new EventEntity();
		eventTwo.setName("Second event");
		eventTwo.setTrack(track);
		save(eventTwo);

		return "Successfully created dummy data!";
	}

	private boolean checkIfDataExists() {
		Query q = getSession().createQuery(
				"From ConferenceEntity conf where conf.name = :name");
		q.setParameter("name", CONFERENCE_NAME);

		if (q.uniqueResult() != null) {
			return true;
		}
		return false;
	}

}
