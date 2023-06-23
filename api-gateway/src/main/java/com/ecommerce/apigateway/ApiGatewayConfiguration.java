//package com.ecommerce.apigateway;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ApiGatewayConfiguration {
//	@Bean
//	RouteLocator routeLocator(RouteLocatorBuilder builder, CustomConfiguration.AuthorizationFilter authorizationFilter) {
//		return builder.routes()
//				.route(p -> p
//						.path("/users/**")
//						.uri("http://localhost:8080/")
//				)
//				.route(p -> p
//						.path("/sellers/**")
//						.uri("http://localhost:8081/")
//				)
//				.route(p -> p
//						.path("/products/**")
//						.filters(f -> f.filter(authorizationFilter.apply(new CustomConfiguration.AuthorizationFilter.Config())))
//						.uri("http://localhost:8082/")
//				)
//				.route(p -> p
//						.path("/orders/**")
//						.filters(f -> f.filter(authorizationFilter.apply(new CustomConfiguration.AuthorizationFilter.Config())))
//						.uri("http://localhost:8083/")
//				)
//				.route(p -> p
//						.path("/payments/**")
//						.filters(f -> f.filter(authorizationFilter.apply(new CustomConfiguration.AuthorizationFilter.Config())))
//						.uri("http://localhost:8084/")
//				)
//				.route(p -> p
//						.path("/shipments/**")
//						.filters(f -> f.filter(authorizationFilter.apply(new CustomConfiguration.AuthorizationFilter.Config())))
//						.uri("http://localhost:8085/")
//				)
//				.build();
//	}
//
//}
