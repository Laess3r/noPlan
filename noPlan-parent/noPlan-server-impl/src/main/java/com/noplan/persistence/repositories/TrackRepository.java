package com.noplan.persistence.repositories;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.persistence.entity.TrackEntity;

/**
 * This repository is for the maintainence of tracks
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Repository
public class TrackRepository extends AbstractRepository {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TrackEntity> getAllTracksForConference(Long conferenceId) {
		Query q = getSession().createQuery("From TrackEntity user where user.FK_CONFERENCE = :FK_CONFERENCE");
		q.setParameter("FK_CONFERENCE", conferenceId);

		return q.list();
	}

	@Transactional(readOnly = true)
	public TrackEntity getTrackById(Long id) {
		Query q = getSession().createQuery("From TrackEntity user where user.id = :id");
		q.setParameter("id", id);

		return (TrackEntity) q.uniqueResult();
	}

	@Transactional(readOnly = false)
	public void saveTrack(TrackEntity track) {
		save(track);
	}

	@Transactional(readOnly = false)
	public void updateTrack(TrackEntity track) {
		update(track);
	}

	@Transactional(readOnly = false)
	public void deleteTrack(TrackEntity track) {
		delete(track);
	}

}
