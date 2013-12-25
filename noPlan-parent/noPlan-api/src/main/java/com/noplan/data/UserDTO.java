package com.noplan.data;

import java.util.Map;

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
	private Boolean enabled;
	private Boolean isadmin;

	private Map<String, Boolean> roles;
	private String token;

	public UserDTO() {

	}

	public UserDTO(String userName, Map<String, Boolean> roles, String token) {
		this.username = userName;
		this.roles = roles;
		this.token = token;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public Map<String, Boolean> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, Boolean> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}

}
