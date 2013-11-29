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

/**
 * This is the mapping between a user and the conferences he wants to see
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLUSEREVENTMAPPING")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQTBLUSEREVENTMAPPING")
public class UserEventMappingEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@JoinColumn(name = "FK_USER", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;

	@JoinColumn(name = "FK_EVENT", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private EventEntity event;

	public UserEventMappingEntity(UserEntity user, EventEntity event) {
		this.user = user;
		this.event = event;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

}
