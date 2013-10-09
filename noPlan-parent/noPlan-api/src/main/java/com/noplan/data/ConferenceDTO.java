package com.noplan.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@XmlRootElement
public class ConferenceDTO {

	private Long id;
	private String name;
	private String description;
	private List<TrackDTO> tracks = new ArrayList<TrackDTO>();

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

	public List<TrackDTO> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackDTO> tracks) {
		this.tracks = tracks;
	}

}
