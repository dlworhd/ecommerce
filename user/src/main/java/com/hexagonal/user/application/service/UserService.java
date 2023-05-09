package com.hexagonal.user.application.service;

import com.hexagonal.user.application.port.in.UserUseCase;
import com.hexagonal.user.application.port.out.UserPersistencePort;
import com.hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

	private final UserPersistencePort userPersistencePort;

	@Override
	public void createUser(User user) {
		userPersistencePort.createUser(user);
	}

	@Override
	public void modifyUser(User user) {
		userPersistencePort.modifyUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userPersistencePort.deleteUser(user);
	}
}
