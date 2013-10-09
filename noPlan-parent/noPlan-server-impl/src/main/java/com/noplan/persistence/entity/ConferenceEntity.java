package com.noplan.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.noplan.data.ConferenceDTO;
import com.noplan.data.TrackDTO;

/**
 * This is the main part of a conference
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLCONFERENCE")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQTBLCONFERENCE", allocationSize = 1)
public class ConferenceEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Cascade(CascadeType.SAVE_UPDATE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conference", orphanRemoval = true)
	private List<TrackEntity> tracks = new ArrayList<TrackEntity>();

	public ConferenceEntity() {

	}

	public ConferenceEntity(ConferenceDTO dTO) {
		fromDTO(dTO, false);
	}

	public List<TrackEntity> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackEntity> tracks) {
		this.tracks = tracks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ConferenceDTO toDTO() {
		ConferenceDTO dTO = new ConferenceDTO();
		dTO.setId(getId());
		dTO.setName(getName());
		dTO.setDescription(getDescription());

		for (TrackEntity track : getTracks()) {
			dTO.getTracks().add(track.toDTO(dTO));
		}

		return dTO;
	}

	public void fromDTO(ConferenceDTO dTO, boolean isUpdate) {
		if (!isUpdate) {
			setId(dTO.getId());
		}
		setName(dTO.getName());
		setDescription(dTO.getDescription());

		for (TrackDTO track : dTO.getTracks()) {

			if (!isUpdate) {
				insertNewTrack(track);
				continue;
			}

			// existing tracks are already loaded if update is true
			if (track.getId() == null) {
				insertNewTrack(track);
			} else {
				updateTrack(track);
			}
		}
	}

	private void insertNewTrack(TrackDTO track) {
		getTracks().add(new TrackEntity(track, this));
	}

	private void updateTrack(TrackDTO track) {
		for (TrackEntity existingTrack : getTracks()) {
			if (existingTrack.getId().equals(track.getId())) {
				existingTrack.fromDTO(track, this, true);
			}
		}
	}
}
