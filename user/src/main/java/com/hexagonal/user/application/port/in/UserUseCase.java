package com.hexagonal.user.application.port.in;

import com.hexagonal.user.domain.UserDto;

public interface UserUseCase {
	UserDto.Response createUser(UserDto.Request user);
//	UserDto.Response modifyUser(UserDto.Request user);
	UserDto.Response deleteUser(UserDto.Request user);
	UserDto.Response login(UserDto.Request user);
}
