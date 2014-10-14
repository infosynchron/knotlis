package com.liquidscientific.knotlis.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liquidscientific.knotlis.entity.Narcotics;
import com.liquidscientific.knotlis.entity.Patient;

@Repository
@SuppressWarnings({ "unchecked" })
public class PatientService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(PatientService.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Patient getPatient(String mrn) {
		String hqlQuery = "From Patient patient  where patient.mrn=:mrn";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlQuery);
		query.setParameter("mrn", mrn);
		List<Patient> patient = query.list();
		session.flush();
		session.clear();
		if (patient.size() <= 0) {
			Patient blankpatient = new Patient();
			blankpatient.setPatientid("");
			blankpatient.setPatientfirstname("");
			blankpatient.setPatientlastname("");
			return blankpatient;
		} else {
			Patient encryptedpatient = new Patient();
			encryptedpatient.setMrn(patient.get(0).getMrn().trim());
			encryptedpatient.setPatientid(patient.get(0).getPatientid().trim());
			encryptedpatient.setPatientfirstname(patient.get(0)
					.getPatientfirstname().trim().substring(0, 1));
			encryptedpatient.setPatientlastname(patient.get(0)
					.getPatientlastname().trim().substring(0, 1));
			encryptedpatient.setDateofbirth(patient.get(0).getDateofbirth());
			encryptedpatient.setGender(patient.get(0).getGender());
			encryptedpatient.setSampletype(patient.get(0).getSampletype());
			encryptedpatient.setClientid(patient.get(0).getClientid());
			return encryptedpatient;
		}
	}

	@Transactional
	public void savePatientTest(Narcotics narcotics) {
		Session session = sessionFactory.getCurrentSession();
		session.save(narcotics);
		session.flush();
		session.clear();

	}

	@Transactional
	public List<Narcotics> getPatientTest(String fileindex) {
		String hqlQuery = "From Narcotics narcotics  where narcotics.fileindex =:fileindex";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlQuery);
		query.setParameter("fileindex", fileindex);
		List<Narcotics> testlist = query.list();
		session.flush();
		session.clear();
		return testlist;

	}

	@Transactional
	public List<String> getPatientReports(String mrn) {

		String hqlQuery = "SELECT narcotics.fileindex  From Narcotics narcotics  where narcotics.mrn=:mrn  ORDER BY narcotics.testdate DESC";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlQuery);
		query.setParameter("mrn", mrn);
		List<String> patientreports = query.list();
		patientreports.add(0, "-Select a Report to View-");
		session.flush();
		session.clear();
		return patientreports;

	}
}
