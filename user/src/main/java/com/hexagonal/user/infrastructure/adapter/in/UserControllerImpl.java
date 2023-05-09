package com.hexagonal.user.infrastructure.adapter.in;

import com.hexagonal.user.application.port.in.UserUseCase;
import com.hexagonal.user.application.util.jwt.TokenProvider;
import com.hexagonal.user.domain.Token;
import com.hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserUseCase userUseCase;
	private final TokenProvider tokenProvider;

	@Override
	public void createUser(User user) {
		userUseCase.createUser(user);
	}

//	@Override
//	public void modifyUser(User user) {
//		userUseCase.modifyUser(user);
//	}

	@Override
	public void deleteUser(User user) {
		userUseCase.deleteUser(user);
	}

	@Override
	public ResponseEntity<?> login(User user) {
		userUseCase.login(user);
		return ResponseEntity.ok(Token.builder().token(tokenProvider.createToken(user.getEmail())).build());
	}
}
