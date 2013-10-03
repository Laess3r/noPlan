package com.noplan.persistence.repositories;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.persistence.entity.AbstractEntity;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Transactional(readOnly = true)
public abstract class AbstractRepository {

	@Autowired
	SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	protected Serializable save(AbstractEntity entity) {
		return getSession().save(entity);
	}

	@Transactional
	protected void update(AbstractEntity entity) {
		getSession().update(entity);
	}

	@Transactional
	protected void delete(AbstractEntity entity) {
		getSession().delete(entity);
	}
}
