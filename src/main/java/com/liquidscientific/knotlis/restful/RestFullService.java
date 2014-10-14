package com.liquidscientific.knotlis.restful;

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
import com.liquidscientific.knotlis.service.PatientService;

@Repository
@SuppressWarnings({ "unchecked" })
public class RestFullService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(PatientService.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public String addPatientTest(Patient patient, Narcotics narcotics) {
		String hqlQuery = "From Patient patient  where patient.mrn =:mrn";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlQuery);
		query.setParameter("mrn", patient.getMrn());
		List<Patient> testlist = query.list();
		if (testlist.size() <= 0) {
			session.save(patient);
			session.save(narcotics);
		} else {
			session.save(narcotics);
		}

		session.flush();
		session.clear();
		return "1";

	}

}
