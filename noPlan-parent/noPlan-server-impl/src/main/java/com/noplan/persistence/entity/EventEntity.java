package com.noplan.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a single event
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLEVENT")
public class EventEntity extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private TrackEntity track;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

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

	// TODO from date

	// TODO to-date

	// TODO location

	// TODO other info

}
