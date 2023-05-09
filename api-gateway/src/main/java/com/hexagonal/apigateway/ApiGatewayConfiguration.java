package com.hexagonal.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/users/**")
						.uri("http://localhost:8081/")
				)
				.route(p -> p
						.path("/products/**")
						.uri("http://localhost:8082/")
				)
				.route(p -> p
						.path("/orders/**")
						.uri("http://localhost:8083/")
				)
				.route(p -> p
						.path("/payments/**")
						.uri("http://localhost:8084/")
				)
				.route(p -> p
						.path("/shipments/**")
						.uri("http://localhost:8085/")
				)
				.build();
	}

}
