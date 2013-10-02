package com.noplan.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DaHu4wA (Stefan Huber)
 */
public class AbstractPersistenceService {

	@Autowired
	SessionFactory sessionFactory;
	
	// TODO maybe CRUD methods that require transaction?

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
