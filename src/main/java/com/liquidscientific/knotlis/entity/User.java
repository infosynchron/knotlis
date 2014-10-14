package com.liquidscientific.knotlis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "user")
@Component
@Scope("session")
public class User implements Serializable {

	public User(String userid, String password, String clientid,
			String firstname, String lastname, String parentclientid,
			String role) {
		super();
		this.userid = userid;
		this.password = password;
		this.clientid = clientid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.parentclientid = parentclientid;
		this.role = role;
	}

	public User() {
		super();

	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "userid")
	public String userid;

	@Column(name = "password")
	public String password;

	@Column(name = "clientid")
	public String clientid;

	@Column(name = "firstname")
	public String firstname;

	@Column(name = "lastname")
	public String lastname;

	@Column(name = "parentclientid")
	public String parentclientid;

	@Column(name = "role")
	public String role;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
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

	public String getParentclientid() {
		return parentclientid;
	}

	public void setParentclientid(String parentclientid) {
		this.parentclientid = parentclientid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
