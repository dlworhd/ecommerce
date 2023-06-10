package com.hexagonal.user.adapter.out;

import com.hexagonal.user.application.port.out.UserPersistencePort;
import com.hexagonal.user.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDto.Response createUser(UserDto.Request user) {
		if (userRepository.existsByEmail(user.getEmail())) throw new RuntimeException("Duplicated User!");
		UserEntity userEntity = userRepository.save(UserEntity.from(user, passwordEncoder));
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

		if (!passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
			throw new RuntimeException("Password not equal");
		}

		UserDto.Response response = UserDto.Response.from(userEntity);
		return response;
	}
}
