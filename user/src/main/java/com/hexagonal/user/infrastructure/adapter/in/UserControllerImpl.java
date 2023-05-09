package com.hexagonal.user.infrastructure.adapter.in;

import com.hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {



	@Override
	public void createUser(User user) {

	}

	@Override
	public void modifyUser(User user) {

	}

	@Override
	public void deleteUser(User user) {

	}
}
