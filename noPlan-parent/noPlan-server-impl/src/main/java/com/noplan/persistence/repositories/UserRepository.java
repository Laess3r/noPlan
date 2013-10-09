package com.noplan.persistence.repositories;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.persistence.entity.UserEntity;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Service
public class UserRepository extends AbstractRepository {

	@Transactional(readOnly = true)
	public UserEntity getUserById(Long id) {
		Query q = getSession().createQuery("From UserEntity user where user.id = :id");
		q.setParameter("id", id);

		return (UserEntity) q.uniqueResult();
	}

	@Transactional(readOnly = true)
	public UserEntity getUserByUsername(String username) {
		Query q = getSession().createQuery("From UserEntity user where user.username = :username");
		q.setParameter("username", username);

		return (UserEntity) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserEntity> getAllUsers() {
		Query q = getSession().createQuery("From UserEntity");

		return q.list();
	}

	@Transactional(readOnly = false)
	public void saveUser(UserEntity user) {
		save(user);
	}

	@Transactional(readOnly = false)
	public void updateUser(UserEntity user) {
		update(user);
	}

	@Transactional(readOnly = false)
	public void deleteUser(UserEntity user) {
		delete(user);
	}

}
