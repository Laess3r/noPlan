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
@Table(name = "TBLUSER", uniqueConstraints = { @UniqueConstraint(columnNames = { "USERNAME" }) })
@SequenceGenerator(name = "SEQ", sequenceName = "SEQTBLUSER")
public class UserEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "USERNAME", nullable = false, length = 40)
	private String username;

	// TODO encrypt password
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	/**
	 * This field can only be set via database
	 */
	@Column(name = "ENABLED")
	private Boolean enabled;

	@Column(name = "FIRSTNAME", nullable = false, length = 50)
	private String firstname;

	@Column(name = "LASTNAME", nullable = false, length = 50)
	private String lastname;

	@Column(name = "EMAIL", nullable = false, length = 100)
	private String email;

	// TODO permissions

	public UserEntity() {

	}

	public UserEntity(UserDTO dTO) {
		fromDTO(dTO, false);
	}

	public UserDTO toDTO() {
		UserDTO dTo = new UserDTO();
		dTo.setId(getId());
		dTo.setUsername(getUsername());
		dTo.setPassword(getPassword());
		dTo.setFirstname(getFirstname());
		dTo.setLastname(getLastname());
		dTo.setEmail(getEmail());
		dTo.setEnabled(getEnabled());

		return dTo;
	}

	public void fromDTO(UserDTO userDTO, boolean isUpdate) {
		if (!isUpdate) {
			setId(userDTO.getId());
		}
		setUsername(userDTO.getUsername());
		setPassword(userDTO.getPassword());
		setFirstname(userDTO.getFirstname());
		setLastname(userDTO.getLastname());
		setEmail(userDTO.getEmail());
		setEnabled(userDTO.getEnabled());
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
