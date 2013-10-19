package com.noplan.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@XmlRootElement
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private Boolean isadministrator;

	public UserDTO() {

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

	public Boolean getIsadministrator() {
		return isadministrator;
	}

	/**
	 * NOT IMPLEMENTED FOR SECURITY REASONS!
	 */
	public void setIsadministrator(Boolean isadministrator) {
		// this.isadministrator = isadministrator;
	}

}
