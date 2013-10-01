package com.noplan.services.user;

import java.util.List;

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

	private static int count = 0;

	@Override
	public UserDTO getUserById(Long id) {
		count++;

		// this is just a test for SPRING
		// otherServiceImpl.getTest();
		
		
		// hibernate test
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
		session.beginTransaction();
		 
		UserEntity usr = new UserEntity();
		usr.setPassword("password");
		usr.setUsername("HUBER");
		
		session.save(usr);
		 
		session.getTransaction().commit();
		Query q = session.createQuery("From UserEntity ");
		 
		List<UserEntity> resultList = q.list();
		System.out.println("num of UserEntity:" + resultList.size());
		for (UserEntity next : resultList) {
		System.out.println("next UserEntity: " + next);
		}
		 

		UserDTO u = new UserDTO();
		u.setId(id);
		u.setUsername(id + "TEST" + count);
		return u;
	}

}
