package com.noplan.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@XmlRootElement
public class EventDTO {

	private Long id;
//	private TrackDTO track;
	private String name;
	private String description;

	public EventDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public TrackDTO getTrack() {
//		return track;
//	}
//
//	public void setTrack(TrackDTO track) {
//		this.track = track;
//	}

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

}
