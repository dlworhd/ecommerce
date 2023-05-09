package com.hexagonal.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class CustomConfiguration {

	@Autowired
	private TokenProvider tokenProvider;

	@Bean
	public AuthorizationFilter authorizationFilter() {
		return new AuthorizationFilter();
	}


	public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

		public AuthorizationFilter() {
			super(Config.class);
		}

		@Override
		public GatewayFilter apply(Config config) {
			return (exchange, chain) -> {
				//TODO: ExceptionHandler 만들기
				String token = exchange.getRequest().getHeaders().get("Authorization").get(0).substring(7);
				String username = tokenProvider.getnameFromToken(token);
				addAuthorizationHeader(exchange.getRequest(), username);

				return chain.filter(exchange);
			};
		}

		public static class Config {

		}

		private void addAuthorizationHeader(ServerHttpRequest request, String username) {
			request.mutate().header("X-Authorization-Id", username).build();

		}
	}
}
