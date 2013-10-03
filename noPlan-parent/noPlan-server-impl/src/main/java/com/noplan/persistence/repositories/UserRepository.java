package com.noplan.persistence.repositories;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.noplan.persistence.entity.UserEntity;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Repository
public class UserRepository extends AbstractRepository {

	public UserEntity getUserById(Long id) {
		Query q = getSession().createQuery(
				"From UserEntity user where user.id = :id");
		q.setParameter("id", id);

		return (UserEntity) q.uniqueResult();
	}

	public void saveUser(UserEntity user) {
		save(user);
	}

	public void updateUser(UserEntity user) {
		update(user);
	}

	public void deleteUser(UserEntity user) {
		delete(user);
	}

}
