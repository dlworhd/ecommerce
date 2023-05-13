package com.hexagonal.user.application.port.out;

import com.hexagonal.user.domain.UserDto;

public interface UserPersistencePort {

	UserDto.Response createUser(UserDto.Request user);
//	UserDto.Response modifyUser(UserDto.Request user);
	UserDto.Response deleteUser(UserDto.Request user);

	UserDto.Response login(UserDto.Request user);
}
