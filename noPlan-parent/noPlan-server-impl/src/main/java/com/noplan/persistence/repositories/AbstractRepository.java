package com.noplan.persistence.repositories;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noplan.persistence.entity.AbstractEntity;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Repository
public abstract class AbstractRepository {

	@Autowired
	SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(AbstractEntity entity) {
		return getSession().save(entity);
	}

	public void update(AbstractEntity entity) {
		getSession().update(entity);
	}

	public void delete(AbstractEntity entity) {
		getSession().delete(entity);
		// Gener
	}
}
