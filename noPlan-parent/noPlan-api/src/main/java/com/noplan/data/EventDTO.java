package com.noplan.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a single event
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@XmlRootElement
public class EventDTO {

	private Long id;
	private long trackId;
	private String name;
	private String description;
	private String presenter;
	private String location;
	private Date startdate;
	private Date enddate;
	private String infolink;
	

	public EventDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getTrackId() {
		return trackId;
	}

	public void setTrackId(long trackId) {
		this.trackId = trackId;
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

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getInfolink() {
		return infolink;
	}

	public void setInfolink(String infolink) {
		this.infolink = infolink;
	}

}
