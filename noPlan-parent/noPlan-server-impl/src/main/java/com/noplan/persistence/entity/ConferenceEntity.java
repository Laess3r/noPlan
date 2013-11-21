package com.noplan.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.noplan.data.ConferenceDTO;

/**
 * This is the main part of a conference
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLCONFERENCE")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQTBLCONFERENCE", allocationSize = 1)
public class ConferenceEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 200)
	private String name;

	@Column(name = "LOCATION", nullable = false, length = 300)
	private String location;

	@Column(name = "STARTDATE", nullable = false)
	private Date startdate;

	@Column(name = "ENDDATE", nullable = false)
	private Date enddate;

	@Column(name = "INFOLINK", length = 500)
	private String infolink;

	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	public ConferenceEntity() {

	}

	public ConferenceEntity(ConferenceDTO dTO) {
		fromDTO(dTO, false);
	}

	public ConferenceDTO toDTO() {
		ConferenceDTO dTO = new ConferenceDTO();
		dTO.setId(getId());
		dTO.setName(getName());
		dTO.setDescription(getDescription());
		dTO.setLocation(getLocation());
		dTO.setStartDate(getStartdate());
		dTO.setEndDate(getEnddate());
		dTO.setInfolink(getInfolink());

		return dTO;
	}

	public void fromDTO(ConferenceDTO dTO, boolean isUpdate) {
		if (!isUpdate) {
			setId(dTO.getId());
		}
		setName(dTO.getName());
		setDescription(dTO.getDescription());
		setLocation(dTO.getLocation());
		setStartdate(dTO.getStartDate());
		setEnddate(dTO.getEndDate());
		setInfolink(dTO.getInfolink());
	}

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
