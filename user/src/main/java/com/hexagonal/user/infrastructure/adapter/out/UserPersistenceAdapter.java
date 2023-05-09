package com.hexagonal.user.infrastructure.adapter.out;

import com.hexagonal.user.application.port.out.UserPersistencePort;
import com.hexagonal.user.domain.User;
import com.hexagonal.user.infrastructure.jpa.UserEntity;
import com.hexagonal.user.infrastructure.jpa.UserRepository;
import com.hexagonal.user.infrastructure.jpa.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

	private final UserRepository userRepository;

	@Override
	public void createUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) throw new RuntimeException("Duplicated User!");
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

	@Override
	public void login(User user) {
		UserEntity userEntity = getUserEntityByEmail(user.getEmail());

		if (!userEntity.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("Password not equal");
		}
	}
}
