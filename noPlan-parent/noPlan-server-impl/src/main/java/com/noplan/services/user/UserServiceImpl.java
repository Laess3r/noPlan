package com.noplan.services.user;

import javax.ws.rs.Path;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.noplan.data.UserDTO;
import com.noplan.persistence.HibernateUtil;
import com.noplan.persistence.entity.UserEntity;
import com.noplan.services.UserService;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/user")
@Component
public class UserServiceImpl implements UserService {

	@Override
	public UserDTO getUserById(Long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query q = session
				.createQuery("From UserEntity user where user.id = :id");
		q.setParameter("id", id);

		Object result = q.uniqueResult();

		if (result == null) {
			return null;
		}

		return ((UserEntity) result).toUserDTO();
	}

	@Override
	public UserDTO createUser(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(password);

		session.save(user);

		session.getTransaction().commit();

		return user.toUserDTO();
	}
}
