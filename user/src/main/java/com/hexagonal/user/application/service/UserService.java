package com.hexagonal.user.application.service;

import com.hexagonal.user.application.port.in.UserUseCase;
import com.hexagonal.user.application.port.out.UserPersistencePort;
import com.hexagonal.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

	private final UserPersistencePort userPersistencePort;

	@Override
	@Transactional
	public void createUser(User user) {
		userPersistencePort.createUser(user);
	}

	@Override
	@Transactional
	public void modifyUser(User user) {
		userPersistencePort.modifyUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userPersistencePort.deleteUser(user);
	}

	@Override
	public void login(User user) {
		userPersistencePort.login(user);
	}
}
