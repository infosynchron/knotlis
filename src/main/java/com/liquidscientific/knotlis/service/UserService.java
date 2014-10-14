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

import com.liquidscientific.knotlis.entity.User;

@Repository
@SuppressWarnings({ "unchecked" })
public class UserService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public User getUser(String userid, String password) {
		String hqlQuery = "From User user  where user.userid=:userid and user.password=:password";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlQuery);
		query.setParameter("userid", userid);
		query.setParameter("password", password);
		List<User> user = query.list();
		session.flush();
		session.clear();
		if (user.size() <= 0) {
			User blankuser = new User();
			blankuser.setPassword("");
			blankuser.setRole("");
			blankuser.setUserid("");
			return blankuser;
		} else {
			return user.get(0);
		}
	}
}
