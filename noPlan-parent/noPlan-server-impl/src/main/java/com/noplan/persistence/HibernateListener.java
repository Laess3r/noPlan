package com.noplan.persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author DaHu4wA (Stefan Huber)
 */
public class HibernateListener implements ServletContextListener {

	/**
	 * Will be called on server-start to initialize hibernate
	 */
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getSessionFactory();
	}

	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.shutdown();
	}
}