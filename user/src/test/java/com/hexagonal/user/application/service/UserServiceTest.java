package com.hexagonal.user.application.service;

import com.hexagonal.user.domain.UserDto;
import com.hexagonal.user.infrastructure.adapter.out.UserPersistenceAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	//@Mock 설정을 하지 않으면 의존성 관련 에러가 발생한다.
	@Mock
	UserPersistenceAdapter userPersistenceAdapter;

	@Test
	@DisplayName("회원가입")
	void createUser() {
		UserDto.Request request = UserDto.Request.builder()
				.email("dlwodud821@gmail.com")
				.password("1234")
				.build();

		String userId = UUID.randomUUID().toString();
		String token = "token";

		when(userService.createUser(request))
				.thenReturn(UserDto.Response.builder()
						.userId(UUID.fromString(userId))
						.token(token)
						.nowTime(LocalDateTime.now()).build()
				);

		UserDto.Response response = userService.createUser(request);

		assertThat(response.getUserId().toString()).isEqualTo(userId);
		assertThat(response.getToken()).isEqualTo(token);
	}

	@Test
	@DisplayName("로그인")
	void login() {
		UserDto.Request request = UserDto.Request.builder()
				.email("dlwodud821@gmail.com")
				.password("1234")
				.build();

		String userId = UUID.randomUUID().toString();
		String token = "token";

		when(userService.login(request))
				.thenReturn(UserDto.Response.builder()
						.userId(UUID.fromString(userId))
						.token(token)
						.nowTime(LocalDateTime.now()).build()
				);

		UserDto.Response response = userService.login(request);

		assertThat(response.getUserId().toString()).isEqualTo(userId);
		assertThat(response.getToken()).isEqualTo(token);
	}


}
