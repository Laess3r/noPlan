package com.noplan.persistence.entity;

import java.util.Date;

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

	@Column(name = "NAME", nullable = false, length = 300)
	private String name;

	@Column(name = "DESCRIPTION", length = 3000)
	private String description;

	@Column(name = "PRESENTER", length = 300)
	private String presenter;

	@Column(name = "LOCATION", length = 300)
	private String location;

	@Column(name = "STARTDATE")
	private Date startdate;

	@Column(name = "ENDDATE")
	private Date enddate;

	@Column(name = "INFOLINK", length = 300)
	private String infolink;

	public EventEntity() {

	}

	public EventEntity(EventDTO dTO, TrackEntity trackEntity) {
		fromDTO(dTO, trackEntity, false);
	}

	public EventDTO toDTO() {
		return toDTO(null);
	}

	public EventDTO toDTO(TrackDTO track) {
		EventDTO dTO = new EventDTO();
		dTO.setId(getId());
		dTO.setName(getName());
		dTO.setTrackId(getTrack().getId());
		dTO.setPresenter(getPresenter());
		dTO.setLocation(getLocation());
		dTO.setStartdate(getStartdate());
		dTO.setEnddate(getEnddate());
		dTO.setInfolink(getInfolink());

		return dTO;
	}

	public void fromDTO(EventDTO dTO, TrackEntity trackEntity, boolean isUpdate) {
		if (!isUpdate) {
			setId(dTO.getId());
		}
		if (trackEntity != null) {
			setTrack(trackEntity);
		}
		setName(dTO.getName());
		setDescription(dTO.getDescription());
		setPresenter(dTO.getPresenter());
		setLocation(dTO.getLocation());
		setStartdate(dTO.getStartdate());
		setEnddate(dTO.getEnddate());
		setInfolink(dTO.getInfolink());
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
