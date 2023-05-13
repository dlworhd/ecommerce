package com.hexagonal.user.infrastructure.adapter.out;

import com.hexagonal.user.application.port.out.UserPersistencePort;
import com.hexagonal.user.domain.UserDto;
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
	public UserDto.Response createUser(UserDto.Request user) {
		if (userRepository.existsByEmail(user.getEmail())) throw new RuntimeException("Duplicated User!");
		UserEntity userEntity = userRepository.save(UserEntity.from(user));

		return UserDto.Response.from(userEntity);

	}

//	@Override
//	public UserDto.Response modifyUser(UserDto.Request user) {
//		UserEntity userEntity = getUserEntityByEmail(user.getEmail());
//		userEntity.update(user);
//
//		return userEntity.getId();
//	}

	private UserEntity getUserEntityByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Override
	public UserDto.Response deleteUser(UserDto.Request user) {
		UserEntity userEntity = getUserEntityByEmail(user.getEmail());
		userEntity.setUserStatus(UserStatus.UNACTIVE);

		return UserDto.Response.from(userEntity);
	}

	@Override
	public UserDto.Response login(UserDto.Request user) {
		UserEntity userEntity = getUserEntityByEmail(user.getEmail());

		if (!userEntity.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("Password not equal");
		}

		UserDto.Response response = UserDto.Response.from(userEntity);

		return response;
	}
}
