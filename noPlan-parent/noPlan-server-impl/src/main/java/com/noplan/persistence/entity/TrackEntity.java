package com.noplan.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A track represents a part of a conference
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLTRACK")
public class TrackEntity extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private ConferenceEntity conference;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
	private List<EventEntity> events;

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
