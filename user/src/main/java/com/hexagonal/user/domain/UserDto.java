package com.hexagonal.user.domain;

import com.hexagonal.user.infrastructure.jpa.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDto {

	@Getter
	@Builder
	@AllArgsConstructor
	public static class Request {

		private String email;
		private String password;

	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class Response {

		private UUID userId;
		private String token;

		public void setToken(String token) {
			this.token = token;
		}

		public static Response from(UserEntity user){
			return Response.builder()
					.userId(user.getId())
					.build();
		}
	}
}