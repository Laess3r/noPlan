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

import com.noplan.data.TrackDTO;

/**
 * A track represents a part of a conference (eg. one day)
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

	@Column(name = "NAME", nullable = false, length = 200)
	private String name;

	@Column(name = "RESOURCE", length = 500)
	private String resource;

	@Column(name = "LOCATION", length = 300)
	private String location;

	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	public TrackEntity() {

	}

	public TrackEntity(TrackDTO dTO, ConferenceEntity conference) {
		fromDTO(dTO, conference, false);
	}

	public TrackDTO toDTO() {
		TrackDTO dTO = new TrackDTO();
		dTO.setId(getId());
		dTO.setConferenceId(getConference().getId());
		dTO.setDescription(getDescription());
		dTO.setName(getName());
		dTO.setResource(getResource());
		dTO.setLocation(getLocation());

		return dTO;
	}

	public void fromDTO(TrackDTO dTO, ConferenceEntity conference, boolean isUpdate) {
		if (!isUpdate) {
			setId(dTO.getId());
		}
		if (conference != null) {
			setConference(conference);
		}

		setDescription(dTO.getDescription());
		setName(dTO.getName());
		setResource(dTO.getResource());
		setLocation(dTO.getLocation());
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

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
