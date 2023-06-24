package com.ecommerce.user.adapter.in;

import com.ecommerce.user.application.port.in.UserUseCase;
import com.ecommerce.user.application.util.jwt.TokenProvider;
import com.ecommerce.user.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserUseCase userUseCase;
	private final TokenProvider tokenProvider;

	@Override
	public ResponseEntity<?> users() {
		return ResponseEntity.ok("success");
	}

	@Override
	public ResponseEntity<?> createUser(UserDto.Request user) {
		return ResponseEntity.ok(userUseCase.createUser(user));
	}

//	@Override
//	public void modifyUser(UserDto.Request user) {
//		userUseCase.modifyUser(user);
//	}

	@Override
	public ResponseEntity<?> deleteUser(UserDto.Request user) {
		return ResponseEntity.ok(userUseCase.deleteUser(user));
	}

	@Override
	public ResponseEntity<?> login(UserDto.Request user) {
		UserDto.Response response = userUseCase.login(user);
		response.setToken(tokenProvider.createToken(user.getEmail()));
		return ResponseEntity.ok(response);
	}
}
