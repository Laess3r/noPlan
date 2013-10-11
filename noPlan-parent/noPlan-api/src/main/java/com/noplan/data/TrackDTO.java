package com.noplan.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@XmlRootElement
public class TrackDTO {

	private Long id;
	private Long conferenceId;
	private String name;
	private String description;

	public TrackDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(Long conferenceId) {
		this.conferenceId = conferenceId;
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

}
