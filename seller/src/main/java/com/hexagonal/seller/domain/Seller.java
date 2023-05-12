package com.hexagonal.seller.domain;

import lombok.*;


public class Seller {

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Create {
		private String email;
		private String password;
		private String registrationNum;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Login {
		private String email;
		private String password;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Delete {
		private String email;
		private String password;
	}
}
