package com.liquidscientific.knotlis.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "narcotics")
@Component
@Scope("session")
public class Narcotics implements Serializable {

	public Narcotics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Narcotics(String mrn, String clientid, Integer testid, String bzo,
			String amp, String thc, String opi, String coc, String met,
			String bup, String oxy, String filepath, String fileindex,
			Timestamp testdate) {
		super();
		this.mrn = mrn;
		this.clientid = clientid;
		this.testid = testid;
		this.bzo = bzo;
		this.amp = amp;
		this.thc = thc;
		this.opi = opi;
		this.coc = coc;
		this.met = met;
		this.bup = bup;
		this.oxy = oxy;
		this.filepath = filepath;
		this.fileindex = fileindex;
		this.testdate = testdate;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mrn")
	public String mrn;

	@Id
	@Column(name = "clientid")
	public String clientid;

	@Id
	@SequenceGenerator(name = "testid")
	@Column(name = "testid")
	public Integer testid;

	@Column(name = "bzo")
	public String bzo;

	@Column(name = "amp")
	public String amp;

	@Column(name = "thc")
	public String thc;

	@Column(name = "opi")
	public String opi;

	@Column(name = "coc")
	public String coc;

	@Column(name = "met")
	public String met;

	@Column(name = "bup")
	public String bup;

	@Column(name = "oxy")
	public String oxy;

	@Column(name = "filepath")
	public String filepath;

	@Column(name = "fileindex")
	public String fileindex;

	@Column(name = "testdate")
	@Type(type = "timestamp")
	public Timestamp testdate;

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public Integer getTestid() {
		return testid;
	}

	public void setTestid(Integer testid) {
		this.testid = testid;
	}

	public String getBzo() {
		return bzo;
	}

	public void setBzo(String bzo) {
		this.bzo = bzo;
	}

	public String getAmp() {
		return amp;
	}

	public void setAmp(String amp) {
		this.amp = amp;
	}

	public String getThc() {
		return thc;
	}

	public void setThc(String thc) {
		this.thc = thc;
	}

	public String getOpi() {
		return opi;
	}

	public void setOpi(String opi) {
		this.opi = opi;
	}

	public String getCoc() {
		return coc;
	}

	public void setCoc(String coc) {
		this.coc = coc;
	}

	public String getMet() {
		return met;
	}

	public void setMet(String met) {
		this.met = met;
	}

	public String getBup() {
		return bup;
	}

	public void setBup(String bup) {
		this.bup = bup;
	}

	public String getOxy() {
		return oxy;
	}

	public void setOxy(String oxy) {
		this.oxy = oxy;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFileindex() {
		return fileindex;
	}

	public void setFileindex(String fileindex) {
		this.fileindex = fileindex;
	}

	public Timestamp getTestdate() {
		return testdate;
	}

	public void setTestdate(Timestamp testdate) {
		this.testdate = testdate;
	}

}
