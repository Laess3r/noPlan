package com.noplan.persistence.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.data.ConferenceDTO;
import com.noplan.persistence.entity.ConferenceEntity;

/**
 * This repository is for the backend side to maintain conferences
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Repository
public class ConferenceRepository extends AbstractRepository {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConferenceEntity> getAllConferences() {
		return getSession().createQuery("From ConferenceEntity").list();
	}

	@Transactional(readOnly = true)
	public List<ConferenceDTO> getAllConferenceDTOs() {
		List<ConferenceDTO> result = new ArrayList<ConferenceDTO>();

		for (ConferenceEntity conference : getAllConferences()) {
			result.add(conference.toDTO());
		}

		return result;
	}

	@Transactional(readOnly = true)
	public ConferenceEntity getConferenceById(Long id) {
		Query q = getSession().createQuery("From ConferenceEntity conf where conf.id = :id");
		q.setParameter("id", id);

		return (ConferenceEntity) q.uniqueResult();
	}

	@Transactional(readOnly = true)
	public ConferenceDTO getConferenceDTOById(Long id) {
		ConferenceEntity conference = getConferenceById(id);

		if (conference == null) {
			return null;
		}

		return conference.toDTO();
	}

	@Transactional(readOnly = false)
	public ConferenceDTO createConference(ConferenceDTO conference) {
		ConferenceEntity entity = new ConferenceEntity(conference);
		saveConference(entity);

		return entity.toDTO();
	}

	@Transactional(readOnly = false)
	public void saveConference(ConferenceEntity conference) {
		save(conference);
	}

	@Transactional(readOnly = false)
	public void updateConference(ConferenceEntity conference, ConferenceDTO dTO) {
		conference.fromDTO(dTO, true);
		update(conference);
	}

	@Transactional(readOnly = false)
	public ConferenceDTO updateConference(ConferenceDTO conference) {

		ConferenceEntity entity = getConferenceById(conference.getId());

		updateConference(entity, conference);

		return entity.toDTO();
	}

	@Transactional(readOnly = false)
	public void deleteConference(Long id) {
		ConferenceEntity entity = getConferenceById(id);
		
		// TODO check if tracks exist!
		
		delete(entity);
	}

}
