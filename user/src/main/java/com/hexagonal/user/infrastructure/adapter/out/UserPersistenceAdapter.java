package com.hexagonal.user.infrastructure.adapter.out;

import com.hexagonal.user.application.port.out.UserPersistencePort;
import com.hexagonal.user.domain.User;
import com.hexagonal.user.infrastructure.jpa.UserEntity;
import com.hexagonal.user.infrastructure.jpa.UserRepository;
import com.hexagonal.user.infrastructure.jpa.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

	private final UserRepository userRepository;

	@Override
	public void createUser(User user) {
		userRepository.save(UserEntity.from(user));
	}

	@Override
	public void modifyUser(User user) {
		UserEntity userEntity = getUserEntityByEmail(user.getEmail());
		userEntity.update(user);
	}

	private UserEntity getUserEntityByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Override
	public void deleteUser(User user) {
		UserEntity userEntity = getUserEntityByEmail(user.getEmail());
		userEntity.setUserStatus(UserStatus.UNACTIVE);
	}
}
