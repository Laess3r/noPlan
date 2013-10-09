package com.noplan.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.noplan.data.ConferenceDTO;
import com.noplan.data.EventDTO;
import com.noplan.data.TrackDTO;

/**
 * A track represents a part of a conference
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLTRACK")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQTBLTRACK", allocationSize = 10)
public class TrackEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@JoinColumn(name = "FK_CONFERENCE", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private ConferenceEntity conference;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Cascade(CascadeType.SAVE_UPDATE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "track", orphanRemoval = true)
	private List<EventEntity> events = new ArrayList<EventEntity>();

	public TrackEntity() {

	}

	public TrackEntity(TrackDTO dTO, ConferenceEntity conference) {
		fromDTO(dTO, conference, false);
	}

	public TrackDTO toDTO() {
		return toDTO(null);
	}

	public TrackDTO toDTO(ConferenceDTO conference) {
		TrackDTO dTO = new TrackDTO();
		dTO.setId(getId());
//		if (conference != null) {
//			dTO.setConference(conference);
//		} else {
//			dTO.setConference(getConference().toDTO());
//		}
		dTO.setDescription(getDescription());
		dTO.setName(getName());

		for (EventEntity event : getEvents()) {
			dTO.getEvents().add(event.toDTO(dTO));
		}

		return dTO;
	}

	public void fromDTO(TrackDTO dTO, ConferenceEntity conference, boolean isUpdate) {
		if (!isUpdate) {
			setId(dTO.getId());
		}
		setConference(conference);
		setDescription(dTO.getDescription());
		setName(dTO.getName());

		for (EventDTO event : dTO.getEvents()) {

			if (!isUpdate) {
				insertNewEvent(event);
				continue;
			}

			// existing events are already loaded if update is true
			if (event.getId() == null) {
				insertNewEvent(event);
			} else {
				updateEvent(event);
			}
		}

		setEvents(events);
	}

	private void insertNewEvent(EventDTO event) {
		getEvents().add(new EventEntity(event, this));
	}

	private void updateEvent(EventDTO event) {
		for (EventEntity existingEvent : getEvents()) {
			if (existingEvent.getId().equals(event.getId())) {
				existingEvent.fromDTO(event, this, true);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public ConferenceEntity getConference() {
		return conference;
	}

	public void setConference(ConferenceEntity conference) {
		this.conference = conference;
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

	public List<EventEntity> getEvents() {
		return events;
	}

	public void setEvents(List<EventEntity> events) {
		this.events = events;
	}

}
