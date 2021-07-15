package com.sapient.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapient.test.validations.ValidLuhn10;

@Entity
@Table(name = "user")
public class Users {
	public enum APIRole {
		USER,
		ADMIN
	}
	
	@Id
	@Column(name = "user_name")
	@NotBlank
	private String userName;
	
	@Column(name = "password")
	@NotBlank
	private String password;
	
	@Column(name = "access_token")
	private String accessToken;
	
	@Column(name = "user_role")
	private String userRole;

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		// TODO Auto-generated method stub
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
