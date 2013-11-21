package com.noplan.persistence.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noplan.persistence.entity.UserEntity;
import com.noplan.persistence.entity.UserRoleEntity;

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

	@Transactional(readOnly = false)
	public void saveRole(UserRoleEntity role) {
		save(role);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserRoleEntity> getRolesForUser(Long userId) {
		Criteria criteria = getSession().createCriteria(UserRoleEntity.class);
		criteria.add(Restrictions.eq("userId.id", userId));

		return criteria.list();
	}

	@Transactional(readOnly = false)
	public void addRoleToUser(Long userId, String role) {

		UserEntity usr = getUserById(userId);

		if (usr == null) {
			throw new IllegalArgumentException("User not found! ID: " + userId);
		}

		List<UserRoleEntity> existingRoles = getRolesForUser(userId);

		for (UserRoleEntity savedRole : existingRoles) {
			if (savedRole.getAuthority().equals(role)) {
				throw new IllegalArgumentException("User " + usr.getUsername() + " already has role " + role);
			}
		}

		UserRoleEntity newRole = new UserRoleEntity();
		newRole.setUserId(usr);
		newRole.setAuthority(role);

		saveRole(newRole);
	}

	@Transactional(readOnly = false)
	public void removeRoleFromUser(Long userId, String role) {

		UserEntity usr = getUserById(userId);

		if (usr == null) {
			throw new IllegalArgumentException("User not found! ID: " + userId);
		}

		List<UserRoleEntity> existingRoles = getRolesForUser(userId);

		for (UserRoleEntity savedRole : existingRoles) {
			if (savedRole.getAuthority().equals(role)) {
				delete(savedRole);
				return;
			}
		}
		throw new IllegalArgumentException("User " + usr.getUsername() + " does not have role " + role);
	}

}
