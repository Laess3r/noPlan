package com.noplan.persistence.entity;

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

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	public ConferenceEntity() {

	}

	public ConferenceEntity(ConferenceDTO dTO) {
		fromDTO(dTO, false);
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

	public ConferenceDTO toDTO() {
		ConferenceDTO dTO = new ConferenceDTO();
		dTO.setId(getId());
		dTO.setName(getName());
		dTO.setDescription(getDescription());

		return dTO;
	}

	public void fromDTO(ConferenceDTO dTO, boolean isUpdate) {
		if (!isUpdate) {
			setId(dTO.getId());
		}
		setName(dTO.getName());
		setDescription(dTO.getDescription());
	}
}
