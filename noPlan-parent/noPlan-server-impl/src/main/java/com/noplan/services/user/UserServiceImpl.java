package com.noplan.services.user;

import javax.ws.rs.Path;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noplan.data.user.UserDTO;
import com.noplan.persistence.HibernateUtil;
import com.noplan.persistence.user.UserEntity;

@Path("/user")
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private TestServiceImpl otherServiceImpl;

	@Override
	public UserDTO getUserById(Long id) {

		// this is just a test for SPRING
		// otherServiceImpl.getTest();

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
