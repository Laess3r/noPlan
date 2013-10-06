package com.noplan.services.user;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noplan.data.UserDTO;
import com.noplan.persistence.entity.UserEntity;
import com.noplan.persistence.repositories.UserRepository;
import com.noplan.services.UserService;

/**
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

		return user.toUserDTO();
	}

	@Override
	public UserDTO createUser(UserDTO user) {

		// TODO check if username exists

		UserEntity userEntity = UserEntity.fromUserDTO(user);

		userRepository.saveUser(userEntity);

		return userEntity.toUserDTO();
	}

}
