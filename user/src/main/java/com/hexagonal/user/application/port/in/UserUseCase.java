package com.hexagonal.user.application.port.in;

import com.hexagonal.user.domain.User;

public interface UserUseCase {
	void createUser(User user);
	void modifyUser(User user);
	void deleteUser(User user);
}
