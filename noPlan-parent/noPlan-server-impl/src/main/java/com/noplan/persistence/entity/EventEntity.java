package com.noplan.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.noplan.data.EventDTO;
import com.noplan.data.TrackDTO;

/**
 * Represents a single event
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLEVENT")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQTBLEVENT", allocationSize = 10)
public class EventEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@JoinColumn(name = "FK_TRACK", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private TrackEntity track;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	// TODO from date

	// TODO to-date

	// TODO location

	// TODO other info

	public EventEntity() {

	}

	public EventEntity(EventDTO dTO, TrackEntity trackEntity) {
		fromDTO(dTO, trackEntity, false);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrackEntity getTrack() {
		return track;
	}

	public void setTrack(TrackEntity track) {
		this.track = track;
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

	public EventDTO toDTO() {
		return toDTO(null);
	}

	public EventDTO toDTO(TrackDTO track) {
		EventDTO dTO = new EventDTO();
		dTO.setId(getId());
		dTO.setName(getName());
		dTO.setTrackId(getTrack().getId());

		return dTO;
	}

	public void fromDTO(EventDTO dTO, TrackEntity trackEntity, boolean isUpdate) {
		if (!isUpdate) {
			setId(dTO.getId());
		}
		setName(dTO.getName());
		setDescription(dTO.getDescription());
		if (trackEntity != null) {
			setTrack(trackEntity);
		}
	}

}
