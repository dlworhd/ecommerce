package com.ecommerce.user.application.service;

import com.ecommerce.user.application.port.in.UserUseCase;
import com.ecommerce.user.application.port.out.UserPersistencePort;
import com.ecommerce.user.domain.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

	private final UserPersistencePort userPersistencePort;

	@Override
	@Transactional
	public UserDto.Response createUser(UserDto.Request user) {
		return userPersistencePort.createUser(user);
	}

//	@Override
//	@Transactional
//	public UserDto.Response modifyUser(UserDto.Request user) {
//		userPersistencePort.modifyUser(user);
//	}

	@Override
	@Transactional
	public UserDto.Response deleteUser(UserDto.Request user) {
		return userPersistencePort.deleteUser(user);
	}

	@Override
	public UserDto.Response login(UserDto.Request user) {
		return userPersistencePort.login(user);
	}
}
