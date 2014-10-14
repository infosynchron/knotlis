package com.liquidscientific.knotlis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "patient")
@Component
@Scope("session")
public class Patient implements Serializable {

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String mrn, String patientid, String patientfirstname,
			String patientlastname, Date dateofbirth, String sampletype,
			String gender, String clientid) {
		super();
		this.mrn = mrn;
		this.patientid = patientid;
		this.patientfirstname = patientfirstname;
		this.patientlastname = patientlastname;
		this.dateofbirth = dateofbirth;
		this.sampletype = sampletype;
		this.gender = gender;
		this.clientid = clientid;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mrn")
	public String mrn;

	@Column(name = "patientid")
	public String patientid;

	@Column(name = "patientfirstname")
	public String patientfirstname;

	@Column(name = "patientlastname")
	public String patientlastname;

	@Column(name = "dateofbirth")
	public Date dateofbirth;

	@Column(name = "sampletype")
	public String sampletype;

	@Column(name = "gender")
	public String gender;

	@Column(name = "clientid")
	public String clientid;

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getPatientfirstname() {
		return patientfirstname;
	}

	public void setPatientfirstname(String patientfirstname) {
		this.patientfirstname = patientfirstname;
	}

	public String getPatientlastname() {
		return patientlastname;
	}

	public void setPatientlastname(String patientlastname) {
		this.patientlastname = patientlastname;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getSampletype() {
		return sampletype;
	}

	public void setSampletype(String sampletype) {
		this.sampletype = sampletype;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

}
