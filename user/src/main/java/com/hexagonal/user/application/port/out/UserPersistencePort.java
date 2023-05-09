package com.hexagonal.user.application.port.out;

import com.hexagonal.user.domain.User;

public interface UserPersistencePort {

	void createUser(User user);
	void modifyUser(User user);
	void deleteUser(User user);

	void login(User user);
}
