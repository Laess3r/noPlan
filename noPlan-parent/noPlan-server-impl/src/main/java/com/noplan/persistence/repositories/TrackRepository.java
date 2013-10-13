package com.noplan.persistence.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.data.TrackDTO;
import com.noplan.persistence.entity.ConferenceEntity;
import com.noplan.persistence.entity.TrackEntity;

/**
 * This repository is for the maintenance of tracks
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Repository
public class TrackRepository extends AbstractRepository {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TrackEntity> getAllTracksForConference(Long conferenceId) {

		Criteria criteria = getSession().createCriteria(TrackEntity.class);
		criteria.add(Restrictions.eq("conference.id", conferenceId));

		return criteria.list();
	}

	@Transactional(readOnly = true)
	public List<TrackDTO> getAllTrackDTOsForConference(Long conferenceId) {
		List<TrackDTO> result = new ArrayList<TrackDTO>();

		for (TrackEntity track : getAllTracksForConference(conferenceId)) {
			result.add(track.toDTO());
		}

		return result;
	}

	@Transactional(readOnly = true)
	public TrackEntity getTrackById(Long id) {
		return (TrackEntity) getSession().get(TrackEntity.class, id);
	}

	@Transactional(readOnly = true)
	public TrackDTO getTrackDTOById(Long id) {
		TrackEntity track = getTrackById(id);

		if (track == null) {
			return null;
		}

		return track.toDTO();
	}

	@Transactional(readOnly = false)
	public TrackDTO createTrack(TrackDTO track) {

		ConferenceEntity conference = (ConferenceEntity) getSession().get(ConferenceEntity.class, track.getConferenceId());
		TrackEntity entity = new TrackEntity(track, conference);

		save(entity);

		return entity.toDTO();
	}

	@Transactional(readOnly = false)
	public TrackDTO updateTrack(TrackDTO track) {

		TrackEntity existing = getTrackById(track.getId());

		existing.fromDTO(track, null, true);

		return existing.toDTO();
	}

	@Transactional(readOnly = false)
	public void deleteTrackById(Long trackId) {

		// TODO check if conferences exist

		delete(getTrackById(trackId));
	}

}
