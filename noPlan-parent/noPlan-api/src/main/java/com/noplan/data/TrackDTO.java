package com.noplan.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@XmlRootElement
public class TrackDTO {

	private Long id;
//	private ConferenceDTO conference;
	private String name;
	private String description;
	private List<EventDTO> events = new ArrayList<EventDTO>();

	public TrackDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public ConferenceDTO getConference() {
//		return conference;
//	}
//
//	public void setConference(ConferenceDTO conference) {
//		this.conference = conference;
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

	public List<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}
	
	

}
