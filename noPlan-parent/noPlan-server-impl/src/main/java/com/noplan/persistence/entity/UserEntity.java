package com.noplan.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.noplan.data.UserDTO;

/**
 * Represents a user within the application
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Entity
@Table(name = "TBLUSER")
public class UserEntity extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	// TODO isAdmin

	// TODO deleted

	// TODO encrypt password

	// TODO conncect with spring security

	public UserEntity() {
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

	public UserDTO toUserDTO() {
		UserDTO dTo = new UserDTO();
		dTo.setId(getId());
		dTo.setUsername(getUsername());
		dTo.setPassword(getPassword());
		return dTo;
	}

	public static UserEntity fromUserDTO(UserDTO userDTO) {
		UserEntity entity = new UserEntity();
		entity.setId(userDTO.getId());
		entity.setUsername(userDTO.getUsername());
		entity.setPassword(userDTO.getPassword());
		return entity;
	}
}
