package com.noplan.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noplan.data.UserDTO;
import com.noplan.persistence.entity.UserEntity;
import com.noplan.persistence.repositories.UserRepository;

/**
 * This is the main user service
 * 
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/user")
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO getUserById(Long id) {

		UserEntity user = userRepository.getUserById(id);

		if (user == null) {
			return null;
		}

		return user.toDTO();
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		UserEntity user = userRepository.getUserByUsername(username);

		if (user == null) {
			return null;
		}

		return user.toDTO();
	}

	@Override
	public UserDTO createUser(UserDTO user) {

		// TODO check if username exists

		UserEntity userEntity = new UserEntity(user);

		userRepository.saveUser(userEntity);

		return userEntity.toDTO();
	}

	@Override
	public List<UserDTO> getAllUsers() {

		List<UserDTO> result = new ArrayList<UserDTO>();

		for (UserEntity user : userRepository.getAllUsers()) {
			result.add(user.toDTO());
		}

		return result;
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		UserEntity entity = userRepository.getUserById(user.getId());

		// map the changes into the entity
		entity.fromDTO(user, true);

		userRepository.updateUser(entity);

		return entity.toDTO();
	}

	@Deprecated
	@Override
	public UserDTO createUser(String username, String pw) {
		UserDTO user = new UserDTO();
		
		user.setUsername(username);
		user.setPassword(pw);
		
		return createUser(user);
	}

}
