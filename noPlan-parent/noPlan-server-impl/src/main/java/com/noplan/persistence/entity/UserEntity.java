package com.noplan.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.noplan.data.UserDTO;

/**
 * Represents a user within the application
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLUSER", uniqueConstraints = {@UniqueConstraint(columnNames={"USERNAME"})})
@SequenceGenerator(name = "SEQ", sequenceName = "SEQTBLUSER")
public class UserEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	// TODO isAdmin

	// TODO deleted

	// TODO encrypt password

	// TODO conncect with spring security

	public UserEntity() {

	}

	public UserEntity(UserDTO dTO) {
		fromDTO(dTO, false);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDTO toDTO() {
		UserDTO dTo = new UserDTO();
		dTo.setId(getId());
		dTo.setUsername(getUsername());
		dTo.setPassword(getPassword());
		return dTo;
	}

	public void fromDTO(UserDTO userDTO, boolean isUpdate) {
		if (!isUpdate) {
			setId(userDTO.getId());
		}
		setUsername(userDTO.getUsername());
		setPassword(userDTO.getPassword());
	}
}
