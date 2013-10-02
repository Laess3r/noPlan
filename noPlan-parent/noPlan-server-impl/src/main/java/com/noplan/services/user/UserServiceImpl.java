package com.noplan.services.user;

import javax.ws.rs.Path;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.data.UserDTO;
import com.noplan.persistence.entity.UserEntity;
import com.noplan.services.AbstractPersistenceService;
import com.noplan.services.UserService;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/user")
@Component
public class UserServiceImpl extends AbstractPersistenceService implements
		UserService {

	@Override
	@Transactional(readOnly = true)
	public UserDTO getUserById(Long id) {

		Query q = getSession().createQuery(
				"From UserEntity user where user.id = :id");
		q.setParameter("id", id);

		Object result = q.uniqueResult();

		if (result == null) {
			return null;
		}

		return ((UserEntity) result).toUserDTO();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public UserDTO createUser(String username, String password) {

		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(password);

		getSession().save(user);

		return user.toUserDTO();
	}

}
